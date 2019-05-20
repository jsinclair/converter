package converter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.Optional;

import converter.model.AuthModel;
import converter.model.LoginModel;
import converter.database.service.IUserService;
import converter.database.service.IUserSessionService;
import converter.database.service.ISessionActionService;
import converter.database.model.User;
import converter.database.model.UserSession;
import converter.database.model.SessionAction;

@RestController
public class AuthController {

  @Autowired
  private IUserService userService;

  @Autowired
  private IUserSessionService userSessionService;

  @Autowired
  private ISessionActionService sessionActionService;

  @CrossOrigin
  @PostMapping("/auth")
  AuthModel auth(@RequestBody LoginModel credentials) {
    String sessionID = "";
    String error = "";

    Optional<User> optionalUser = userService.findUserByUsername(credentials.getUsername());
    if (optionalUser.isPresent()
    && optionalUser.get().getPassword().equals(credentials.getPassword())) {
      User user = optionalUser.get();
      UUID uuid = UUID.randomUUID();
      sessionID = uuid.toString();

      // Create and save a new session record
      final UserSession newSession = new UserSession(user.getId(), sessionID);
      userSessionService.save(newSession);
      sessionActionService.save(new SessionAction(newSession.getId(), "Logged In"));
    } else {
      error = "User not found.";
    }

    /*for (User user : userService.findAll()) {
      System.out.println(user.getId() + " " + user.getUsername());
    }*/

    return new AuthModel(sessionID, error);
  }
}
