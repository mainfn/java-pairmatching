package pairmatching.domain;

import java.util.HashMap;
import java.util.Map;

public enum Level {
  LEVEL1("레벨1"),
  LEVEL2("레벨2"),
  LEVEL3("레벨3"),
  LEVEL4("레벨4"),
  LEVEL5("레벨5");

  private static final Map<String, Level> levelsMap = new HashMap<>();

  static {
    for (final Level level : values()) {
      levelsMap.put(level.value, level);
    }
  }

  private final String value;

  Level(final String value) {
    this.value = value;
  }

  public static Level of(final String value) {
    if (!levelsMap.containsKey(value)) {
      throw new IllegalArgumentException("레벨1 ~ 5 중 하나를 입력하세요.");
    }
    return levelsMap.get(value);
  }

}