import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { BaseUrl } from '../utils/static-variables';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor(private http:HttpClient) { }

  private httpOptions = {
    headers: new HttpHeaders({
    'Content-Type' : 'application/json'
    })
  }
  

  httpGet(requestPath: string): Observable<any> {
    return this.http.get(BaseUrl + requestPath, this.httpOptions);
  }

  httpPost(requestPath: string, body: any): Observable<any> {
    return this.http.post(BaseUrl + requestPath, body, this.httpOptions);
  }

  httpPut(requestPath: string, body: any): Observable<any> {
    return this.http.put(BaseUrl + requestPath, body, this.httpOptions);
  }

  httpDelete(requestPath: string): Observable<any> {
    return this.http.delete(BaseUrl + requestPath, this.httpOptions);
  }

}
