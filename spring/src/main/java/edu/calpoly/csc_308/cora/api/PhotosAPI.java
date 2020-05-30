package edu.calpoly.csc_308.cora.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;

import org.springframework.web.multipart.MultipartFile;


import org.springframework.security.core.Authentication;

import edu.calpoly.csc_308.cora.api.response.FailResponse;
import edu.calpoly.csc_308.cora.api.response.ResponseModel;
import edu.calpoly.csc_308.cora.api.response.UploadPhotoResponse;
import edu.calpoly.csc_308.cora.data.photos.PhotoDAO;
import edu.calpoly.csc_308.cora.data.photos.PhotoRepository;

import edu.calpoly.csc_308.cora.security.AuthUser;
import edu.calpoly.csc_308.cora.entities.User;

@RestController
public class PhotosAPI {

  
  private Logger logger = LoggerFactory.getLogger(MessengerAPI.class);

  @Autowired
  private PhotoRepository repo;

  @GetMapping("/api/photo/get")
  public ResponseEntity<Resource> getPhoto(@RequestParam Long id) {
    Optional<PhotoDAO> optionalDao = repo.findById(id);
    if(!optionalDao.isPresent()) {
      return ResponseEntity.status(404).header(HttpHeaders.CONTENT_TYPE, "image/jpeg").body(new ClassPathResource("placeholder.jpg"));
    }
    PhotoDAO dao = optionalDao.get();
    Resource resource = new ByteArrayResource(dao.data);

    ResponseEntity<Resource> response = ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, dao.contentType).body(resource);
    return response;
  }
  
  @PostMapping("/api/photo/post")
  public ResponseEntity<ResponseModel> uploadPhoto(@RequestParam MultipartFile file, Authentication authentication) {
    User principal = ((AuthUser) authentication.getPrincipal()).user;
    switch(file.getContentType()) {
      case "image/bmp":
      case "image/gif":
      case "image/jpeg":
      case "image/png":
      case "image/webp":
        try {
          byte[] bytes = file.getBytes();
          byte[] converted = convertToJpg(bytes);
          if(converted == null) {
            return ResponseEntity.badRequest().body(new FailResponse());
          }
          PhotoDAO dao = new PhotoDAO(principal.id, "image/jpeg", converted);
          dao = repo.save(dao);
          return ResponseEntity.ok().body(new UploadPhotoResponse(dao.id));
        } catch (IOException e) {
          return ResponseEntity.badRequest().body(new FailResponse());
        }
        
      default:
        return ResponseEntity.badRequest().body(new FailResponse());
    }
  }

  private static byte[] convertToJpg(byte[] image) {
    try(ByteArrayInputStream bais = new ByteArrayInputStream(image);
        ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      BufferedImage img = ImageIO.read(bais);
      if(img == null) {
        return null;
      }
      ImageIO.write(img, "jpg", baos);
      baos.flush();
      return baos.toByteArray();
    } catch (IOException e) {
      return null;
    }
  }

}
