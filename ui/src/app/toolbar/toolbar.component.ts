import { Component, OnInit, Input} from '@angular/core';
import { LoginManagerService } from '@app/loginmanager.service';
import { User } from '@app/entities/user';


@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {

  public options : NavOption[] = [new NavOption("Volunteer", "/volunteer"),
                                  new NavOption("Supplies", "/supplies")];
  @Input('selected') selectedOption : string;

  public user : User

  public menuOptions : NavOption[] = [
    new NavOption("Login", "/login")
  ]

  constructor(
    private login : LoginManagerService
  ) { }

  ngOnInit(): void {

    this.login.isLoggedIn().subscribe(isLoggedIn => {
      if(isLoggedIn) {
        this.login.getLoggedInUser().subscribe(user => {
          this.user = user;
          this.menuOptions = [
            new NavOption("Messages", "/message"),
            new NavOption("Profile", "/profile/"+this.user.id),
            new NavOption("Logout", "/profile/logout")
          ]
        })
      }
    })
  }

  public getOptionClass(option : NavOption) : string[] {
    if(option.label == this.selectedOption) {
      return ["nav-button", "nav-button-selected"]
    } 
    else {
      return ["nav-button"]
    }
  }

}
class NavOption {
  constructor(
    public label : string,
    public url : string
  ) {}
}
