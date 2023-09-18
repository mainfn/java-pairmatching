package pairmatching.controller;

import pairmatching.domain.AppFeature;
import pairmatching.domain.PairMatchResult;
import pairmatching.domain.PairMatcher;
import pairmatching.domain.PairSelectionOptions;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public final class MainController {

  private final InputView inputView;

  private final OutputView outputView;

  private final PairMatcher pairMatcher;

  public MainController(
      final OutputView outputView,
      final InputView inputView,
      final PairMatcher pairMatcher
  ) {
    this.inputView = inputView;
    this.outputView = outputView;
    this.pairMatcher = pairMatcher;
  }

  public void start() {
    // 1. 기능 선택
    boolean isRunning = true;
    while (isRunning) {
      isRunning = selectFeature();
    }
  }

  private boolean selectFeature() {
    final AppFeature feature = inputView.inputAppFeature();

    if (AppFeature.MATCH_PAIRS == feature) {
      // PairSelectionOptionsMap 출력(첫 매칭 시도 시에만 출력)
      outputView.printPairSelectionOptionsMap();
      matchPairs();
    }
    if (AppFeature.QUERY_PAIRS == feature) {
      outputView.printPairSelectionOptionsMap();
      queryPairs();
    }
    if (AppFeature.INITIALIZE_PAIRS == feature) {
      pairMatcher.initializePairs();
      outputView.printPairsInitializationMessage();
    }
    return AppFeature.QUIT != feature;
  }

  private void queryPairs() {
    // 1. 과정, 레벨, 미션을 입력 받음
    final PairSelectionOptions pairSelectionOptions = inputView.inputPairSelectionOptions();

    // 2. 매칭 결과가 있는지 확인
    final PairMatchResult result = pairMatcher.findMatchedPairs(pairSelectionOptions);

    // 3. 없으면 오류
    if (result.notFound()) {
      throw new IllegalStateException("매칭 이력이 없습니다.");
    }
    // 4. 있으면 결과 출력
    outputView.printPairMatchResult(result);
  }

  private void matchPairs() {
    // 1. 과정, 레벨, 미션을 입력 받음
    final PairSelectionOptions pairSelectionOptions = inputView.inputPairSelectionOptions();

    // 2. 해당 미션의 페어 매칭 정보가 이미 존재하는지 확인
    final boolean hasDuplicatePairMatchResult = pairMatcher.hasDuplicatePairMatchResult(
        pairSelectionOptions);

    // 3. 중복 매칭 정보가 있는 경우, 재매칭 여부 받기
    if (hasDuplicatePairMatchResult) {
      final boolean willRematch = inputView.inputRematchSelection();
      if (!willRematch) {
        // 4. 재매칭 하지 않는 경우, 다시 옵션 선택
        matchPairs();
        return;
      }
    }

    // 5. 주어진 옵션대로 매칭 및 결과 출력
    final PairMatchResult result = pairMatcher.match(pairSelectionOptions);
    outputView.printPairMatchResult(result);
  }
}
