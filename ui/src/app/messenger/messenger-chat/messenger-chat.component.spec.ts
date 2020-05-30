import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MessengerChatComponent } from './messenger-chat.component';
import { Observable, Subject, of } from 'rxjs';
import { MessengerService, Conversation, MessageUser } from '@app/messenger/messenger.service';
import { FormBuilder } from '@angular/forms';
import { User } from '@app/entities/user';
import { LoginManagerService } from '@app/loginmanager.service';


describe('MessengerChatComponent', () => {
  let component: MessengerChatComponent;
  let fixture: ComponentFixture<MessengerChatComponent>;

  let mockMessengerService = {
    listConversations() : Observable<Conversation[]> {
      return of([])
    },
    getMessageUser(id : number) : Observable<MessageUser> {
      return of()
    },

    selectedConversation : new Subject<Conversation>()
  }

  let mockloginservice = {
    getLoggedInUser() : Observable<User> {
      return of(new User(0, "Test User", "Test Location", "Test Type", "Test Description", [], -1))
    }
  }

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      providers: [{provide: MessengerService, useValue: mockMessengerService},
                  {provide: LoginManagerService, useValue: mockloginservice},
                  FormBuilder],
      declarations: [ MessengerChatComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MessengerChatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should respond to conversation changes', () => {
    expect(component.currConvo).toBeFalsy();
    const testconvo = new Conversation(
      new MessageUser(1, "User 1", -1),
      new MessageUser(2, "User 2", -1),
      []
    )
    mockMessengerService.selectedConversation.next(testconvo);
    expect(component.currConvo).toBeTruthy();
    expect(component.currConvo).toEqual(testconvo);
  })
});
