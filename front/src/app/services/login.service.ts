import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {
  }

  login() {
    return this.http.get('http://localhost:8080/TRMS_war_exploded/login?user=first_last&pass=password');
  }
}
