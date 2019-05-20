package converter.model;

public class AuditAction {

  private final String sessionId;
  private final String action;

  public AuditAction(String sessionId, String action) {
    this.sessionId = sessionId;
    this.action = action;
  }

  public String getSessionId() {
    return sessionId;
  }

  public String getAction() {
    return action;
  }
}
