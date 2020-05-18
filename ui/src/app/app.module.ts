import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from '@app/app-routing.module';
import { AppComponent } from '@app/app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToolbarComponent } from '@app/toolbar/toolbar.component';
//import { FilterToolsComponent } from '@app/filter-tools/filter-tools.component';
import { HttpClientModule } from '@angular/common/http';

import { MatFormFieldModule } from '@angular/material/form-field';
//import { MatPaginator } from '@angular/material/paginator'
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { TaskListComponent } from '@app/volunteer/task-list/task-list.component';
import { TaskItemComponent } from '@app/volunteer/task-item/task-item.component';
import { FilterToolsComponent } from '@app/toolbar/filter-tools/filter-tools.component';
import { MatMenuModule} from '@angular/material/menu';
import { MatCardModule } from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    TaskListComponent,
    TaskItemComponent,
    FilterToolsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatCardModule,
    MatToolbarModule,
    MatButtonModule,
    MatCheckboxModule,
    MatIconModule,
    HttpClientModule,
    MatMenuModule,
    MatInputModule
    //MatPaginator
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
