import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from '@app/app-routing.module';
import { AppComponent } from '@app/app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToolbarComponent } from '@app/toolbar/toolbar.component';
<<<<<<< HEAD
//import { FilterToolsComponent } from '@app/filter-tools/filter-tools.component';
import { HttpClientModule } from '@angular/common/http';
=======
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
>>>>>>> b7689e846b798a4a2c7f86e33c73a16212f6739d

import { MatFormFieldModule } from '@angular/material/form-field';
//import { MatPaginator } from '@angular/material/paginator'
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
<<<<<<< HEAD
<<<<<<< HEAD
=======
import { MatSidenavModule } from '@angular/material/sidenav';
import {MatMenuModule} from '@angular/material/menu';
import {MatCheckboxModule} from '@angular/material/checkbox';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';

>>>>>>> 788c63d... Updated ViewTasks
import { TaskListComponent } from '@app/volunteer/task-list/task-list.component';
import { TaskItemComponent } from '@app/volunteer/task-item/task-item.component';
import { FilterToolsComponent } from '@app/toolbar/filter-tools/filter-tools.component';
import { MatMenuModule} from '@angular/material/menu';
import { MatCardModule } from '@angular/material/card';
import {MatInputModule} from '@angular/material/input';
=======
import { MatSidenavModule } from '@angular/material/sidenav';

import { TaskListComponent } from '@app/volunteer/task-list/task-list.component';
import { TaskItemComponent } from '@app/volunteer/task-item/task-item.component';
import { MessengerComponent } from '@app/messenger/messenger.component';
import { MessengerUserComponent } from '@app/messenger/messenger-user/messenger-user.component';
import { MessengerChatComponent } from '@app/messenger/messenger-chat/messenger-chat.component';
import { MessengerNewConversationComponent } from '@app/messenger/messenger-new-conversation/messenger-new-conversation.component';
import { LoginComponent } from './login/login.component';
import { XhrInterceptor } from './xhr-interceptor';
import { SuppliesListComponent } from '@app/supplies/supplies-list/supplies-list.component';
import { SupplyComponent } from './supplies/supply/supply.component';
import { NewPostComponent } from './new-post/new-post.component';
import { TestComponent } from './post-new-resource/test.component';
>>>>>>> b7689e846b798a4a2c7f86e33c73a16212f6739d

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    TaskListComponent,
    TaskItemComponent,
<<<<<<< HEAD
    FilterToolsComponent
=======
    MessengerComponent,
    MessengerUserComponent,
    MessengerChatComponent,
    MessengerNewConversationComponent,
    LoginComponent,
    SuppliesListComponent,
    SupplyComponent,
    NewPostComponent,
    TestComponent
>>>>>>> b7689e846b798a4a2c7f86e33c73a16212f6739d
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
<<<<<<< HEAD
    MatFormFieldModule,
    MatCardModule,
=======
    MatMenuModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatInputModule,
>>>>>>> 788c63d... Updated ViewTasks
    MatToolbarModule,
    MatButtonModule,
    MatCheckboxModule,
    MatIconModule,
<<<<<<< HEAD
    HttpClientModule,
    MatMenuModule,
    MatInputModule
    //MatPaginator
=======
    MatSidenavModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
>>>>>>> b7689e846b798a4a2c7f86e33c73a16212f6739d
  ],
  providers: [ {provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true} ],
  bootstrap: [AppComponent]
})
export class AppModule { }