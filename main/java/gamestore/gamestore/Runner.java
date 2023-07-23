package gamestore.gamestore;

import gamestore.gamestore.Services.Game.GameService;
import gamestore.gamestore.Services.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collectors;

import static gamestore.gamestore.Constants.Commands.*;

@Component
public class Runner implements CommandLineRunner {

    private UserService userService;
    private GameService gameService;
    private static final Scanner scanner = new Scanner(System.in);

    @Autowired
    public Runner(UserService userService,GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        String input = scanner.nextLine();

        while (!input.equals(CLOSE)) {
            String[] arguments = input.split("\\|");
            String command = arguments[0];
           final String output = switch (command) {
                case LOGIN_USER -> this.userService.loginUser(arguments);
                case REGISTER_USER -> this.userService.registerUser(arguments);
                case LOGOUT_USER -> this.userService.logout();
                case ADD_GAME -> this.gameService.addGame(arguments);
                case DELETE_GAME -> this.gameService.deleteGame(Integer.parseInt(arguments[1]));
                case GAME_INFO ->  this.gameService.gameDetails(Integer.parseInt(arguments[1]));
                default -> "There is no such command";
            };
            System.out.println(output);
            input = scanner.nextLine();
        }
    }
}
