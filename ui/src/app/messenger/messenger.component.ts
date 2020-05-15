import { Component, OnInit } from '@angular/core';
import { MessengerService, Conversation } from './messenger.service';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-messenger',
  templateUrl: './messenger.component.html',
  styleUrls: ['./messenger.component.css']
})
export class MessengerComponent implements OnInit {

  conversations : Observable<Conversation[]>

  constructor(
    private messengerService : MessengerService
  ) {
    this.conversations = messengerService.listConversations()
  }

  ngOnInit(): void {
  }

}
