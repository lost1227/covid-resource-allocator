import { Component, OnInit } from '@angular/core';
import { ApiService, VolunteerFilter, VolunteerTask } from '@app/api.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-supplies-list',
  templateUrl: './supplies-list.component.html',
  styleUrls: ['./supplies-list.component.css']
})
export class SuppliesListComponent implements OnInit {

  tasks : Observable<VolunteerTask[]>

  constructor(private api : ApiService) { }

  ngOnInit(): void {
    this.tasks = this.api.getVolunteerTasks(new VolunteerFilter(false, false, -1));
  }
}
