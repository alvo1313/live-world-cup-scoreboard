package live.worldcup.scoreboard;

import java.util.*;

/**
 * Implementation of a football scoreboard.
 * Single Responsibility: Manages the collection of matches.
 * Open/Closed: Extended through interfaces without modification.
 * Liskov Substitution: Properly implements Scoreboard interface.
 * Interface Segregation: Uses focused interfaces.
 * Dependency Inversion: Depends on abstractions, not concrete implementations.
 */
public class FootballScoreboard implements Scoreboard {
    private final NavigableSet<Match> matches;
    private final Map<String, Match> matchIndex;
    private final MatchComparator comparator;

    /**
     * Creates a new empty scoreboard.
     */
    public FootballScoreboard() {
        this.comparator = new MatchComparator();
        this.matches = new TreeSet<>(comparator);
        this.matchIndex = new HashMap<>();
    }

    @Override
    public Match startMatch(Team homeTeam, Team awayTeam) {
        String matchKey = createMatchKey(homeTeam, awayTeam);
        if (matchIndex.containsKey(matchKey)) {
            throw new IllegalStateException("Match between these teams is already in progress");
        }

        Match match = new FootballMatch(homeTeam, awayTeam);
        matches.add(match);
        matchIndex.put(matchKey, match);
        return match;
    }

    @Override
    public void updateScore(Team homeTeam, Team awayTeam, Score score) {
        Match match = findMatch(homeTeam, awayTeam);
        matches.remove(match);
        match.updateScore(score);
        matches.add(match);
    }

    @Override
    public void finishMatch(Team homeTeam, Team awayTeam) {
        Match match = findMatch(homeTeam, awayTeam);
        matches.remove(match);
        matchIndex.remove(createMatchKey(homeTeam, awayTeam));
    }

    @Override
    public List<Match> getMatchesSummary() {
        return new ArrayList<>(matches);
    }

    @Override
    public int getMatchesCount() {
        return matches.size();
    }

    /**
     * Finds a match by team objects.
     *
     * @param homeTeam the home team
     * @param awayTeam the away team
     * @return the match if found
     * @throws IllegalArgumentException if match not found
     */
    private Match findMatch(Team homeTeam, Team awayTeam) {
        String matchKey = createMatchKey(homeTeam, awayTeam);
        Match match = matchIndex.get(matchKey);
        if (match == null) {
            throw new IllegalArgumentException("Match not found");
        }
        return match;
    }

    /**
     * Creates a unique key for match lookup.
     *
     * @param homeTeam the home team
     * @param awayTeam the away team
     * @return a unique key string
     */
    private String createMatchKey(Team homeTeam, Team awayTeam) {
        return homeTeam.getName() + ":::" + awayTeam.getName();
    }
}