import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {FormService} from '../../services/form.service';
import {AuthenticationService} from './../../services/authentication.service';
import {User} from '../../models/user';
import {first} from 'rxjs/operators';

@Component({
  selector: 'app-request-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {
  reimbursementForm: FormGroup;
  loading = false;
  submitted = false;
  done = false;
  error: string;
  @ViewChild('close') close;
  private loggedInEmployee: User;

  constructor(
    private formBuilder: FormBuilder,
    private formService: FormService,
    private authenticationService: AuthenticationService) {
  }

  get f() {
    return this.reimbursementForm.controls;
  }

  ngOnInit(): void {
    this.loggedInEmployee = this.authenticationService.currentUserValue;
    this.reimbursementForm = this.formBuilder.group({
      amount: ['', [Validators.required, Validators.pattern('^\\d+(?:\\.\\d{0,2})?$')]],
      description: ['', Validators.required]
    });
  }

  onSubmit() {
    this.submitted = true;
    if (this.reimbursementForm.invalid) {
      console.warn('Invalid form');
      return;
    }

    this.loading = true;
    console.log(this.f.amount.value);
    console.log(this.f.description.value);
    console.log(this.loggedInEmployee);
    this.formService.sendRequest(this.loggedInEmployee.id, this.f.amount.value, this.f.description.value)
      .pipe(first())
      .subscribe(
        data => {
          console.log(data);
          this.reimbursementForm.reset();
          this.close.nativeElement.click();
        },
        message => {
          this.error = message[`error`];
          // this.alertService.error(error);
          this.loading = false;
        });
    this.loading = false;
  }
}
