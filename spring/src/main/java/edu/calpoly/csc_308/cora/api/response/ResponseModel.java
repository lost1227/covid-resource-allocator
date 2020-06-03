package edu.calpoly.csc_308.cora.api.response;

public abstract class ResponseModel {
  private boolean ok = true;

  public void setOk(boolean ok) {
    this.ok = ok;
  }

  public boolean getOk() {
    return ok;
  }
}
