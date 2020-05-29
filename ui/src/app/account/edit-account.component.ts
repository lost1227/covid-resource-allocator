import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '@app/entities/user';
import { LoginManagerService } from '@app/loginmanager.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class EditAccountComponent implements OnInit {

  user : User

  type : String

  form : FormGroup

  constructor(
    private route : ActivatedRoute,
    private formBuilder : FormBuilder,
    private login : LoginManagerService
  ) {
    this.route.url.subscribe(params => {
      this.type = params[0].path;
      this.login.getLoggedInUser("/post/"+this.type).subscribe(user => {
        this.user = user;
      })
    })



    this.form = formBuilder.group({
      'name' : '',
      'description' : '',
      'location' : '',
      /*'need' : '',
      'supplyPostType' : ''*/
    })
  }

  getSelectedType() {
    switch(this.type) {
      case "volunteer":
        return "Volunteer";
      case "supply":
        return "Supplies";
      default:
        return "";
    }
  }

  ngOnInit(): void {
  }

  onSubmit(formValue : any) {
    console.log(formValue);

    // TODO: add the new item to the requisite database
  }

}
