package nicolorsillo.entities;

import java.util.Objects;

public abstract class Game {
    private String gameID;
    private String gameTitle;
    private int gameYear;
    private double gamePrice;

    // Constructor

    public Game(String gameID, String gameTitle, int gameYear, double gamePrice) {
        if (gamePrice < 0) {
            throw new IllegalArgumentException("Il prezzo del gioco non può essere negativo");
        }
        this.gameID = gameID;
        this.gameTitle = gameTitle;
        this.gameYear = gameYear;
        this.gamePrice = gamePrice;
    }

    // Getter

    public String getGameID() {
        return gameID;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public int getGameYear() {
        return gameYear;
    }

    public double getGamePrice() {
        return gamePrice;
    }

    @Override
    public String toString() {
        return "Game{" +
                "ID= " + gameID +
                ", Title= " + gameTitle +
                ", Year= " + gameYear +
                ", Price= " + gamePrice;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return gameYear == game.gameYear && Double.compare(gamePrice, game.gamePrice) == 0 && Objects.equals(gameID, game.gameID) && Objects.equals(gameTitle, game.gameTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameID, gameTitle, gameYear, gamePrice);
    }
}
