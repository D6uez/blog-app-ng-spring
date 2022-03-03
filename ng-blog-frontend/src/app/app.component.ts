import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './_services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'ng-blog-frontend';
  roles: string[] = [];
  isLoggedIn = false;
  showAdminBoard = false;
  showModeratorBoard = false;
  username?: string;

  homeURL = "";


  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.username = user.username;
    }

    if (this.roles.includes("ROLE_USER")) {
      this.homeURL = "boardUser";
    }
    if (this.roles.includes("ROLE_MOD")) {
      this.homeURL = "boardMod";
    }
    if (this.roles.includes("ROLE_ADMIN")) {
      this.homeURL = "boardAdmin";
    }
  }


  logout(): void {
    this.tokenStorageService.logout();
    window.location.reload();
  }
}
