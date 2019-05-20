package converter.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;
import java.util.Optional;

import converter.database.service.IUserSessionService;
import converter.database.service.ISessionActionService;
import converter.database.model.UserSession;
import converter.database.model.SessionAction;
import converter.model.BasicResult;
import converter.model.AuditAction;

@RestController
public class AuditController {

  @Autowired
  private IUserSessionService userSessionService;

  @Autowired
  private ISessionActionService sessionActionService;

  @CrossOrigin
  @PostMapping("/log_action")
  BasicResult logAction(@RequestBody AuditAction action) {
    String message = "";

    Optional<UserSession> optionalSession = userSessionService
      .findUserSessionBySessionId(action.getSessionId());

    if (optionalSession.isPresent()) {
      UserSession session = optionalSession.get();
      // Create and save a new session action record
      sessionActionService.save(new SessionAction(session.getId(), action.getAction()));
      message = "Action logged";
    } else {
      message = "Session not found.";
    }

    return new BasicResult(message);
  }
}
