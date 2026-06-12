package nicolorsillo.entities;

import java.util.Objects;

public class BoardGame extends Game {

    private int minNumOfPlayers;
    private int maxNumOfPlayers;
    private int averagePlayingTime;

    //Constructor

    public BoardGame(String gameID, String gameTitle, int gameYear, double gamePrice, int minNumOfPlayers, int maxNumOfPlayers, int averagePlayingTime) {
        super(gameID, gameTitle, gameYear, gamePrice);
        this.minNumOfPlayers = minNumOfPlayers;
        this.maxNumOfPlayers = maxNumOfPlayers;
        this.averagePlayingTime = averagePlayingTime;
    }

    //Getter

    public int getMinNumOfPlayers() {
        return minNumOfPlayers;
    }

    public int getMaxNumOfPlayers() {
        return maxNumOfPlayers;
    }

    public int getAveragePlayingTime() {
        return averagePlayingTime;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Type= BoardGame" +
                ", NumOfPlayers= " + minNumOfPlayers +
                "-" + maxNumOfPlayers +
                ", AveragePlayingTime= " + averagePlayingTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BoardGame boardGame = (BoardGame) o;
        return maxNumOfPlayers == boardGame.maxNumOfPlayers && minNumOfPlayers == boardGame.minNumOfPlayers && averagePlayingTime == boardGame.averagePlayingTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), maxNumOfPlayers, minNumOfPlayers, averagePlayingTime);
    }
}
