package converter.database.service;

import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Service;

import converter.database.model.UserSession;

@Service
public interface IUserSessionService {

    List<UserSession> findAll();

    Optional<UserSession> findUserSessionBySessionId(String sessionId);

    void save(UserSession userSession);
}
