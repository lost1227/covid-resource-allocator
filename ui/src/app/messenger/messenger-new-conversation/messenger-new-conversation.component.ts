import { Component, OnInit } from '@angular/core';
import { MessengerService, MessageUser} from '@app/messenger/messenger.service';

@Component({
  selector: 'app-messenger-new-conversation',
  templateUrl: './messenger-new-conversation.component.html',
  styleUrls: ['./messenger-new-conversation.component.css']
})
export class MessengerNewConversationComponent implements OnInit {

  users : MessageUser[] = [];

  constructor(
    private messenger : MessengerService
  ) { }

  ngOnInit(): void {
  }

  onInput(e : any) {
    const query : string = e.target.value;
    if(query.length > 0) {
      this.messenger.searchUser(query).subscribe(users => {
        this.users = users;
      });
    } else {
      this.users = [];
    }
    
  }

  onClick(user : MessageUser) {
    this.messenger.newConversation(user);
  }

}
