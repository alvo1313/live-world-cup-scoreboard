package live.worldcup.scoreboard;

/**
 * Represents a football team with validation logic.
 * Encapsulates team data and its validation rules.
 */
public class Team {
    private final String name;

    /**
     * Creates a new team with validation.
     *
     * @param name the team name
     * @throws IllegalArgumentException if name is invalid
     */
    public Team(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Team name cannot be null or empty");
        }
        this.name = name;
    }

    /**
     * Gets the team name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return name.equals(team.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}