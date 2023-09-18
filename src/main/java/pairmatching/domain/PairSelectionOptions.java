package pairmatching.domain;

public final class PairSelectionOptions {

  private final Course course;
  private final Level level;
  private final Mission mission;

  private PairSelectionOptions(
      final Course course,
      final Level level,
      final Mission mission
  ) {
    this.course = course;
    this.level = level;
    this.mission = mission;
  }

  public static PairSelectionOptions of(
      final Course course,
      final Level level,
      final Mission mission
  ) {
    return new PairSelectionOptions(course, level, mission);
  }

  public Course getCourse() {
    return course;
  }

  public Level getLevel() {
    return level;
  }

  public Mission getMission() {
    return mission;
  }
}
