import { Injectable } from '@angular/core';
import { HttpServiceService } from "./http-service.service";
import { GET_SYS_INFO_URL_PATH } from '../utils/static-variables';
import {Observable} from "rxjs";
import {SysInfoDtoModel} from "../components/sys-info/sys-info-dto.model";

@Injectable({
  providedIn: 'root'
})
export class SysInfoService {

  constructor(private http: HttpServiceService) { }

  getSysInfo(): Observable<SysInfoDtoModel> {
    return this.http.get(GET_SYS_INFO_URL_PATH);
  }


}
