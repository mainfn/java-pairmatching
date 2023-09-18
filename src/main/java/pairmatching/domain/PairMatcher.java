package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class PairMatcher {

  private final PairRepository pairRepository;
  private final CrewRepository crewRepository;

  public PairMatcher(
      final PairRepository pairRepository,
      final CrewRepository crewRepository
  ) {
    this.pairRepository = pairRepository;
    this.crewRepository = crewRepository;
  }

  // 해당 옵션에 따라 페어를 매칭한 적이 있는지
  public boolean hasDuplicatePairMatchResult(final PairSelectionOptions options) {
    final List<Pair> pairs = pairRepository.findManyByOptions(options);
    return !pairs.isEmpty();
  }

  // 페어 매칭 시도
  public PairMatchResult match(final PairSelectionOptions options) {
    // 중복 매치 정보가 있으면 모두 제거
    pairRepository.removeManyByOptions(options);

    // 매치 성공 시, 모든 Pair List에 담아 반환
    final List<Pair> matchedPairs = rematch(options, 1);

    // 저장
    pairRepository.saveAll(matchedPairs);

    // 결과 생성
    return PairMatchResult.of(matchedPairs);
  }

  private List<Pair> rematch(
      final PairSelectionOptions options,
      final int attemptCount
  ) {
    // 3회 실패 시, 예외 발생
    if (attemptCount > 3) {
      throw new IllegalStateException("페어 매치에 3회 실패했습니다.");
    }
    // 매치 된 Pair를 저장할 리스트
    final List<Pair> matchedPairs = new ArrayList<>();

    // Course가 일치하는 Crew를 모두 가져옴
    final Crews shuffledCrews = crewRepository.getShuffledCrewNamesByCourse(
        options.getCourse());

    // 크루를 랜덤으로 하나씩 뽑아서 가져옴
    while (shuffledCrews.isNotEmpty()) {
      // 매치 가능한 경우 Optional에 Pair가 담겨서 반환됨
      final Optional<Pair> pair = takePairIfCanMatch(shuffledCrews, options);
      if (!pair.isPresent()) {
        // 매치에 실패한 경우 재귀 호출로 재시도, 시도 횟수를 1 늘림
        return rematch(options, attemptCount + 1);
      }
      // 매치 성공 시, 리스트에 Pair 추가
      matchedPairs.add(pair.get());
    }
    // 끝까지 성공 시, 리스트 반환
    return matchedPairs;
  }

  private Optional<Pair> takePairIfCanMatch(
      final Crews shuffledCrews,
      final PairSelectionOptions options
  ) {
    // 크루를 2명 혹은 홀수일 경우 마지막 3명을 뽑음
    final List<Crew> crews = shuffledCrews.take();
    // 페어로 변환
    final Pair pair = Pair.of(crews, options);
    // 매치가 가능한지 확인
    final boolean canMatch = pairRepository.canMatch(pair);

    // 가능하다면 Optional에 담아서 반환
    if (canMatch) {
      return Optional.of(pair);
    }
    // 매치 불가 시, 빈 Optional 반환
    return Optional.empty();
  }

  public PairMatchResult findMatchedPairs(final PairSelectionOptions options) {
    return PairMatchResult.of(pairRepository.findManyByOptions(options));
  }

  public void initializePairs() {
    pairRepository.removeAll();
  }
}
