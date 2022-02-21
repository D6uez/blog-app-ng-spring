import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
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

  constructor(private blogService: BlogService) { }

  ngOnInit(): void {
    this.sub = this.blogService.getBlogs().subscribe({
      next: blogs => {
        this.blogs = blogs;
      },
      error: err => this.errorMessage = err
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

}
