package gamestore.gamestore.Services.User;

import gamestore.gamestore.Entities.Models.RegisterUserDTO;
import gamestore.gamestore.Entities.Models.UserLoginDTO;
import gamestore.gamestore.Entities.User;
import gamestore.gamestore.Repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static gamestore.gamestore.Constants.ErrorMessages.EMAIL_ALREADY_EXISTS;
import static gamestore.gamestore.Constants.ErrorMessages.INVALID_EMAIL_OR_PASSWORD;

@Service
public class UserServiceImpl implements UserService {

    private User loggedUser;
    private final UserRepository userRepository;
    private static final ModelMapper mapper = new ModelMapper();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        loggedUser = null;
        this.userRepository = userRepository;
    }

    @Override
    public String registerUser(String[] args) {
        String email = args[1];
        String password = args[2];
        String confirmPassword = args[3];
        String fullName = args[4];
        RegisterUserDTO userDTO;
        try {
             userDTO = new RegisterUserDTO(email, password, confirmPassword, fullName);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        Optional<User> user = this.userRepository.findByEmail(email);
        if (user.isPresent()) { return EMAIL_ALREADY_EXISTS; }
        User mappedUser = mapper.map(userDTO, User.class);
        if (userRepository.count() == 0 ) { mappedUser.setIsAdministrator(true); }
        this.userRepository.save(mappedUser);
        return String.format("User %s successfully registered !",mappedUser.getFullName());
    }

    @Override
    public String loginUser(String[] args) {
        String email = args[1];
        String password = args[2];
        String fullName = args[3];
        UserLoginDTO userLoginDTO;
        try {
            userLoginDTO = new UserLoginDTO(email,password,fullName);
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
        Optional<User> optionalUser = this.userRepository.findByEmailAndPasswordAndFullName(email, password, fullName);
        if (optionalUser.isEmpty()) {
           return INVALID_EMAIL_OR_PASSWORD;
       }
        User mappedUser = optionalUser.get();
       mappedUser.setIsLogged(true);
       this.userRepository.save(mappedUser);
       this.loggedUser = mappedUser;
       return String.format("User %s successfully logged !",userLoginDTO.getFullName());
    }

    @Override
    public String logout() {
        if (loggedUser != null) {
            String userLoggedOut = String.format("User %s logged out !",loggedUser.getFullName());
            this.loggedUser.setIsLogged(null);
            this.userRepository.save(loggedUser);
            this.loggedUser = null;
            return userLoggedOut;
        }
        return "No user have been logged, in order to logout !";
    }

    @Override
    public User getLoggedUser() {
        return this.loggedUser;
    }
}
