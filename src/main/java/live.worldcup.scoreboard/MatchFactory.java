package live.worldcup.scoreboard;

/**
 * Factory for creating match objects.
 * Single Responsibility: Handles match creation logic.
 * Dependency Inversion: Clients depend on the interface, not implementation.
 */
public class MatchFactory {
    /**
     * Creates a match between two teams specified by name.
     *
     * @param homeTeamName the name of the home team
     * @param awayTeamName the name of the away team
     * @return a new match instance
     * @throws IllegalArgumentException if team names are invalid
     */
    public Match createMatch(String homeTeamName, String awayTeamName) {
        Team homeTeam = new Team(homeTeamName);
        Team awayTeam = new Team(awayTeamName);
        return new FootballMatch(homeTeam, awayTeam);
    }
}
