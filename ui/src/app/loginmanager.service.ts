import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginManagerService {

  constructor() { }

  public getAuthToken() : string {
    return "abc123";
  }

  public getLoggedInUserId() : number {
    return 5;
  }
}
