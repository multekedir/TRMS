import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class FormService {
  private requestsList;

  constructor(private http: HttpClient) {
  }

  sendRequest(id: number, amount: number, description: string) {
    return this.http.post<any>(`${environment.apiUrl}/form/add`, {ID: id, amount, description})
      .pipe(map(result => {
        console.log(result);
        return result;
      }));
  }

  getRequests(id: number) {
    return this.http.get(`${environment.apiUrl}/form/pending/${id}`).pipe(
      map(resp => resp)
    );
    // console.log(this.requestsList);
    // return this.requestsList;
  }

}
