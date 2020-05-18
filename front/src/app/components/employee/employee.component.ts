import {Component, OnInit} from '@angular/core';
import {LoginService} from './../../services/login.service';
import {AuthenticationService} from '../../services/authentication.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  loggedInEmployee;

  constructor(
    private loginService: LoginService,
    private authenticationService: AuthenticationService
  ) {
  }

  ngOnInit() {
    this.loggedInEmployee = this.authenticationService.currentUserValue;
  }

}
