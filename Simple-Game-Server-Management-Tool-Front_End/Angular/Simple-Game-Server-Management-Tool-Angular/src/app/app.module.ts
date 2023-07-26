import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatTabsModule} from '@angular/material/tabs';
import {MatToolbarModule} from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { SysInfoComponent } from './components/sys-info/sys-info.component';
import { HomeComponent } from './components/home/home.component';
import { BackupJobComponent } from './components/backup-job/backup-job.component';
import { JobsComponent } from './components/jobs/jobs.component';
import { AppConfigPageComponent } from './components/app-config-page/app-config-page.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SysInfoComponent,
    HomeComponent,
    BackupJobComponent,
    JobsComponent,
    AppConfigPageComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSlideToggleModule,
    MatMenuModule,
    MatIconModule,
    MatToolbarModule,
    MatTabsModule,
    MatButtonModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
