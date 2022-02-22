import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { TokenStorageService } from '../_services/token-storage.service';
import { IBlog } from './blog';
import { BlogService } from './blog.service';

@Component({
  selector: 'app-blog-list',
  templateUrl: './blog-list.component.html',
  styleUrls: ['./blog-list.component.css']
})
export class BlogListComponent implements OnInit, OnDestroy {

  pageTitle = "All Blogs"
  errorMessage: string = "";
  sub!: Subscription;

  blogs: IBlog[] = [];
  isLoggedIn: boolean = false;
  isAdmin: boolean = false;
  roles: any;

  constructor(private blogService: BlogService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    // This gets the blog list
    this.sub = this.blogService.getBlogs().subscribe({
      next: blogs => {
        this.blogs = blogs;
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
