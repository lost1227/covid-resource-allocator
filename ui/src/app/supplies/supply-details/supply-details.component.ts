import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { User } from '@app/entities/user';
import { Supply, SupplyType } from '@app/entities/supply';
import { SuppliesService } from '../supplies.service';
import { UserinfoApiService } from '@app/api/userinfo-api.service';

@Component({
  selector: 'app-supply-details',
  templateUrl: './supply-details.component.html',
  styleUrls: ['./supply-details.component.css']
})
export class SupplyDetailsComponent implements OnInit {

  user : User
  item : Supply

  constructor(
    private route : ActivatedRoute,
    private suppliesService : SuppliesService,
    private usersService : UserinfoApiService
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe( params => {
      const id = Number(params.get('id'));
      this.suppliesService.getSupply(id).subscribe(supply => {
        this.item = supply;
        this.usersService.getUserInfo(this.item.ownerId).subscribe(response => {
          this.user = new User(response.id, response.name, response.location, response.userType, response.description, response.skillset);
        })
      })
    }
    )
  }

  getTypeClass() : string[] {
    if(!this.item) {
      return [];
    }

    switch(this.item.type) {
      case SupplyType.OFFER:
        return ["supply-type", "supply-type-offer"]
      case SupplyType.REQUEST:
        return ["supply-type", "supply-type-request"]
      default:
        return []
    }
  }

  getTypeText() : string {
    if(!this.item) {
      return "";
    }

    switch(this.item.type) {
      case SupplyType.OFFER:
        return "Offer"
      case SupplyType.REQUEST:
        return "Request"
      default:
        return ""
    }
  }

  getNeedClass() : string[] {
    if(!this.item) {
      return []
    }

    switch(this.item.need) {
      case 0:
        return ["supply-need", "supply-need-low"]
      case 1:
        return ["supply-need", "supply-need-high"]
      default:
        return []
    }
  }

  getNeedText() : string {
    if(!this.item) {
      return ""
    }

    switch(this.item.need) {
      case 0:
        return "Low need"
      case 1:
        return "High need"
      default:
        return ""
    }
  }

}
