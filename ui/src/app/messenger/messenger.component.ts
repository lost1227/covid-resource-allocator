import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { MessengerService, Conversation } from './messenger.service';


@Component({
  selector: 'app-messenger',
  templateUrl: './messenger.component.html',
  styleUrls: ['./messenger.component.css']
})
export class MessengerComponent implements OnInit {

  conversations : Conversation[]

  constructor(
    private messengerService : MessengerService
  ) {
    messengerService.listConversations().subscribe(conversations => {
      this.conversations = conversations;
    })
    messengerService.selectedConversation.subscribe(conversation => {
      if(!this.conversations.includes(conversation)) {
        this.conversations.push(conversation);
      }
    })
  }

  ngOnInit(): void {
  }

}
