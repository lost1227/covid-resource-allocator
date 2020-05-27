import { Component, OnInit, Input } from '@angular/core';


@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {
  user = {

    name: "Ryan Madamba"
  }

  public options : NavOption[] = [new NavOption("Volunteer", "/volunteer"),
                                  new NavOption("Supplies", "/supplies"),
                                  new NavOption("New Post", "/post")];

  @Input('selected') selectedOption : string;

  constructor() { }

  ngOnInit(): void {
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
