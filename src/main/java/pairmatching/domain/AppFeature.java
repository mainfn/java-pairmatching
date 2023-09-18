package pairmatching.domain;

import java.util.HashMap;
import java.util.Map;

public enum AppFeature {
  MATCH_PAIRS("1"),
  QUERY_PAIRS("2"),
  INITIALIZE_PAIRS("3"),
  QUIT("Q");

  private static final Map<String, AppFeature> appFeaturesMap = new HashMap<>();

  static {
    for (final AppFeature appFeature : values()) {
      appFeaturesMap.put(appFeature.command, appFeature);
    }
  }

  private final String command;

  AppFeature(final String command) {
    this.command = command;
  }

  public static AppFeature of(final String command) {
    if (!appFeaturesMap.containsKey(command)) {
      throw new IllegalArgumentException("잘못된 기능을 선택하셨습니다.");
    }
    return appFeaturesMap.get(command);
  }
}
