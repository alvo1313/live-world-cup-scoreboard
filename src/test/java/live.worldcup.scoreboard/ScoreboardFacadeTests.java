package live.worldcup.scoreboard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ScoreboardFacadeTests {
    private ScoreboardFacade facade;

    @BeforeEach
    void setUp() {
        facade = new ScoreboardFacade();
    }

    @Test
    void shouldStartMatchWithZeroScore() {
        Match match = facade.startMatch("Home", "Away");

        assertEquals("Home", match.getHomeTeam().getName());
        assertEquals("Away", match.getAwayTeam().getName());
        assertEquals(0, match.getScore().getHomeGoals());
        assertEquals(0, match.getScore().getAwayGoals());
        assertEquals(1, facade.getMatchesCount());
    }

    @Test
    void shouldThrowExceptionWhenStartingInvalidMatch() {
        assertThrows(IllegalArgumentException.class,
                () -> facade.startMatch("Home", "Home"),
                "Home and away teams cannot be the same");
    }

    @Test
    void shouldThrowExceptionWhenStartingDuplicateMatch() {
        facade.startMatch("Home", "Away");

        assertThrows(IllegalStateException.class,
                () -> facade.startMatch("Home", "Away"),
                "Match between these teams is already in progress");
    }

    @Test
    void shouldUpdateMatchScore() {
        facade.startMatch("Home", "Away");
        facade.updateScore("Home", "Away", 2, 1);

        Match match = facade.getMatchesSummary().get(0);
        assertEquals(2, match.getScore().getHomeGoals());
        assertEquals(1, match.getScore().getAwayGoals());
    }

    @Test
    void shouldThrowExceptionWhenUpdatingWithNegativeScore() {
        facade.startMatch("Home", "Away");

        assertThrows(IllegalArgumentException.class,
                () -> facade.updateScore("Home", "Away", -1, 0),
                "Scores cannot be negative");
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonExistentMatch() {
        assertThrows(IllegalArgumentException.class,
                () -> facade.updateScore("Home", "Away", 1, 1),
                "Match not found");
    }

    @Test
    void shouldFinishExistingMatch() {
        facade.startMatch("Home", "Away");
        facade.finishMatch("Home", "Away");

        assertEquals(0, facade.getMatchesCount());
    }

    @Test
    void shouldThrowExceptionWhenFinishingNonExistentMatch() {
        assertThrows(IllegalArgumentException.class,
                () -> facade.finishMatch("Home", "Away"),
                "Match not found");
    }

    @Test
    void shouldReturnMatchesSummaryOrderedByTotalScoreAndStartTime() {
        setupWorldCupScenario();

        var summary = facade.getMatchesSummary();

        assertEquals(5, summary.size());
        assertEquals("Uruguay 6 - Italy 6", summary.get(0).toString());
        assertEquals("Spain 10 - Brazil 2", summary.get(1).toString());
        assertEquals("Mexico 0 - Canada 5", summary.get(2).toString());
        assertEquals("Argentina 3 - Australia 1", summary.get(3).toString());
        assertEquals("Germany 2 - France 2", summary.get(4).toString());
    }

    private void setupWorldCupScenario() {
        facade.startMatch("Mexico", "Canada");
        facade.startMatch("Spain", "Brazil");
        facade.startMatch("Germany", "France");
        facade.startMatch("Uruguay", "Italy");
        facade.startMatch("Argentina", "Australia");

        facade.updateScore("Mexico", "Canada", 0, 5);
        facade.updateScore("Spain", "Brazil", 10, 2);
        facade.updateScore("Germany", "France", 2, 2);
        facade.updateScore("Uruguay", "Italy", 6, 6);
        facade.updateScore("Argentina", "Australia", 3, 1);
    }
}