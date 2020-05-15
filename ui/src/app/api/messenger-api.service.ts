import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { ApiService, ResponseModel, AuthenticatedRequestModel } from '@app/api/api.service';
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

  listConversations(request : ListConversationsRequestModel) : Observable<ListConversationsResponse> {
    return super.verifyResponse(this.http.post<ListConversationsResponse>("/api/message/conversations", request));
  }
}

export class SendMessageRequestModel extends AuthenticatedRequestModel {
  constructor(
    authToken : string,
    public receiverId : number,
    public messageText : string
  ) {
    super(authToken);
  }
}

export class ListConversationsRequestModel extends AuthenticatedRequestModel {
  constructor(
    authToken : string
  ) {
    super(authToken);
  }
}

export interface ConversationResponse {
  userId : number
}

export interface ListConversationsResponse extends ResponseModel {
  userId : number,
  conversations : ConversationResponse[]
}
