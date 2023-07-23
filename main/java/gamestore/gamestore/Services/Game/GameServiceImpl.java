package gamestore.gamestore.Services.Game;

import gamestore.gamestore.Entities.Game;
import gamestore.gamestore.Entities.Models.AddGameDTO;
import gamestore.gamestore.Entities.User;
import gamestore.gamestore.Repositories.GameRepository;
import gamestore.gamestore.Services.User.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static gamestore.gamestore.Constants.ErrorMessages.GAME_NOT_EXIST;
import static gamestore.gamestore.Constants.ErrorMessages.USER_NOT_ADMIN;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserService userService;
    private final ModelMapper mapper = new ModelMapper();

    public GameServiceImpl(GameRepository gameRepository,UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
    }

    @Override
    public String addGame(String[] args) {
        checkIfLoggedUserIsAdmin();
        String title = args[1];
        double price = Double.parseDouble(args[2]);
        double size = Double.parseDouble(args[3]);
        String trailer = args[4];
        String url = args[5];
        String description = args[6];
        LocalDate localDate = LocalDate.now();
        AddGameDTO addGameDTO;
        try {
             addGameDTO = new AddGameDTO(title,price,size,trailer,url,description,localDate);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        Game mappedGame = mapper.map(addGameDTO, Game.class);
        this.gameRepository.save(mappedGame);
        return String.format("Successfully added game %s !",mappedGame.getTitle());
    }

    @Override
    public String deleteGame(int gameId) {
        checkIfLoggedUserIsAdmin();
        Optional<Game> gameToBeRemoved = this.gameRepository.findById(gameId);
        if (gameToBeRemoved.isEmpty()) { throw new IllegalArgumentException(GAME_NOT_EXIST); }
        this.gameRepository.delete(gameToBeRemoved.get());
        return String.format("Successfully removed game %s from database !",gameToBeRemoved.get().getTitle());
    }
    @Override
    public void checkIfLoggedUserIsAdmin() {
        User loggedUser = this.userService.getLoggedUser();
        if (!loggedUser.getIsAdministrator()) { throw new IllegalArgumentException(USER_NOT_ADMIN); }
    }

    @Override
    public String gameDetails(int gameId) {
        StringBuilder sb = new StringBuilder();
        Optional<Game> gameToBeFound = this.gameRepository.findById(gameId);
        if (gameToBeFound.isEmpty()) { throw new IllegalArgumentException(GAME_NOT_EXIST); }
        Game game = gameToBeFound.get();
        sb.append(String.format( game.getTitle() + " " + game.getDescription() + " " + game.getPrice()));
        return sb.toString();
    }

}
