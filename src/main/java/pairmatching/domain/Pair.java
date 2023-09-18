package pairmatching.domain;

import java.util.List;

public final class Pair {

  private final List<Crew> crews;

  private final Course course;
  private final Level level;
  private final Mission mission;

  private Pair(
      final List<Crew> crews,
      final Course course,
      final Level level,
      final Mission mission
  ) {
    this.crews = crews;
    this.course = course;
    this.level = level;
    this.mission = mission;
  }

  public static Pair of(
      final List<Crew> crews,
      final PairSelectionOptions options
  ) {
    return new Pair(
        crews,
        options.getCourse(),
        options.getLevel(),
        options.getMission()
    );
  }


  public boolean containsMoreThanOneCrew(final Pair pair) {
    return this.crews.stream()
        .filter(pair.crews::contains)
        .count() > 1;
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

  public List<Crew> getCrews() {
    return crews;
  }
}
