import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { UserServiceService } from '../user-service.service';
import { IUser } from './user';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit, OnDestroy {

  pageTitle = "Registered Users"
  errorMessage: string = "";
  sub!: Subscription;

  private _listFilter: string = "";

  get listFilter(): string {
    return this._listFilter;
  }

  set listFilter(value: string) {
    this._listFilter = value;
    this.filteredUsers = this.performFilter(value);
  }

  filteredUsers: IUser[] = [];

  users: IUser[] = [];
  isLoggedIn: boolean = false;
  isAdmin: boolean = false;
  roles: any;

  constructor(private userService: UserServiceService, private tokenStorageService: TokenStorageService) { }

  performFilter(filterBy: string): IUser[] {
    filterBy = filterBy.toLocaleLowerCase();
    return this.users.filter((product: IUser) =>
      product.username.toLocaleLowerCase().includes(filterBy));
  }

  ngOnInit(): void {
    this.sub = this.userService.getUsers().subscribe({
      next: users => {
        this.users = users;
        this.filteredUsers = this.users;
      },
      error: err => this.errorMessage = err
    });

    //This should retrieve a user and their role
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;
      this.isAdmin = this.roles.includes("ROLE_ADMIN");
    }
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }
}
