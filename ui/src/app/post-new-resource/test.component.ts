import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-test',
  templateUrl: './test.component.html',
  styleUrls: ['./test.component.css']
})
export class TestComponent implements OnInit {
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
