import { Injectable } from '@angular/core';
import { LoginManagerService } from '@app/loginmanager.service';
import { UserinfoApiService } from '@app/api/userinfo-api.service';
import { MessengerApiService, SendMessageRequestModel } from '@app/api/messenger-api.service';
import { Observable, ReplaySubject } from 'rxjs';
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
    return this.login.getLoggedInUser("/message").pipe(
      mergeMap(principal => {
        const senderUser : MessageUser = new MessageUser(principal.id, principal.name, principal.photoId);
        return this.messengerapi.listConversations().pipe(
          mergeMap(conversations => conversations.conversations),
          mergeMap( conversation => this.getMessageUser(conversation.userId).pipe(
            map( receiverUser => new Conversation(
              senderUser,
              receiverUser,
              conversation.messageHistory.map(messageResponse => new Message(messageResponse.sender, messageResponse.receiver, messageResponse.messageText, messageResponse.sentTs)))) 
          )),
          toArray()
          );
      })
    )
    
  }


  getMessageUser(id : number) : Observable<MessageUser> {
    return this.userapi.getUserInfo(id).pipe(
      map( userinfo => new MessageUser(
        userinfo.id,
        userinfo.name,
        userinfo.photoId
      ))
    )
  }

  sendMessage(message : Message) {
    return this.messengerapi.sendMessage(new SendMessageRequestModel(
      message.receiverId,
      message.messageText
    ))
  }

  searchUser(name : string) : Observable<MessageUser[]> {

    return this.login.getLoggedInUser("/message").pipe(
      mergeMap(principal => {
        const uid = principal.id;
        return this.userapi.findUsersByName(name).pipe(
          map(result => result.users.filter( userinfo => userinfo.id != uid)
                                    .map( userinfo => new MessageUser(userinfo.id, userinfo.name, userinfo.photoId)))
        )
      })
    )
  }

  newConversation(otherUser : MessageUser) {
    // TODO: assert no preexisting conversation

    this.login.getLoggedInUser("/message").subscribe(principal => {
      const senderUser = new MessageUser(principal.id, principal.name, principal.photoId);
      const newConversation = new Conversation(senderUser, otherUser, []);
      this.selectedConversation.next(newConversation);
    })
  }

  public selectedConversation = new ReplaySubject<Conversation>(1)

}
export class MessageUser {
  constructor(
    public readonly id : number,
    public readonly name : string,
    public readonly photoId : number
  ) {}

  equals(other : MessageUser) : boolean {
    return other.id === this.id && other.name === this.name && other.photoId === this.photoId;
  }
}
export class Message {
  constructor(
    public readonly senderId : number,
    public readonly receiverId : number,
    public readonly messageText : string,
    public readonly sentTs : number
  ) {}
}
export class Conversation {
  constructor(
    public readonly sender : MessageUser,
    public readonly receiver : MessageUser,
    public readonly history : Message[]
  ) {}

  equals(other : Conversation) : boolean {
    return (this.sender.equals(other.sender) && this.receiver.equals(other.receiver))
            || (this.sender.equals(other.receiver) && this.receiver.equals(other.sender))
  }
}
