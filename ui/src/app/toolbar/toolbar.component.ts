import { Component, OnInit, Input } from '@angular/core';


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

}
