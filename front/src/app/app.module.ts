import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {EmployeeComponent} from './components/employee/employee.component';
import {LoginComponent} from './components/login/login.component';
import {ReactiveFormsModule} from '@angular/forms';
import {FormComponent} from './components/form/form.component';
import {MyRequestComponent} from './components/my-request/my-request.component';


@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    LoginComponent,
    FormComponent,
    MyRequestComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
