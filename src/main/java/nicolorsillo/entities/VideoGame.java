package nicolorsillo.entities;

import java.util.Objects;

public class VideoGame extends Game {
    private String platform;
    private int hoursOfPlay;
    private Genre genre;

    //Constructor

    public VideoGame(String gameID, String gameTitle, int gameYear, double gamePrice, String platform, int hoursOfPlay, Genre genre) {
        super(gameID, gameTitle, gameYear, gamePrice);
        this.platform = platform;
        this.hoursOfPlay = hoursOfPlay;
        this.genre = genre;
    }

    //Getter

    public String getPlatform() {
        return platform;
    }

    public int getHoursOfPlay() {
        return hoursOfPlay;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Type= VideoGame" +
                ", platform= " + platform +
                ", hoursOfPlay= " + hoursOfPlay +
                ", genre= " + genre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        VideoGame videoGame = (VideoGame) o;
        return hoursOfPlay == videoGame.hoursOfPlay && Objects.equals(platform, videoGame.platform) && genre == videoGame.genre;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), platform, hoursOfPlay, genre);
    }

    public enum Genre {
        ACTION,
        SHOOTER,
        SPORT,
        RACING,
        HORROR,
        PUZZLE
    }
}
