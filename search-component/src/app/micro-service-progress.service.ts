import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MicroServiceProgressService {

  constructor(private http:HttpClient) {}

  private saveUrl = 'http://localhost:8404/';
  

  public getLinks() {
    return this.http.get<Boolean>(`${this.saveUrl}`+'/link');
  }
 
  public getWebData() {
    return this.http.get<Boolean>(`${this.saveUrl}`+'/link');
  }

  public getKeyWords() {
    return this.http.get<Boolean>(`${this.saveUrl}`+'/keyWord');
  }
}
