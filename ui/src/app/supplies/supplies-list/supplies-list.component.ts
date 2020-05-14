import { Component, OnInit } from '@angular/core';
import { ApiService, SupplyFilter, Supply } from '@app/api.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-supplies-list',
  templateUrl: './supplies-list.component.html',
  styleUrls: ['./supplies-list.component.css']
})
export class SuppliesListComponent implements OnInit {

  supplies : Observable<Supply[]>

  constructor(private api : ApiService) { }

  ngOnInit(): void {
    this.supplies = this.api.getVolunteerTasks(new SupplyFilter(false, false, -1));
  }
}
