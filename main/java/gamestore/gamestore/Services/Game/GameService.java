package gamestore.gamestore.Services.Game;

public interface GameService {
     String addGame(String[] args);
     String deleteGame(int gameId);
     void checkIfLoggedUserIsAdmin();
     String gameDetails(int gameId);

}
