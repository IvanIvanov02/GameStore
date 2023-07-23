package gamestore.gamestore.Services.User;


import gamestore.gamestore.Entities.User;

public interface UserService {
    String registerUser(String[] args);
    String loginUser(String[] args);
    String logout();
    User getLoggedUser();
}
