import { Component, OnInit, Input } from '@angular/core';
import { Supply, SupplyType } from '@app/entities/supply';

@Component({
  selector: 'app-supply',
  templateUrl: './supply.component.html',
  styleUrls: ['./supply.component.css']
})
export class SupplyComponent implements OnInit {

  @Input() supply : Supply

  constructor() { }

  ngOnInit(): void {
  }

  getNeedClass() : string[] {
    switch(this.supply.need) {
      case 0:
        return ["supply-need", "supply-need-low"]
      case 1:
        return ["supply-need", "supply-need-high"]
      default:
        return []
    }
  }

  getNeedDesc() : string {
    switch(this.supply.need) {
      case 0:
        return "Low need"
      case 1:
        return "High need"
      default:
        return ""
    }
  }

  getTypeClass() : string[] {
    switch(this.supply.type) {
      case SupplyType.REQUEST:
        return ["supply-type", "supply-type-request"];
      case SupplyType.OFFER:
        return ["supply-type", "supply-type-offer"];
      default:
        return [];
    }
  }

  getTypeDesc() : string {
    switch(this.supply.type) {
      case SupplyType.REQUEST:
        return "Request";
      case SupplyType.OFFER:
        return "Offer";
      default:
        return "";
    }
  }

}
