import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TaskListComponent } from './volunteer/task-list/task-list.component';


const routes: Routes = [
  { path: 'volunteer', component: TaskListComponent},
  { path: '', redirectTo: '/volunteer', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
