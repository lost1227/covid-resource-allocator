package edu.calpoly.csc_308.cora.data.photos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class PhotoDAO {
  private @Id @GeneratedValue Long id;

  private Long ownerId;

  private String contentType;

  @Lob
  private byte[] data;

  public PhotoDAO(Long ownerId, String contentType, byte[] data) {
    this.ownerId = ownerId;
    this.contentType = contentType;
    this.data = data;
  }

  @Override
  public String toString() {
    return String.format("PhotoDAO(ownerId=%d,contentType=\"%s\",data=...)", ownerId, contentType);
  }
}
