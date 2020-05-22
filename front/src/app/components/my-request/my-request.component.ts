import {Component, OnInit} from '@angular/core';
import {FormService} from '../../services/form.service';
import {User} from '../../models/user';
import {AuthenticationService} from '../../services/authentication.service';

@Component({
  selector: 'app-my-request',
  templateUrl: './my-request.component.html',
  styleUrls: ['./my-request.component.css']
})
export class MyRequestComponent implements OnInit {
  headElements = ['Amount', 'Description'];
  private loggedInEmployee: User;
  private requests: any;
  private requested: any;
  private show;

  constructor(
    private formService: FormService,
    private authenticationService: AuthenticationService) {
  }

  get elements() {
    return this.requests;
  }

  ngOnInit(): void {
    this.loggedInEmployee = this.authenticationService.currentUserValue;
    this.formService.getRequests(this.loggedInEmployee.id).subscribe(res => this.requests = res);
    console.log(this.loggedInEmployee.role.name);

    console.log(this.requested);
  }
}
