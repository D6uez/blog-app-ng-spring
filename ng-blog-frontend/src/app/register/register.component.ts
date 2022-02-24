import { error } from '@angular/compiler/src/util';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  pageTitle = 'Register';

  form: any = {
    username: null,
    password: null,
    firstname: null,
    lastname: null
  };

  isSuccessful: boolean = false;
  isRegisterFailed: boolean = false;
  errorMessage: string = '';

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const { username, password, firstname, lastname } = this.form;
    this.authService.register(username, password, firstname, lastname).subscribe({
      next: data => {
        console.log(data);
        this.isSuccessful = true;
        this.isRegisterFailed = false;
      },
      error: err => {
        console.log(err);
        this.errorMessage = err.message;
        this.isRegisterFailed = true;
      }
    });
  }


}
