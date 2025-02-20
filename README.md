# Live Football World Cup Scoreboard

A simple Java library for tracking live football matches and their scores during the World Cup.

## Features

- Start new matches (initial score 0-0)
- Update match scores
- Finish matches (remove from scoreboard)
- Get summary of matches ordered by total score and start time

## Design

The library follows SOLID principles:

- **Single Responsibility**: Each class has one job (Team, Score, Match, etc.)
- **Open/Closed**: Extends through interfaces without modifying existing code
- **Liskov Substitution**: Implementations properly follow interface contracts
- **Interface Segregation**: Focused interfaces prevent unnecessary dependencies
- **Dependency Inversion**: High-level modules depend on abstractions

## Usage

```java
// Create scoreboard
ScoreboardFacade scoreboard = new ScoreboardFacade();

// Start match
scoreboard.startMatch("Spain", "Brazil");

// Update score
scoreboard.updateScore("Spain", "Brazil", 2, 1);

// Get ordered summary
List<Match> summary = scoreboard.getMatchesSummary();

// Finish match
scoreboard.finishMatch("Spain", "Brazil");
```

## Implementation Notes

- Uses in-memory storage (Java collections)
- Comprehensive validation for all inputs
- Ordering follows requirements (total score, then most recent)

## Testing

Includes complete test coverage for:
- Basic functionality
- Edge cases and error handling
- Required ordering logic
