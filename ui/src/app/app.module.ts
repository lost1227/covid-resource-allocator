import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from '@app/app-routing.module';
import { AppComponent } from '@app/app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToolbarComponent } from '@app/toolbar/toolbar.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MatFormFieldModule } from '@angular/material/form-field';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatInputModule } from '@angular/material/input';
import { MatSidenavModule } from '@angular/material/sidenav';

import { TaskListComponent } from '@app/tasks/task-list/task-list.component';
import { TaskItemComponent } from '@app/tasks/task-item/task-item.component';
import { MessengerComponent } from '@app/messenger/messenger.component';
import { MessengerUserComponent } from '@app/messenger/messenger-user/messenger-user.component';
import { MessengerChatComponent } from '@app/messenger/messenger-chat/messenger-chat.component';
import { MessengerNewConversationComponent } from '@app/messenger/messenger-new-conversation/messenger-new-conversation.component';
import { LoginComponent } from './login/login.component';
import { XhrInterceptor } from './xhr-interceptor';
import { SuppliesListComponent } from '@app/supplies/supplies-list/supplies-list.component';
import { SupplyComponent } from './supplies/supply/supply.component';
import { NewPostComponent } from './new-post/new-post.component';
import { RegisterComponent } from './register/register.component';
import { ImageUploadComponentComponent } from './image-upload-component/image-upload-component.component';

import { EditAccountComponent} from "@app/account/edit-account.component";

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    TaskListComponent,
    TaskItemComponent,
    MessengerComponent,
    MessengerUserComponent,
    MessengerChatComponent,
    MessengerNewConversationComponent,
    LoginComponent,
    SuppliesListComponent,
    SupplyComponent,
    NewPostComponent,
    RegisterComponent,
    ImageUploadComponentComponent,
    EditAccountComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatMenuModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatInputModule,
    MatToolbarModule,
    MatButtonModule,
    MatCheckboxModule,
    MatIconModule,
    MatSidenavModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [ {provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true} ],
  bootstrap: [AppComponent]
})
export class AppModule { }
