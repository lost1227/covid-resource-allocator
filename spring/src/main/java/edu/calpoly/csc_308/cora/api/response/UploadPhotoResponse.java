package edu.calpoly.csc_308.cora.api.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class UploadPhotoResponse extends ResponseModel {
  private final Long id;

}
