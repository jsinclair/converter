package converter.database.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import converter.database.model.Auditable;

@Entity
@Table(name = "session_actions")
public class SessionAction extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userSessionId;
    private String action;

    public SessionAction() {
    }

    public SessionAction(Long userSessionId, String action) {
        this.userSessionId = userSessionId;
        this.action = action;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserSessionId() {
      return userSessionId;
    }

    public void setUserSessionId(Long userSessionId) {
      this.userSessionId = userSessionId;
    }

    public String getAction() {
      return action;
    }

    public void setAction(String action) {
      this.action = action;
    }
}
