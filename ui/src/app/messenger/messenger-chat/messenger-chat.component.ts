import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { MessengerService, Conversation, Message } from '@app/messenger/messenger.service';
import { LoginManagerService } from '@app/loginmanager.service';
import { User } from '@app/entities/user';

@Component({
  selector: 'app-messenger-chat',
  templateUrl: './messenger-chat.component.html',
  styleUrls: ['./messenger-chat.component.css']
})
export class MessengerChatComponent implements OnInit {

  currConvo : Conversation

  sendMessageForm : FormGroup

  user : User

  constructor(
    private loginService : LoginManagerService,
    private messengerService : MessengerService,
    private formBuilder : FormBuilder
  ) {
    this.sendMessageForm = this.formBuilder.group({
      message: ''
    })
    this.loginService.getLoggedInUser("/message").subscribe(principal => {
      this.user = principal;
    })
  }

  ngOnInit(): void {
    this.messengerService.selectedConversation.subscribe(newConvo => {
      this.currConvo = newConvo
    })
  }

  getMessageOuterClass(message : Message) : string[] {
    if(message.senderId == this.user.id) {
      return ["message-outer", "sent-message-outer"]
    } else {
      return ["message-outer", "received-message-outer"]
    }
  }
  getMessageInnerClass(message : Message) : string[] {
    if(message.senderId == this.user.id) {
      return ["message-inner", "sent-message-inner"]
    } else {
      return ["message-inner", "received-message-inner"]
    }
  }

  onKeypress(event) {
    if(event.keyCode === 13) {
      this.onSend(this.sendMessageForm.value);
      return false;
    } else {
      return true;
    }
  }

  onSend(messageData) {
    this.sendMessageForm.reset();
    const message = new Message(
      this.currConvo.sender.id,
      this.currConvo.receiver.id,
      messageData.message,
      new Date().getTime()
    )
    this.messengerService.sendMessage(message).subscribe( response => {
      this.currConvo.history.push(message)
    })
  }

}
