package live.worldcup.scoreboard;

import java.util.List;

/**
 * Defines the contract for a football scoreboard.
 * Interface segregation principle: clients depend only on methods they use.
 */
public interface Scoreboard {
    /**
     * Starts a new match between two teams with initial score 0-0.
     *
     * @param homeTeam the home team
     * @param awayTeam the away team
     * @return the newly created match
     * @throws IllegalStateException if a match between these teams is already in progress
     */
    Match startMatch(Team homeTeam, Team awayTeam);

    /**
     * Updates the score for an existing match.
     *
     * @param homeTeam the home team
     * @param awayTeam the away team
     * @param score the new score
     * @throws IllegalArgumentException if the match doesn't exist
     */
    void updateScore(Team homeTeam, Team awayTeam, Score score);

    /**
     * Removes a match from the scoreboard.
     *
     * @param homeTeam the home team
     * @param awayTeam the away team
     * @throws IllegalArgumentException if the match doesn't exist
     */
    void finishMatch(Team homeTeam, Team awayTeam);

    /**
     * Gets a summary of all matches in progress, ordered by:
     * 1. Total score (descending)
     * 2. Most recently started (for matches with equal scores)
     *
     * @return an ordered list of matches
     */
    List<Match> getMatchesSummary();

    /**
     * Gets the current number of matches in progress.
     *
     * @return the count of matches
     */
    int getMatchesCount();
}