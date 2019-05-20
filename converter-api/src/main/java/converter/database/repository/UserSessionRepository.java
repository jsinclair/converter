package converter.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import converter.database.model.UserSession;

@Repository
public interface UserSessionRepository extends CrudRepository<UserSession, Long> {

  @Query("SELECT us FROM UserSession us WHERE us.sessionId = :sessionId")
  public Optional<UserSession> findUserSessionBySessionId(@Param("sessionId") String sessionId);
}
