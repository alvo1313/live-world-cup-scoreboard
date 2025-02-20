package live.worldcup.scoreboard;

import java.util.List;

/**
 * Facade providing a simplified interface to the scoreboard system.
 * Serves as the primary entry point for clients.
 * Single Responsibility: Simplifies the scoreboard interface for clients.
 * Dependency Inversion: Depends on abstractions.
 */
public class ScoreboardFacade {
    private final Scoreboard scoreboard;
    private final MatchFactory matchFactory;

    /**
     * Creates a new scoreboard facade.
     */
    public ScoreboardFacade() {
        this.scoreboard = new FootballScoreboard();
        this.matchFactory = new MatchFactory();
    }

    /**
     * Starts a new match with provided team names.
     *
     * @param homeTeamName the name of the home team
     * @param awayTeamName the name of the away team
     * @return the created match
     * @throws IllegalArgumentException if team names are invalid
     * @throws IllegalStateException if match already exists
     */
    public Match startMatch(String homeTeamName, String awayTeamName) {
        Team homeTeam = new Team(homeTeamName);
        Team awayTeam = new Team(awayTeamName);
        return scoreboard.startMatch(homeTeam, awayTeam);
    }

    /**
     * Updates the score for a match identified by team names.
     *
     * @param homeTeamName the name of the home team
     * @param awayTeamName the name of the away team
     * @param homeScore the new score for the home team
     * @param awayScore the new score for the away team
     * @throws IllegalArgumentException if match not found or scores invalid
     */
    public void updateScore(String homeTeamName, String awayTeamName, int homeScore, int awayScore) {
        Team homeTeam = new Team(homeTeamName);
        Team awayTeam = new Team(awayTeamName);
        Score score = new Score(homeScore, awayScore);
        scoreboard.updateScore(homeTeam, awayTeam, score);
    }

    /**
     * Finishes a match identified by team names.
     *
     * @param homeTeamName the name of the home team
     * @param awayTeamName the name of the away team
     * @throws IllegalArgumentException if match not found
     */
    public void finishMatch(String homeTeamName, String awayTeamName) {
        Team homeTeam = new Team(homeTeamName);
        Team awayTeam = new Team(awayTeamName);
        scoreboard.finishMatch(homeTeam, awayTeam);
    }

    /**
     * Gets a summary of all matches in progress.
     *
     * @return ordered list of matches
     */
    public List<Match> getMatchesSummary() {
        return scoreboard.getMatchesSummary();
    }

    /**
     * Gets the number of matches currently in progress.
     *
     * @return match count
     */
    public int getMatchesCount() {
        return scoreboard.getMatchesCount();
    }
}