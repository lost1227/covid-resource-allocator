import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TaskListComponent } from '@app/tasks/task-list/task-list.component';
import { MessengerComponent } from '@app/messenger/messenger.component';
import { LoginComponent } from '@app/login/login.component';

import { NewPostComponent } from './new-post/new-post.component';
import { SuppliesListComponent } from './supplies/supplies-list/supplies-list.component';
import { TestComponent} from './post-new-resource/test.component';


const routes: Routes = [
  { path: 'volunteer', component: TaskListComponent},
  { path: 'message', component: MessengerComponent},
  { path: 'login', component: LoginComponent },
  { path: 'post', component: NewPostComponent},
  { path: 'test', component: TestComponent},
  { path: 'supplies', component: SuppliesListComponent},
  { path: '', redirectTo: '/volunteer', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
