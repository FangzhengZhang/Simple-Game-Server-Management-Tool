import { Component } from '@angular/core';
import { SysInfoService } from '../../services/sys-info.service';
import { SysInfoDtoModel } from './sys-info-dto.model';

@Component({
  selector: 'app-sys-info',
  templateUrl: './sys-info.component.html',
  styleUrls: ['./sys-info.component.css']
})
export class SysInfoComponent {
  sysInfoDto: SysInfoDtoModel | undefined ;

  constructor(private sysInfoService: SysInfoService) { }

  ngOnInit(): void {
    this.updateSysInfoDto();
  }

  updateSysInfoDto(): void {
    this.sysInfoService.getSysInfo()
      .subscribe(sysInfoDto => this.sysInfoDto = sysInfoDto);
  }


}
