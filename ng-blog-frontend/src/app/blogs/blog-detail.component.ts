import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { IBlog } from './blog';
import { BlogService } from './blog.service';

@Component({
  templateUrl: './blog-detail.component.html',
  styleUrls: ['./blog-detail.component.css']
})
export class BlogDetailComponent implements OnInit, OnDestroy {

  pageTitle = "Blog Detail";
  blog: IBlog | undefined;
  errorMessage: string = "";
  sub!: Subscription;

  constructor(private route: ActivatedRoute, private router: Router, private blogService: BlogService) { }

  ngOnInit(): void {
    //Gets the id from the url parameter
    const id = Number(this.route.snapshot.paramMap.get('id'));
    //This subscribes to the blogService and calls the getSiblgeBlog method
    this.sub = this.blogService.getSingleBlog(id).subscribe({
      next: blog => { this.blog = blog; },
      error: err => this.errorMessage = err
    });
  }

  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  onBack(): void {
    this.router.navigate(['/blogs'])
  }

}
