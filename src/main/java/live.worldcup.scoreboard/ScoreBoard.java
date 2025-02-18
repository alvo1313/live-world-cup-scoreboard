package live.worldcup.scoreboard;

import java.util.*;

public class Scoreboard {
    private final NavigableSet<Match> matches;
    private final Map<String, Match> matchIndex;

    public Scoreboard() {
        this.matches = new TreeSet<>((m1, m2) -> {
            int scoreCompare = Integer.compare(m2.getTotalScore(), m1.getTotalScore());
            if (scoreCompare != 0) {
                return scoreCompare;
            }
            int timeCompare = m2.getStartTime().compareTo(m1.getStartTime());
            if (timeCompare != 0) {
                return timeCompare;
            }
            return m1.getId().compareTo(m2.getId());
        });
        this.matchIndex = new HashMap<>();
    }


}