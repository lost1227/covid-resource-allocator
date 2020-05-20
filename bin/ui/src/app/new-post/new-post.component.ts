import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-new-post',
  templateUrl: './new-post.component.html',
  styleUrls: ['./new-post.component.css']
})
export class NewPostComponent implements OnInit {

  public Options = ["Request", "Resource"]
  @Input('selected') selectedOption : string;
  constructor() { }

  ngOnInit(): void {
  }

  public getOptionClass(option : string) : string[] {
    if(option == this.selectedOption) {
      return ["nav-button", "nav-button-selected"]
    } else {
      return ["nav-button"]
    }
  }

}
