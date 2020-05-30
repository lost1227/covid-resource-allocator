import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TaskListComponent } from '@app/tasks/task-list/task-list.component';
import { MessengerComponent } from '@app/messenger/messenger.component';
import { LoginComponent } from '@app/login/login.component';

import { NewPostComponent } from './new-post/new-post.component';
import { SuppliesListComponent } from './supplies/supplies-list/supplies-list.component';
import { SupplyDetailsComponent } from './supplies/supply-details/supply-details.component';
import { RegisterComponent } from './account/register/register.component';
import { EditAccountComponent } from "@app/account/edit-account/edit-account.component";
import { TaskDetailsComponent } from './tasks/task-details/task-details.component';
import { ProfileComponent } from './account/profile/profile.component';
import { LogoutComponent } from './account/logout/logout.component';


const routes: Routes = [
  { path: 'volunteer', component: TaskListComponent },
  { path: 'supplies', component: SuppliesListComponent },
  { path: 'supplies/details/:id', component: SupplyDetailsComponent },
  { path: 'volunteer/details/:id', component: TaskDetailsComponent },
  { path: 'message', component: MessengerComponent},
  { path: 'login', component: LoginComponent },
  { path: 'login/register', component: RegisterComponent },
  { path: 'profile/logout', component: LogoutComponent },
  { path: 'profile/edit', component: EditAccountComponent },
  { path: 'profile/:id', component: ProfileComponent },
  { path: 'post', 
      children: [
        { path: 'volunteer', component: NewPostComponent },
        { path: 'supply', component: NewPostComponent }
      ]},
  { path: '', redirectTo: '/volunteer', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
