import { Component, OnInit } from '@angular/core';
import { MessengerService, Conversation } from './messenger.service';


@Component({
  selector: 'app-messenger',
  templateUrl: './messenger.component.html',
  styleUrls: ['./messenger.component.css']
})
export class MessengerComponent implements OnInit {

  conversations : Conversation[] = []

  constructor(
    private messengerService : MessengerService
  ) {
    messengerService.listConversations().subscribe(conversations => {
      this.conversations = conversations;
    })
    messengerService.selectedConversation.subscribe(conversation => {
      if(conversation) {
        const alreadyInConversations = this.conversations.reduce(
          (accumulator, currentValue) => accumulator || currentValue.equals(conversation),
          false);
        if(!alreadyInConversations) {
          this.conversations.push(conversation);
        }
      }
    })
  }

  ngOnInit(): void {
    this.messengerService.selectedConversation.next(null);
  }

}
