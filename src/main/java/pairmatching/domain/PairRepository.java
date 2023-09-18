package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class PairRepository {

  private List<Pair> pairs = new ArrayList<>();

  public PairRepository() {
  }

  public List<Pair> findManyByOptions(final PairSelectionOptions options) {
    return pairs.stream()
        .filter(pair -> options.getCourse().equals(pair.getCourse()))
        .filter(pair -> options.getLevel().equals(pair.getLevel()))
        .filter(pair -> options.getMission().equals(pair.getMission()))
        .collect(Collectors.toList());
  }

  // 이전에 페어로 매칭된 적이 없다면 페어 가능
  public boolean canMatch(final Pair pair) {
    return pairs.stream()
        .noneMatch(p ->
            p.containsMoreThanOneCrew(pair) &&
                p.getMission().equals(pair.getMission()) &&
                p.getLevel().equals(pair.getLevel())
        );
  }

  public void removeManyByOptions(final PairSelectionOptions options) {
    pairs.removeIf(p ->
        p.getCourse().equals(options.getCourse()) &&
            p.getLevel().equals(options.getLevel()) &&
            p.getMission().equals(options.getMission())
    );
  }

  public void saveAll(final List<Pair> matchedPairs) {
    pairs.addAll(matchedPairs);
  }

  public void removeAll() {
    pairs = new ArrayList<>();
  }
}
