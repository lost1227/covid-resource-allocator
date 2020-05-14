import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from '@app/app-routing.module';
import { AppComponent } from '@app/app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToolbarComponent } from '@app/toolbar/toolbar.component';
import { HttpClientModule } from '@angular/common/http';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { TaskListComponent } from '@app/volunteer/task-list/task-list.component';
import { TaskItemComponent } from '@app/volunteer/task-item/task-item.component';
import { SuppliesListComponent } from '@app/supplies/supplies-list/supplies-list.component';
import { SupplyComponent } from './supplies/supply/supply.component';
import { NewPostComponent } from './new-post/new-post.component';

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    TaskListComponent,
    TaskItemComponent,
    SuppliesListComponent,
    SupplyComponent,
    NewPostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
