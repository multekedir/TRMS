import {Component, OnInit} from '@angular/core';

import {AuthenticationService} from '../../services/authentication.service';
import {User} from '../../models/user';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  loggedInEmployee: User;

  constructor(
    private authenticationService: AuthenticationService
  ) {
  }

  ngOnInit() {
    this.loggedInEmployee = this.authenticationService.currentUserValue;
  }

}
