import { Component, OnInit, Input } from '@angular/core';
import { VolunteerTask } from '@app/api.service'

@Component({
  selector: 'app-task-item',
  templateUrl: './task-item.component.html',
  styleUrls: ['./task-item.component.css']
})
export class TaskItemComponent implements OnInit {

  @Input() task : VolunteerTask

  constructor() { }

  ngOnInit(): void {
  }

  getClass() : string[] {
    switch(this.task.need) {
      case 0:
        return ["task-need", "task-need-low"]
      case 1:
        return ["task-need", "task-need-high"]
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
