package edu.calpoly.csc_308.cora.api.response;

import lombok.Data;

@Data
public abstract class ResponseModel {
  private boolean ok = true;
}
