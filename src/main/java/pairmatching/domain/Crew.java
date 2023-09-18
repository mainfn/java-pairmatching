package pairmatching.domain;

public final class Crew {

  private final String name;
  private final Course course;

  private Crew(
      final String name,
      final Course course
  ) {
    this.name = name;
    this.course = course;
  }

  public static Crew of(
      final String name,
      final Course course
  ) {
    return new Crew(name, course);
  }

  public Course getCourse() {
    return course;
  }

  public String getName() {
    return name;
  }
}
