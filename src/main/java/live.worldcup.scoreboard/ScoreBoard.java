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

    public Match startMatch(String homeTeam, String awayTeam) {
        String matchKey = createMatchKey(homeTeam, awayTeam);
        if (matchIndex.containsKey(matchKey)) {
            throw new IllegalStateException("Match between these teams is already in progress");
        }
        Match match = new Match(homeTeam, awayTeam);
        matches.add(match);
        matchIndex.put(matchKey, match);
        return match;
    }

    public void updateScore(String homeTeam, String awayTeam, int homeScore, int awayScore) {
        Match match = findMatch(homeTeam, awayTeam);
        matches.remove(match);
        match.updateScore(homeScore, awayScore);
        matches.add(match);
    }

    public void finishMatch(String homeTeam, String awayTeam) {
        Match match = findMatch(homeTeam, awayTeam);
        matches.remove(match);
        matchIndex.remove(createMatchKey(homeTeam, awayTeam));
    }

    public List<Match> getMatchesSummary() {
        return new ArrayList<>(matches);
    }

    private Match findMatch(String homeTeam, String awayTeam) {
        Match match = matchIndex.get(createMatchKey(homeTeam, awayTeam));
        if (match == null) {
            throw new IllegalArgumentException("Match not found");
        }
        return match;
    }

    private String createMatchKey(String homeTeam, String awayTeam) {
        return homeTeam + "|" + awayTeam;
    }

    public int getMatchesCount() {
        return matches.size();
    }
}