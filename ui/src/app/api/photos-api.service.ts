import { Injectable } from '@angular/core';
import { ApiService, ResponseModel } from '@app/api/api.service';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PhotosApiService extends ApiService {

  constructor(
    private http : HttpClient
  ) {
    super();
  }

  postPhoto(photo : File) : Observable<UploadPhotoResponse> {
    const formData = new FormData();
    formData.append("file", photo, photo.name);
    return super.verifyResponse(this.http.post<UploadPhotoResponse>("/api/photo/post", formData));
  }
  
}

export interface UploadPhotoResponse extends ResponseModel {
  id : number
}
