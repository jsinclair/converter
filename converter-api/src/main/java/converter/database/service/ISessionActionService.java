package converter.database.service;

import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Service;

import converter.database.model.SessionAction;

@Service
public interface ISessionActionService {

    List<SessionAction> findAll();

    List<SessionAction> findActionsForSession(Long userSessionId);

    void save(SessionAction sessionAction);
}
