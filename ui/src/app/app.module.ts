import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from '@app/app-routing.module';
import { AppComponent } from '@app/app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToolbarComponent } from '@app/toolbar/toolbar.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';

import { TaskListComponent } from '@app/volunteer/task-list/task-list.component';
import { TaskItemComponent } from '@app/volunteer/task-item/task-item.component';
import { MessengerComponent } from '@app/messenger/messenger.component';
import { MessengerUserComponent } from '@app/messenger/messenger-user/messenger-user.component';
import { MessengerChatComponent } from '@app/messenger/messenger-chat/messenger-chat.component';
import { MessengerNewConversationComponent } from '@app/messenger/messenger-new-conversation/messenger-new-conversation.component';

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
    TaskListComponent,
    TaskItemComponent,
    MessengerComponent,
    MessengerUserComponent,
    MessengerChatComponent,
    MessengerNewConversationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
