package converter.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import converter.database.model.SessionAction;

@Repository
public interface SessionActionRepository extends CrudRepository<SessionAction, Long> {

  @Query("SELECT sa FROM SessionAction sa WHERE sa.userSessionId = :userSessionId")
  public List<SessionAction> findActionsForSession(@Param("userSessionId") Long userSessionId);
}
