package converter.model;

public class AuthModel {

  private final String sessionID;
  private final String error;

  public AuthModel(String sessionID, String error) {
    this.sessionID = sessionID;
    this.error = error;
  }

  public String getSessionID() {
    return sessionID;
  }

  public String getError() {
    return error;
  }
}
