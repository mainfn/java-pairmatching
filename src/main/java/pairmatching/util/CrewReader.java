package pairmatching.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public final class CrewReader {

  private static final String FRONTEND_CREW_FILE_PATH = "./src/main/resources/frontend-crew.md";
  private static final String BACKEND_CREW_FILE_PATH = "./src/main/resources/backend-crew.md";

  public static List<String> readFrontendCrewNames() {
    return readFileLines(FRONTEND_CREW_FILE_PATH);
  }

  public static List<String> readBackendCrewNames() {
    return readFileLines(BACKEND_CREW_FILE_PATH);
  }

  private static List<String> readFileLines(final String filePath) {
    try {
      return Files.readAllLines(Paths.get(filePath));
    } catch (Exception e) {
      throw new RuntimeException("파일 읽기에 실패했습니다.");
    }
  }
}
