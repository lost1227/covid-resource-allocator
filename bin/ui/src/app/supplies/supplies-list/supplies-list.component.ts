import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { SuppliesService } from '@app/supplies/supplies.service';
import { Supply } from '@app/entities/supply';

@Component({
  selector: 'app-supplies-list',
  templateUrl: './supplies-list.component.html',
  styleUrls: ['./supplies-list.component.css']
})
export class SuppliesListComponent implements OnInit {

  supplies : Observable<Supply[]>

  constructor(private api : SuppliesService) { }

  ngOnInit(): void {
    this.supplies = this.api.listSupplies();
  }
}
