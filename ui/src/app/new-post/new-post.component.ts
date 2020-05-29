import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '@app/entities/user';
import { LoginManagerService } from '@app/loginmanager.service';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import {Supply, SupplyType} from '@app/entities/supply'
import {SuppliesApiService, PostSupplyRequestModel} from '@app/api/supplies-api.service'
import { SupplyRequestModel } from '@app/api/post-api.service';
import { PostService, PostType } from './post.service';
import { VolunteerTask } from '@app/entities/volunteer-task';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  user : User
  supplyType : SupplyType
  type : PostType
  form : FormGroup
  
  constructor(
    private route : ActivatedRoute,
    private formBuilder : FormBuilder,
    private postService : PostService
  ) {
    this.route.url.subscribe(params => {
      this.type = <PostType> params[0].path;
      this.postService.login(this.type).subscribe(user => {
        this.user = user;
      })
      if(this.type == PostType.SUPPLY) {
        this.form = new FormGroup({
          'title' : new FormControl('', Validators.required),
          'image' : new FormControl(null, Validators.required),
          'description' : new FormControl('', Validators.required),
          'location' : new FormControl('', Validators.required),
          'need' : new FormControl('', Validators.required),
          'supplyPostType' : new FormControl('', Validators.required),
          'quantity' : new FormControl('', [Validators.required, Validators.pattern("[0-9]*")])
        })
      } else {
        this.form = new FormGroup({
          'title' : new FormControl('', Validators.required),
          'image' : new FormControl(null, Validators.required),
          'description' : new FormControl('', Validators.required),
          'location' : new FormControl('', Validators.required),
          'need' : new FormControl('', Validators.required)
        })
      }
      
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

    if(this.form.status == "VALID") {
      switch(this.type) {
        case PostType.SUPPLY:
          const supply = new Supply(
            -1,
            formValue.title,
            formValue.location,
            parseInt(formValue.need),
            formValue.description,
            this.user.id,
            formValue.supplyPostType,
            formValue.quantity,
            -1
          )
          this.postService.postSupply(supply, formValue.image);
          break;
        case PostType.VOLUNTEER:
          const task = new VolunteerTask(
            -1,
            formValue.title,
            formValue.location,
            parseInt(formValue.need),
            formValue.description,
            this.user.id,
            -1
          )
          this.postService.postTask(task, formValue.image);
          break;
        default:
          break;
      }
      
      
    }
  }

}
