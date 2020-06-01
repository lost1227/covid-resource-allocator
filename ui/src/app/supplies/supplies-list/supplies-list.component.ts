import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { SuppliesService } from '@app/supplies/supplies.service';
import { Supply, SupplyType } from '@app/entities/supply';
import { FormGroup, FormControl } from '@angular/forms';
import { SuppliesFilter } from '@app/api/supplies-api.service';
import { User } from '@app/entities/user';
import { LoginManagerService } from '@app/loginmanager.service';

@Component({
  selector: 'app-supplies-list',
  templateUrl: './supplies-list.component.html',
  styleUrls: ['./supplies-list.component.css']
})
export class SuppliesListComponent implements OnInit {

  supplies : Observable<Supply[]>
  user : User
  filterForm : FormGroup

  constructor(
    private api : SuppliesService,
    private loginmanager : LoginManagerService
  ) {
    this.filterForm = new FormGroup({
      'high-need': new FormControl(false),
      'low-need': new FormControl(false),
      'offers': new FormControl(false),
      'requests': new FormControl(false),
      'location': new FormControl(false),
      'search': new FormControl()
    })
    this.loginmanager.isLoggedIn().subscribe(loggedIn => {
      if(loggedIn) {
        this.loginmanager.getLoggedInUser("/volunteer").subscribe(user => {
          this.user = user;
        })
      }
    })
  }

  ngOnInit(): void {
    this.supplies = this.api.listSupplies();
  }

  updateFilters() {
    const value = this.filterForm.value;
    const filter = new SuppliesFilter([], SupplyType.OFFER, -1, "", "");

    if(value['high-need'] && !value['low-need']) {
      filter.enabledFilters.push("need");
      filter.need = 1;
    } else if(!value['high-need'] && value['low-need']) {
      filter.enabledFilters.push("need");
      filter.need = 0;
    }

    else if(value['offers'] && !value['requests']) {
      filter.enabledFilters.push('type');
      filter.type = SupplyType.OFFER;
    } else if(!value['offers'] && value['requests']) {
      filter.enabledFilters.push('type');
      filter.type = SupplyType.REQUEST;
    }

    if(value['location']) {
      filter.enabledFilters.push("location");
      filter.location = this.user.location;
    }

    if(value['search']) {
      filter.enabledFilters.push("search");
      filter.search = value['search'];
    }

    this.supplies = this.api.listSupplies(filter);
  }
}
