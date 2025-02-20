package live.worldcup.scoreboard;

import java.util.Comparator;

/**
 * Comparator for ordering matches according to business rules.
 * Single Responsibility: Handles the comparison logic for matches.
 */
public class MatchComparator implements Comparator<Match> {
    @Override
    public int compare(Match m1, Match m2) {
        // First by total goals (descending)
        int scoreCompare = Integer.compare(
                m2.getScore().getTotalGoals(),
                m1.getScore().getTotalGoals()
        );
        if (scoreCompare != 0) {
            return scoreCompare;
        }

        // Then by start time (most recent first)
        int timeCompare = m2.getStartTime().compareTo(m1.getStartTime());
        if (timeCompare != 0) {
            return timeCompare;
        }

        // Finally by ID for consistent ordering
        return m1.getId().compareTo(m2.getId());
    }
}