import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { MessengerApiService, SendMessageRequestModel, ListConversationsResponse } from './messenger-api.service';

describe('MessengerApiService', () => {
  let injector: TestBed;
  let httpMock: HttpTestingController;
  let service: MessengerApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [MessengerApiService]
    });
    injector = getTestBed();
    service = TestBed.inject(MessengerApiService);
    httpMock = injector.get(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  })

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return an Observable<ResponseModel>', () => {
    const val = {
      "ok": true
    }

    const message = new SendMessageRequestModel(1, "test message");

    service.sendMessage(message).subscribe(result => {
      expect(result).toEqual(val);
    });

    const req = httpMock.expectOne("/api/message/post");
    expect(req.request.method).toBe("POST");
    expect(req.request.body).toEqual(message);
    req.flush(val);
  });

  it('should return a list of conversations', () => {
    const ret : ListConversationsResponse = {
      ok : true,
      userId : 2,
      conversations: [
        {
          userId : 0,
          messageHistory: []
        }, {
          userId : 1,
          messageHistory: []
        }
      ]
    }

    service.listConversations().subscribe(result => {
      expect(result).toEqual(ret);
    });

    const req = httpMock.expectOne("/api/message/conversations");
    expect(req.request.method).toBe("GET");
    req.flush(ret);
  })
});
