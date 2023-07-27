import { Injectable } from '@angular/core';
import { HttpServiceService } from "./http-service.service";
import {GET_HOMEPAGE_HEAD_MESSAGE_URL, GET_SYS_INFO_URL_PATH} from '../utils/static-variables';
import {Observable} from "rxjs";
import {HomeMessageCardDtoModel} from "../components/home/home.message-card-dto.model";

@Injectable({
  providedIn: 'root'
})
export class HomepageServiceService {

  constructor(private http: HttpServiceService) { }

  getHomeMessageCardModel(): Observable<HomeMessageCardDtoModel[]> {
    return this.http.get(GET_HOMEPAGE_HEAD_MESSAGE_URL);
  }


}
