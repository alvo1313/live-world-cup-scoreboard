package live.worldcup.scoreboard;

/**
 * Represents a match score with validation logic.
 * Encapsulates score data and operations.
 */
public class Score {
    private final int homeGoals;
    private final int awayGoals;

    /**
     * Creates a new score with validation.
     *
     * @param homeGoals goals scored by home team
     * @param awayGoals goals scored by away team
     * @throws IllegalArgumentException if scores are negative
     */
    public Score(int homeGoals, int awayGoals) {
        if (homeGoals < 0 || awayGoals < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    /**
     * Gets the home team's goals.
     *
     * @return home goals
     */
    public int getHomeGoals() {
        return homeGoals;
    }

    /**
     * Gets the away team's goals.
     *
     * @return away goals
     */
    public int getAwayGoals() {
        return awayGoals;
    }

    /**
     * Calculates the total goals scored in the match.
     *
     * @return total goals
     */
    public int getTotalGoals() {
        return homeGoals + awayGoals;
    }

    @Override
    public String toString() {
        return homeGoals + " - " + awayGoals;
    }
}