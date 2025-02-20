package live.worldcup.scoreboard;

import java.time.LocalDateTime;

/**
 * Defines the contract for a football match.
 * Interface segregation principle: clients depend only on methods they use.
 */
public interface Match {
    /**
     * Gets the unique identifier for this match.
     *
     * @return the match identifier
     */
    String getId();

    /**
     * Gets the home team.
     *
     * @return the home team
     */
    Team getHomeTeam();

    /**
     * Gets the away team.
     *
     * @return the away team
     */
    Team getAwayTeam();

    /**
     * Gets the current score.
     *
     * @return the current score
     */
    Score getScore();

    /**
     * Updates the score for the match.
     *
     * @param score the new score
     */
    void updateScore(Score score);

    /**
     * Gets the time when the match started.
     *
     * @return the start time
     */
    LocalDateTime getStartTime();
}