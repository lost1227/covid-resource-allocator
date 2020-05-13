import { Component, OnInit, Input, ElementRef,ViewChild } from '@angular/core';


@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {
  user = {
    name: "Jordan Powers"
  }
  public Options = ["Volunteers", "Supplies"]
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
