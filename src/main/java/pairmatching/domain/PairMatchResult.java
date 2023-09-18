package pairmatching.domain;

import java.util.List;
import java.util.stream.Collectors;

public final class PairMatchResult {

  private final List<Pair> pairs;

  private PairMatchResult(final List<Pair> pairs) {
    this.pairs = pairs;
  }

  public static PairMatchResult of(final List<Pair> pairs) {
    return new PairMatchResult(pairs);
  }

  @Override
  public String toString() {
    return pairs.stream()
        .map(this::toPairString)
        .collect(Collectors.joining("\n"));
  }

  private String toPairString(final Pair pair) {
    return pair.getCrews()
        .stream()
        .map(Crew::getName)
        .collect(Collectors.joining(" : "));
  }

  public boolean notFound() {
    return pairs.isEmpty();
  }
}
