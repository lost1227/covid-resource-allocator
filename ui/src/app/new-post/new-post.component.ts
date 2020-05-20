import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '@app/entities/user';
import { LoginManagerService } from '@app/loginmanager.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

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
    })
    
    this.login.getLoggedInUser().subscribe(user => {
      this.user = user;
    })

    this.form = formBuilder.group({
      'title' : '',
      'description' : '',
      'location' : '',
      'need' : '',
      'supplyPostType' : ''
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
