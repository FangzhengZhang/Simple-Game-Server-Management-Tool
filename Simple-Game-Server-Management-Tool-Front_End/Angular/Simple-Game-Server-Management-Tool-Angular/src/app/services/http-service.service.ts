import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SGSMT_API_URL } from '../utils/static-variables';


@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor( private httpClient : HttpClient) { }

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  get(url: string): Observable<any> {
    return this.httpClient.get(SGSMT_API_URL + url);
  }

  post(url: string, data: any): Observable<any> {
    return this.httpClient.post(SGSMT_API_URL + url, data, this.httpOptions);
  }

  put(url: string, data: any): Observable<any> {
    return this.httpClient.put(SGSMT_API_URL + url, data, this.httpOptions);
  }

  delete(url: string): Observable<any> {
    return this.httpClient.delete(SGSMT_API_URL + url, this.httpOptions);
  }


}
