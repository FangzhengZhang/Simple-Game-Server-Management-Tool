import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { BackupJobComponent } from './components/backup-job/backup-job.component';
import { JobsComponent } from './components/jobs/jobs.component';
import { SysInfoComponent } from './components/sys-info/sys-info.component';
import {AppConfigPageComponent} from "./components/app-config-page/app-config-page.component";



const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'home',
    component: HomeComponent
  },
  {
      path: 'backupJobs',
      component: BackupJobComponent
  },
  {
    path: 'jobPage',
    component: JobsComponent
  },
  {
    path: 'systemInfo',
    component: SysInfoComponent
  },
  {
    path: 'appConfig',
    component: AppConfigPageComponent
  }




];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
