package converter.database.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import converter.database.model.UserSession;
import converter.database.repository.UserSessionRepository;

@Service
public class UserSessionService implements IUserSessionService {

    @Autowired
    private UserSessionRepository repository;

    @Override
    public List<UserSession> findAll() {
        return (List<UserSession>) repository.findAll();
    }

    public Optional<UserSession> findUserSessionBySessionId(String sessionId) {
      return repository.findUserSessionBySessionId(sessionId);
    }

    public void save(UserSession userSession) {
      repository.save(userSession);
    }
}
