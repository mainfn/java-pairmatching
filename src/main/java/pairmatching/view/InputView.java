package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.domain.AppFeature;
import pairmatching.domain.Course;
import pairmatching.domain.Level;
import pairmatching.domain.Mission;
import pairmatching.domain.PairSelectionOptions;

public final class InputView {

  public AppFeature inputAppFeature() {
    System.out.println("기능을 선택하세요.");
    System.out.println("1. 페어 매칭");
    System.out.println("2. 페어 조회");
    System.out.println("3. 페어 초기화");
    System.out.println("Q. 종료");
    return AppFeature.of(readLine());
  }

  public boolean inputRematchSelection() {
    System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
    System.out.println("네 | 아니오");
    final String response = readLine();
    if ("네".equals(response)) {
      return true;
    }
    if ("아니오".equals(response)) {
      return false;
    }
    throw new IllegalArgumentException("네 | 아니오로 답해주세요.");
  }

  public PairSelectionOptions inputPairSelectionOptions() {

    System.out.println("과정, 레벨, 미션을 선택하세요.");

    final List<String> input = Arrays.stream(readLine().split(", "))
        .collect(Collectors.toList());

    final Course course = Course.of(input.get(0));
    final Level level = Level.of(input.get(1));
    final Mission mission = Mission.of(input.get(2));

    return PairSelectionOptions.of(course, level, mission);
  }
//  public List<String> input

  private String readLine() {
    return Console.readLine().trim();
  }
}
