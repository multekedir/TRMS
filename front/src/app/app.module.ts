import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {EmployeeComponent} from './components/employee/employee.component';
import {LoginComponent} from './components/login/login.component';
import {ReactiveFormsModule} from '@angular/forms';
import {FormComponent} from './components/form/form.component';


@NgModule({
  declarations: [
    AppComponent,
    EmployeeComponent,
    LoginComponent,
    FormComponent
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
