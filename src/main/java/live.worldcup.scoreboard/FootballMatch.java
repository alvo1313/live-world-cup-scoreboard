package live.worldcup.scoreboard;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Implementation of a football match.
 * Single Responsibility: Represents a match and its state.
 */
public class FootballMatch implements Match {
    private final String id;
    private final Team homeTeam;
    private final Team awayTeam;
    private Score score;
    private final LocalDateTime startTime;

    /**
     * Creates a new match with the specified teams and initializes score to 0-0.
     *
     * @param homeTeam the home team
     * @param awayTeam the away team
     * @throws IllegalArgumentException if teams are identical
     */
    public FootballMatch(Team homeTeam, Team awayTeam) {
        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Home and away teams cannot be the same");
        }

        this.id = UUID.randomUUID().toString();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.score = new Score(0, 0);
        this.startTime = LocalDateTime.now();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Team getHomeTeam() {
        return homeTeam;
    }

    @Override
    public Team getAwayTeam() {
        return awayTeam;
    }

    @Override
    public Score getScore() {
        return score;
    }

    @Override
    public void updateScore(Score score) {
        this.score = score;
    }

    @Override
    public LocalDateTime getStartTime() {
        return startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballMatch that = (FootballMatch) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s %d - %s %d",
                homeTeam, score.getHomeGoals(),
                awayTeam, score.getAwayGoals());
    }
}