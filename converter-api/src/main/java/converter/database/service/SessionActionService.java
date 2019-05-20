package converter.database.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import converter.database.model.SessionAction;
import converter.database.repository.SessionActionRepository;

@Service
public class SessionActionService implements ISessionActionService {

    @Autowired
    private SessionActionRepository repository;

    @Override
    public List<SessionAction> findAll() {
        return (List<SessionAction>) repository.findAll();
    }

    public List<SessionAction> findActionsForSession(Long userSessionId) {
      return repository.findActionsForSession(userSessionId);
    }

    public void save(SessionAction sessionAction) {
      repository.save(sessionAction);
    }
}
