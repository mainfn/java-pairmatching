package pairmatching.domain;

import java.util.HashMap;
import java.util.Map;

public enum Mission {
  M1("자동차경주", Level.LEVEL1),
  M2("로또", Level.LEVEL1),
  M3("숫자야구게임", Level.LEVEL1),
  M4("장바구니", Level.LEVEL2),
  M5("결제", Level.LEVEL2),
  M6("지하철노선도", Level.LEVEL2),
  M7("성능개선", Level.LEVEL4),
  M8("배포", Level.LEVEL4);

  private static final Map<String, Mission> missionsMap = new HashMap<>();

  static {
    for (final Mission mission : values()) {
      missionsMap.put(mission.title, mission);
    }
  }

  private final String title;
  private final Level level;

  Mission(final String title, final Level level) {
    this.title = title;
    this.level = level;
  }

  public static Mission of(final String title) {
    if (!missionsMap.containsKey(title)) {
      throw new IllegalArgumentException("유효하지 않은 미션명입니다.");
    }
    return missionsMap.get(title);
  }

}
