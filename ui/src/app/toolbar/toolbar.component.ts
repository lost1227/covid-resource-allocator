import { Component, OnInit, Input } from '@angular/core';
<<<<<<< HEAD
=======
import { VolunteerFilter } from '@app/api.service';
>>>>>>> 788c63d... Updated ViewTasks


@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {
  user = {
    name: "Ryan Madamba"
  }
  text : String = null;
<<<<<<< HEAD

  public Options = ["Volunteers", "Supplies"]
=======
  need : boolean = false;
  match : boolean = false;
  filter : VolunteerFilter = null;


  

  public options : NavOption[] = [new NavOption("Volunteer", "/volunteer"),
                                  new NavOption("Supplies", "/supplies"),
                                  new NavOption("New Post", "/post")];
>>>>>>> 788c63d... Updated ViewTasks
  @Input('selected') selectedOption : string;

  constructor() { }

  ngOnInit(): void {
  }

  public getOptionClass(option : string) : string[] {
    if(option == this.selectedOption) {
      return ["nav-button", "nav-button-selected"]
    } 
    else {
      return ["nav-button"]
    }
  }
<<<<<<< HEAD
=======
  
}
>>>>>>> 788c63d... Updated ViewTasks

  value = '...';
}
