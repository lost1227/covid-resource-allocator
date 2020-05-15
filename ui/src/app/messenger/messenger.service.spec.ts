import { TestBed } from '@angular/core/testing';

import { MessengerService } from './messenger.service';
import { UserInfoResponseModel, UserinfoApiService } from '@app/api/userinfo-api.service';
import { Observable, of } from 'rxjs';
import { SendMessageRequestModel, ListConversationsRequestModel, ListConversationsResponse, MessengerApiService } from '@app/api/messenger-api.service';
import { ResponseModel } from '@app/api/api.service.ts';
import { LoginManagerService } from '@app/loginmanager.service';

describe('MessengerService', () => {
  let service: MessengerService;

  let mockuserapi = {
    getUserInfo(id : number) : Observable<UserInfoResponseModel> {
      return of({
        ok: true,
        id: id,
        name: "User "+id,
        location : "someplace",
        userType : "volunteer",
        description : "",
        skillset : "unskilled"
      })
    }
  }

  let mockmessengerapi = {
    sendMessage(message : SendMessageRequestModel) : Observable<ResponseModel> {
      return of({ok : true})
    },
    listConversations(request : ListConversationsRequestModel) : Observable<ListConversationsResponse> {
      return of({
        ok: true,
        userId: 0,
        conversations: [{
          userId: 1
        }, {
          userId : 2
        }]
      })
    }
  }

  let mockloginservice = {
    getLoggedInUserId() : number {
      return 0
    },
    getAuthToken() : string {
      return "abc123";
    }
  }

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        {provide: UserinfoApiService, useValue: mockuserapi},
        {provide: MessengerApiService, useValue: mockmessengerapi},
        {provide: LoginManagerService, useValue: mockloginservice}
      ]
    });
    service = TestBed.inject(MessengerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should get users', () => {
    service.getMessageUser(0).subscribe(user => {
      expect(user.id).toBe(0)
      expect(user.name).toBe("User 0")
    })

    service.getMessageUser(1).subscribe(user => {
      expect(user.id).toBe(1)
      expect(user.name).toBe("User 1")
    })
  })

  it('should get conversations', () => {
    service.listConversations().subscribe(conversations => {
      expect(conversations).toBeTruthy();
      expect(conversations.length).toBe(2);
      expect(conversations[0].sender.id).toBe(0)
      expect(conversations[0].receiver.id).toBe(1)
      expect(conversations[1].sender.id).toBe(0)
      expect(conversations[1].receiver.id).toBe(2);
    })
  })
});
