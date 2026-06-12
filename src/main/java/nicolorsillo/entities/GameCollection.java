package nicolorsillo.entities;

import nicolorsillo.exceptions.GameNotFoundException;
import nicolorsillo.exceptions.IdAlreadyExistException;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameCollection {
    private List<Game> gamesList = new ArrayList<>();

    //Aggiungi gioco

    public void addGame(Game game) throws IdAlreadyExistException {
        if (gamesList.stream().anyMatch(g -> g.getGameID().equals(game.getGameID()))) {
            throw new IdAlreadyExistException(" Esiste gia un gioco con l'ID " + game.getGameID());
        } else {
            gamesList.add(game);
        }
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

    //Ricerca tramite ID

    public Optional<Game> searchByID(String gameID) {
        return gamesList.stream().filter(g -> g.getGameID().equals(gameID)).findFirst();
    }

    //Ricerca tramite Prezzo

    public List<Game> searchByMaxPrice(double maxPrice) {
        if (maxPrice < 0) {
            throw new IllegalArgumentException("Inserire un valore positivo");
        }
        return gamesList.stream().filter(g -> g.getGamePrice() <= maxPrice).toList();
    }

    //Ricerca tramite numero di Giocatori

    public List<Game> searchByNumOfPlayers(int numOfPlayers) {
        if (numOfPlayers < 1) {
            throw new IllegalArgumentException("Deve esserci almeno un giocatore");
        }
        return gamesList.stream().filter(g -> g instanceof BoardGame).
                filter(bg -> ((BoardGame) bg).
                        getMaxNumOfPlayers() >= numOfPlayers && ((BoardGame) bg).getMinNumOfPlayers() <= numOfPlayers).collect(Collectors.toList());
    }

    //Rimuovi tramite ID

    public void removeByID(String gameID) throws GameNotFoundException {
        if (gamesList.stream().anyMatch(game -> game.getGameID().equals(gameID))) {
            gamesList.removeIf(game -> game.getGameID().equals(gameID));
        } else {
            throw new GameNotFoundException("Non esiste nessun gioco con l'ID " + gameID);
        }
    }

    //Aggiorna Gioco

    public void updateGame(String gameID, Game updatedGame) throws GameNotFoundException {
        if (gamesList.stream().noneMatch(game -> game.getGameID().equals(gameID))) {
            throw new GameNotFoundException("Non esiste nessun gioco con l'ID " + gameID);
        } else {
            gamesList.set(gamesList.indexOf(gamesList.stream().filter(g -> g.getGameID().equals(gameID)).findFirst().get()), updatedGame);
        }
    }

    //Salva Statistiche

    public List<Game> searchByPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Inserire un valore positivo");
        }
        return gamesList.stream().filter(g -> g.getGamePrice() == price).toList();
    }

    public void printStats() {
        if (gamesList.isEmpty()) {
            System.out.println("La lista di giochi è vuota");
        } else {
            DoubleSummaryStatistics stats = gamesList.stream().collect(Collectors.summarizingDouble(Game::getGamePrice));
            long totalVideoGames = gamesList.stream().filter(g -> g instanceof VideoGame).count();
            long totalBoardGames = gamesList.stream().filter(g -> g instanceof BoardGame).count();


            System.out.println("Numero totale di giochi in catalogo: " + stats.getCount());
            System.out.println("Numero totale di Videogiochi: " + totalVideoGames);
            System.out.println("Numero totale di Giochi da Tavolo: " + totalBoardGames);
            if ((long) searchByPrice(stats.getMax()).size() > 1) {
                System.out.print("I giochi con il prezzo più alto (" + stats.getMax() + "€) sono: ");
            } else {
                System.out.print("Il gioco con il prezzo più alto (" + stats.getMax() + "€) è: ");
            }
            searchByPrice(stats.getMax()).forEach(g -> System.out.print("'" + g.getGameTitle() + "' "));
            System.out.println();
            if ((long) searchByPrice(stats.getMin()).size() > 1) {
                System.out.print("I giochi con il prezzo più basso (" + stats.getMin() + "€) sono: ");
            } else {
                System.out.print("Il gioco con il prezzo più basso (" + stats.getMin() + "€) è: ");
            }
            searchByPrice(stats.getMin()).forEach(g -> System.out.print("'" + g.getGameTitle() + "' "));
            System.out.println();
            System.out.println("Prezzo medio dei giochi: " + stats.getAverage() + "€");

        }
    }
}
