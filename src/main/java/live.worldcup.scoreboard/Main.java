package live.worldcup.scoreboard;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("World Cup Score Board Demo:");

        ScoreboardFacade scoreboard = new ScoreboardFacade();

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

        List<Match> summary = scoreboard.getMatchesSummary();

        System.out.println("\nCurrent matches summary:");
        for (int i = 0; i < summary.size(); i++) {
            System.out.println((i + 1) + ". " + summary.get(i));
        }

        scoreboard.finishMatch("Mexico", "Canada");
        System.out.println("\nSummary after finishing Mexico-Canada match:");
        summary = scoreboard.getMatchesSummary();
        for (int i = 0; i < summary.size(); i++) {
            System.out.println((i + 1) + ". " + summary.get(i));
        }
    }
}