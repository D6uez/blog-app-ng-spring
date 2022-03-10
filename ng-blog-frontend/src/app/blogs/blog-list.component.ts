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

  private _listFilter: string = "";

  get listFilter(): string {
    return this._listFilter;
  }

  set listFilter(value: string) {
    this._listFilter = value;
    this.filteredBlogs = this.performFilter(value);
  }

  filteredBlogs: IBlog[] = [];

  blogs: IBlog[] = [];
  isLoggedIn: boolean = false;
  isAdmin: boolean = false;
  roles: any;

  constructor(private blogService: BlogService, private tokenStorageService: TokenStorageService) { }

  performFilter(filterBy: string): IBlog[] {
    filterBy = filterBy.toLocaleLowerCase();
    return this.blogs.filter((product: IBlog) =>
      product.title.toLocaleLowerCase().includes(filterBy));
  }

  ngOnInit(): void {
    // This gets the blog list
    this.sub = this.blogService.getBlogs().subscribe({
      next: blogs => {
        this.blogs = blogs.reverse();
        this.filteredBlogs = this.blogs;
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

  deleteBlog(id: number) {
    this.sub = this.blogService.deleteBlog(id).subscribe(res => {
      window.location.reload();
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

}
