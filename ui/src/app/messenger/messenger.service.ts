import { Injectable } from '@angular/core';
import { LoginManagerService } from '@app/loginmanager.service';
import { UserinfoApiService, UserInfoResponseModel } from '@app/api/userinfo-api.service';
import { MessengerApiService, ListConversationsRequestModel, SendMessageRequestModel } from '@app/api/messenger-api.service';
import { Observable, Subject, combineLatest } from 'rxjs';
import { toArray, mergeMap, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class MessengerService {

  constructor(
    private login : LoginManagerService,
    private userapi : UserinfoApiService,
    private messengerapi : MessengerApiService) { }

  listConversations() : Observable<Conversation[]> {
    const userId = this.login.getLoggedInUserId();
    return this.messengerapi.listConversations(
      new ListConversationsRequestModel(this.login.getAuthToken())
    ).pipe(
      mergeMap(conversations => conversations.conversations),
      mergeMap( conversation => combineLatest(this.getMessageUser(userId), this.getMessageUser(conversation.userId)).pipe(
        map( ([senderUser, receiverUser]) => new Conversation(senderUser, receiverUser, null)) 
      )),
      toArray()
      );
  }


  getMessageUser(id : number) : Observable<MessageUser> {
    return this.userapi.getUserInfo(id).pipe(
      map( userinfo => new MessageUser(
        userinfo.id,
        userinfo.name
      ))
    )
  }

  sendMessage(message : Message) {
    return this.messengerapi.sendMessage(new SendMessageRequestModel(
      this.login.getAuthToken(),
      message.receiverId,
      message.messageText
    ))
  }

  public selectedConversation = new Subject<Conversation>()

}
export class MessageUser {
  constructor(
    public readonly id : number,
    public readonly name : String
  ) {}
}
export class Message {
  constructor(
    public readonly senderId : number,
    public readonly receiverId : number,
    public readonly messageText : string
  ) {}
}
export class Conversation {
  constructor(
    public readonly sender : MessageUser,
    public readonly receiver : MessageUser,
    public readonly history : Message[]
  ) {}
}
