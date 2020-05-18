import { Injectable } from '@angular/core';
import { LoginManagerService } from '@app/loginmanager.service';
import { UserinfoApiService } from '@app/api/userinfo-api.service';
import { MessengerApiService, SendMessageRequestModel } from '@app/api/messenger-api.service';
import { Observable, Subject, combineLatest, of, throwError, empty } from 'rxjs';
import { toArray, mergeMap, map, catchError } from 'rxjs/operators';
import { User } from '@app/entities/User';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class MessengerService {

  constructor(
    private login : LoginManagerService,
    private userapi : UserinfoApiService,
    private messengerapi : MessengerApiService) { }

  listConversations() : Observable<Conversation[]> {
    return this.login.getLoggedInUser().pipe(
      mergeMap(principal => {
        const senderUser : MessageUser = new MessageUser(principal.id, principal.name);
        return this.messengerapi.listConversations().pipe(
          mergeMap(conversations => conversations.conversations),
          mergeMap( conversation => this.getMessageUser(conversation.userId).pipe(
            map( receiverUser => new Conversation(senderUser, receiverUser, null)) 
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
        userinfo.name
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

    return this.login.getLoggedInUser().pipe(
      mergeMap(principal => {
        const uid = principal.id;
        return this.userapi.findUsersByName(name).pipe(
          map(result => result.users.filter( userinfo => userinfo.id != uid)
                                    .map( userinfo => new MessageUser(userinfo.id, userinfo.name)))
        )
      })
    )
  }

  newConversation(otherUser : MessageUser) {
    // TODO: assert no preexisting conversation

    this.login.getLoggedInUser().subscribe(principal => {
      const senderUser = new MessageUser(principal.id, principal.name);
      const newConversation = new Conversation(senderUser, otherUser, []);
      this.selectedConversation.next(newConversation);
    })
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
