package pairmatching.domain;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public final class Crews {

  private final Deque<Crew> crews;

  private Crews(final Deque<Crew> crews) {
    this.crews = crews;
  }

  public static Crews of(final List<Crew> crews) {
    return new Crews(new LinkedList<>(crews));
  }


  public List<Crew> take() {
    final List<Crew> pickedCrews = new ArrayList<>();

    // 2명씩 크루를 뽑음
    pickedCrews.add(crews.pollFirst());
    pickedCrews.add(crews.pollFirst());
    // 마지막에 1명이 남는 경우는 3명을 뽑음
    if (crews.size() == 1) {
      pickedCrews.add(crews.pollFirst());
    }

    return pickedCrews;
  }

  public boolean isNotEmpty() {
    return !crews.isEmpty();
  }
}
