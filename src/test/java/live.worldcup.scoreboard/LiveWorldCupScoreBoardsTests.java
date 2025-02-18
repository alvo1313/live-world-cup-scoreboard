package live.worldcup.scoreboard;
import live.worldcup.scoreboard.Match;
import live.worldcup.scoreboard.Scoreboard;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LiveWorldCupScoreBoardTests {
    private Scoreboard scoreboard;

    @BeforeEach
    void setUp() {
        scoreboard = new Scoreboard();
    }

    @Test
    void shouldStartMatchWithZeroScore() {
        Match match = scoreboard.startMatch("Home", "Away");

        assertEquals("Home", match.getHomeTeam());
        assertEquals("Away", match.getAwayTeam());
        assertEquals(0, match.getHomeScore());
        assertEquals(0, match.getAwayScore());
        assertEquals(1, scoreboard.getMatchesCount());
    }


    @Test
    void shouldUpdateMatchScore() {
        scoreboard.startMatch("Home", "Away");
        scoreboard.updateScore("Home", "Away", 2, 1);

        Match match = scoreboard.getMatchesSummary().get(0);
        assertEquals(2, match.getHomeScore());
        assertEquals(1, match.getAwayScore());
    }

    @Test
    void shouldFinishExistingMatch() {
        scoreboard.startMatch("Home", "Away");
        scoreboard.finishMatch("Home", "Away");

        assertEquals(0, scoreboard.getMatchesCount());
    }


    @Test
    void shouldReturnMatchesSummaryOrderedByTotalScoreAndStartTime() {
        setupWorldCupScenario();

        var summary = scoreboard.getMatchesSummary();

        assertEquals(5, summary.size());
        assertEquals("Uruguay 6 - Italy 6", summary.get(0).toString());
        assertEquals("Spain 10 - Brazil 2", summary.get(1).toString());
        assertEquals("Mexico 0 - Canada 5", summary.get(2).toString());
        assertEquals("Argentina 3 - Australia 1", summary.get(3).toString());
        assertEquals("Germany 2 - France 2", summary.get(4).toString());
    }

    @Test
    void shouldOrderEqualScoreMatchesByMostRecent() {
        scoreboard.startMatch("Team1", "Team2");
        scoreboard.startMatch("Team3", "Team4");

        scoreboard.updateScore("Team1", "Team2", 2, 2); // Total 4
        scoreboard.updateScore("Team3", "Team4", 3, 1); // Total 4

        var summary = scoreboard.getMatchesSummary();
        assertEquals("Team3 3 - Team4 1", summary.get(0).toString());
    }

    private void setupWorldCupScenario() {
        scoreboard.startMatch("Mexico", "Canada");
        scoreboard.startMatch("Spain", "Brazil");
        scoreboard.startMatch("Germany", "France");
        scoreboard.startMatch("Uruguay", "Italy");
        scoreboard.startMatch("Argentina", "Australia");

        scoreboard.updateScore("Mexico", "Canada", 0, 5);
        scoreboard.updateScore("Spain", "Brazil", 10, 2);
        scoreboard.updateScore("Germany", "France", 2, 2);
        scoreboard.updateScore("Uruguay", "Italy", 6, 6);
        scoreboard.updateScore("Argentina", "Australia", 3, 1);
    }
}