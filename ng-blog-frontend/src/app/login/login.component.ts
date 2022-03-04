import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../_services/auth.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  pageTitle = 'Login';

  form: any = {
    username: null,
    password: null
  };

  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private authService: AuthService, private tokenStorage: TokenStorageService, private router: Router) { }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
    }
  }

  onSubmit(): void {
    const { username, password } = this.form;
    this.authService.login(username, password).subscribe(
      (data) => {
        this.tokenStorage.saveToken(data.jwt);
        this.tokenStorage.saveUser(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this.tokenStorage.getUser().roles;

        if (this.roles.includes("ROLE_USER")) {
          this.router.navigate(['boardUser']);
        }
        if (this.roles.includes("ROLE_MOD")) {
          this.router.navigate(['boardMod']);
        }
        if (this.roles.includes("ROLE_ADMIN")) {
          this.router.navigate(['boardAdmin']);
        }
      },
      (err) => {
        console.log(err);
        this.errorMessage = err;
        this.isLoginFailed = true;
      }
    );
  }
}

