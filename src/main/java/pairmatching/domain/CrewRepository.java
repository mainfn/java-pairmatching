package pairmatching.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.util.CrewReader;

public final class CrewRepository {

  private final List<Crew> crews;

  private CrewRepository(final List<Crew> crews) {
    this.crews = crews;
  }

  public static CrewRepository create() {
    final List<Crew> crews = new ArrayList<>();
    final List<String> frontendCrewNames = CrewReader.readFrontendCrewNames();
    final List<String> backendCrewNames = CrewReader.readBackendCrewNames();

    // 프론트엔드 크루 생성 및 추가
    for (final String name : frontendCrewNames) {
      final Crew crew = Crew.of(name, Course.FRONTEND);
      crews.add(crew);
    }

    // 백엔드 크루 생성 및 추가
    for (final String name : backendCrewNames) {
      final Crew crew = Crew.of(name, Course.BACKEND);
      crews.add(crew);
    }

    return new CrewRepository(crews);
  }

  //
  public Crews getShuffledCrewNamesByCourse(final Course course) {
    final List<String> crewNames = crews.stream()
        .filter(crew -> crew.getCourse().equals(course))
        .map(Crew::getName)
        .collect(Collectors.toList());

    return Crews.of(Randoms.shuffle(crewNames)
        .stream().map(crewName -> Crew.of(crewName, course))
        .collect(Collectors.toList())
    );
  }
}
