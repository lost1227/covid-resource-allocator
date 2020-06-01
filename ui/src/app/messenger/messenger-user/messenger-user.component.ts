import { Component, OnInit, Input } from '@angular/core';
import { MessengerService, Conversation } from '../messenger.service';

@Component({
  selector: 'app-messenger-user',
  templateUrl: './messenger-user.component.html',
  styleUrls: ['./messenger-user.component.css']
})
export class MessengerUserComponent implements OnInit {

  @Input() conversation : Conversation

  private currConvo : Conversation

  constructor(
    private messengerService : MessengerService
  ) { }

  ngOnInit(): void {
    this.messengerService.selectedConversation.subscribe(convo => {
      this.currConvo = convo;
    })

  }

  buttonClicked() {
    this.messengerService.selectedConversation.next(this.conversation)
  }

  getClass() : string[] {
    if(this.currConvo && this.conversation.equals(this.currConvo)) {
      return ["maindiv", "active"]
    } else {
      return ["maindiv"]
    }
  }

}
