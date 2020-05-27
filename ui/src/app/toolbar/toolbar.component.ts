<<<<<<< HEAD
import { Component, OnInit, Input } from '@angular/core';
<<<<<<< HEAD
=======
import { VolunteerFilter } from '@app/api.service';
>>>>>>> 788c63d... Updated ViewTasks
=======
import { Component, OnInit, Input} from '@angular/core';
>>>>>>> b7689e846b798a4a2c7f86e33c73a16212f6739d


@Component({
  selector: 'app-toolbar',
  templateUrl: './toolbar.component.html',
  styleUrls: ['./toolbar.component.css']
})
export class ToolbarComponent implements OnInit {
  user = {
<<<<<<< HEAD
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
=======
    name: "Raymond Lenz"
  }
  public options : NavOption[] = [new NavOption("Volunteer", "/volunteer"),
                                  new NavOption("Supplies", "/supplies"),
                                  new NavOption("New Post", "/post")];
>>>>>>> b7689e846b798a4a2c7f86e33c73a16212f6739d
  @Input('selected') selectedOption : string;

  constructor() { }

  ngOnInit(): void {
  }

  public getOptionClass(option : NavOption) : string[] {
    if(option.label == this.selectedOption) {
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

class NavOption {
  constructor(
    public label : string,
    public url : string
  ) {}
}
