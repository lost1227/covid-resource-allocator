import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { ApiService, ResponseModel } from '@app/api/api.service';
@Injectable({
  providedIn: 'root'
})
export class MessengerApiService extends ApiService {

  constructor(private http : HttpClient) {
    super();
  }

  sendMessage(message : SendMessageRequestModel) : Observable<ResponseModel> {
    return super.verifyResponse(this.http.post<ResponseModel>("/api/message/post", message))
  }

  listConversations() : Observable<ListConversationsResponse> {
    return super.verifyResponse(this.http.get<ListConversationsResponse>("/api/message/conversations"));
  }
}

export class SendMessageRequestModel {
  constructor(
    public receiverId : number,
    public messageText : string
  ) { }
}

export interface MessageResponse {
  id : number;
  sender : number;
  receiver : number;
    
  messageText: string;

  sentTs : number;

}

export interface ConversationResponse {
  userId : number;
  messageHistory : MessageResponse[]
}

export interface ListConversationsResponse extends ResponseModel {
  userId : number,
  conversations : ConversationResponse[]
}
