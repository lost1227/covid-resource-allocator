import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '@app/entities/user';
import { LoginManagerService } from '@app/loginmanager.service';
import { FormBuilder, FormGroup } from '@angular/forms';
import {Supply, SupplyType} from '@app/entities/supply'
import {SuppliesApiService, PostSupplyRequestModel} from '@app/api/supplies-api.service'
import { SupplyRequestModel } from '@app/api/post-api.service';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  user : User
  supplyType : SupplyType
  type : string
  form : FormGroup
  
  constructor(
    private route : ActivatedRoute,
    private formBuilder : FormBuilder,
    private login : LoginManagerService,
    private supplyService : SuppliesApiService
  ) {
    this.route.url.subscribe(params => {
      this.login.getLoggedInUser("/post/supply").subscribe(user => {
        this.user = user;
      })
    })
    
    

    this.form = formBuilder.group({
      'title' : '',
      'description' : '',
      'location' : '',
      'need' : '',
      'supplyPostType' : '',
      'quantity' : ''
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
    //package supply in supply entity  and send to supply api 
    this.form.reset();
    const supply = new PostSupplyRequestModel(
      formValue.title,
      formValue.location,
      parseInt(formValue.need),
      formValue.description,
      this.user.id,
      this.supplyType,
      0
    )
    this.supplyService.postSupplies(supply).subscribe()
  }

}
