package edu.calpoly.csc_308.cora.data.photos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class PhotoDAO {
  public @Id @GeneratedValue Long id;

  public Long ownerId;

  public String contentType;

  @Lob
  public byte[] data;

  public PhotoDAO(Long ownerId, String contentType, byte[] data) {
    this.ownerId = ownerId;
    this.contentType = contentType;
    this.data = data;
  }

  public PhotoDAO() {}

  @Override
  public String toString() {
    return String.format("PhotoDAO(ownerId=%d,contentType=\"%s\",data=...)", ownerId, contentType);
  }
}
