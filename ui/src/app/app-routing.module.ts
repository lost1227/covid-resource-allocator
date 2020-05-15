import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TaskListComponent } from '@app/volunteer/task-list/task-list.component';
import { MessengerComponent } from '@app/messenger/messenger.component';


const routes: Routes = [
  { path: 'volunteer', component: TaskListComponent},
  { path: 'message', component: MessengerComponent},
  { path: '', redirectTo: '/volunteer', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
