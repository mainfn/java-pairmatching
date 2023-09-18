package pairmatching;

import pairmatching.controller.MainController;
import pairmatching.domain.CrewRepository;
import pairmatching.domain.PairMatcher;
import pairmatching.domain.PairRepository;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public final class Application {

  public static void main(final String[] args) {
    final InputView inputView = new InputView();
    final OutputView outputView = new OutputView();
    final PairRepository pairRepository = new PairRepository();
    final CrewRepository crewRepository = CrewRepository.create();
    final PairMatcher pairMatcher = new PairMatcher(pairRepository, crewRepository);
    final MainController mainController = new MainController(outputView, inputView, pairMatcher);

    try {
      mainController.start();
    } catch (final Exception e) {
      System.out.printf("[ERROR] %s\n", e.getMessage());
    }
  }
}
