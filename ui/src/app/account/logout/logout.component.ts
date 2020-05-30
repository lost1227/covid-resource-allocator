import { Component, OnInit } from '@angular/core';
import { LoginManagerService } from '@app/loginmanager.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css']
})
export class LogoutComponent implements OnInit {

  constructor(
    private login : LoginManagerService
  ) { }

  ngOnInit(): void {
    this.login.logout("/");
  }

}
