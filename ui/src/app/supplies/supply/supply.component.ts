import { Component, OnInit, Input } from '@angular/core';
import { Supply } from '@app/api.service'

@Component({
  selector: 'app-supply',
  templateUrl: './supply.component.html',
  styleUrls: ['./supply.component.css']
})
export class SupplyComponent implements OnInit {

  @Input() task : Supply

  constructor() { }

  ngOnInit(): void {
  }

  getClass() : string[] {
    switch(this.task.need) {
      case 0:
        return ["supply-need", "supply-need-low"]
      case 1:
        return ["supply-need", "supply-need-high"]
      default:
        return []
    }
  }

  getNeedDesc() : string {
    switch(this.task.need) {
      case 0:
        return "Low need"
      case 1:
        return "High need"
      default:
        return ""
    }
  }

}
