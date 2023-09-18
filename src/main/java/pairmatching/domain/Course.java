package pairmatching.domain;

public enum Course {
  FRONTEND("프론트엔드"),
  BACKEND("백엔드");

  private final String title;

  Course(final String title) {
    this.title = title;
  }

  public static Course of(final String title) {
    if ("프론트엔드".equals(title)) {
      return FRONTEND;
    }
    if ("백엔드".equals(title)) {
      return BACKEND;
    }
    throw new IllegalArgumentException("프론트엔드 | 백엔드 중 하나만 입력 가능합니다.");
  }

}
