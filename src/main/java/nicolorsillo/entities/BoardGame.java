package nicolorsillo.entities;

import java.util.Objects;

public class BoardGame extends Game{

    private int numOfPlayers;
    private int averagePlayingTime;

    public BoardGame (String gameID, String gameTitle, int gameYear, double gamePrice, int numOfPlayers, int averagePlayingTime){
        super(gameID, gameTitle, gameYear, gamePrice);
        this.numOfPlayers = numOfPlayers;
        this.averagePlayingTime = averagePlayingTime;
    }

    public int getNumOfPlayers() {
        return numOfPlayers;
    }

    public  int getAveragePlayingTime() {
        return averagePlayingTime;
    }

    @Override
    public String toString() {
        return "BoardGame{" +
                "numOfPlayers=" + numOfPlayers +
                ", averagePlayingTime=" + averagePlayingTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BoardGame boardGame = (BoardGame) o;
        return numOfPlayers == boardGame.numOfPlayers && averagePlayingTime == boardGame.averagePlayingTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numOfPlayers, averagePlayingTime);
    }
}
