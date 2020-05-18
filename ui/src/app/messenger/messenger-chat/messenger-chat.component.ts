import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

import { MessengerService, Conversation, Message } from '@app/messenger/messenger.service';

@Component({
  selector: 'app-messenger-chat',
  templateUrl: './messenger-chat.component.html',
  styleUrls: ['./messenger-chat.component.css']
})
export class MessengerChatComponent implements OnInit {

  currConvo : Conversation

  sendMessageForm : FormGroup

  constructor(
    private messengerService : MessengerService,
    private formBuilder : FormBuilder
  ) {
    this.sendMessageForm = this.formBuilder.group({
      message: ''
    })
  }

  ngOnInit(): void {
    this.messengerService.selectedConversation.subscribe(newConvo => {
      this.currConvo = newConvo
    })
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
