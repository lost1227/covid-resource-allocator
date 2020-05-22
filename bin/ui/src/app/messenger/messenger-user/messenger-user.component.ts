import { Component, OnInit, Input } from '@angular/core';
import { MessengerService, Conversation } from '../messenger.service';

@Component({
  selector: 'app-messenger-user',
  templateUrl: './messenger-user.component.html',
  styleUrls: ['./messenger-user.component.css']
})
export class MessengerUserComponent implements OnInit {

  @Input() conversation : Conversation

  constructor(
    private messengerService : MessengerService
  ) { }

  ngOnInit(): void {
  }

  buttonClicked() {
    this.messengerService.selectedConversation.next(this.conversation)
  }

}
