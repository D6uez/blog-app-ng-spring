import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { IBlog } from './blog';
import { BlogService } from './blog.service';

@Component({
  selector: 'app-edit-blog',
  templateUrl: './edit-blog.component.html',
  styleUrls: ['./edit-blog.component.css']
})
export class EditBlogComponent implements OnInit {

  pageTitle = "Edit Blog"

  blog: IBlog | undefined;

  isSuccessful: boolean = false;
  isBlogEditFailed: boolean = false;
  errorMessage: string = '';
  sub!: Subscription;

  constructor(private blogService: BlogService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    //Gets the id from the url parameter
    const id = Number(this.route.snapshot.paramMap.get('id'));
    //This subscribes to the blogService and calls the getSiblgeBlog method
    this.sub = this.blogService.getSingleBlog(id).subscribe({
      next: blog => { this.blog = blog; },
      error: err => this.errorMessage = err
    });
  }

  onSubmit(): void {
    this.blogService.editBlog(Number(this.blog?.id), this.blog!.body, this.blog!.title, this.blog!.topic).subscribe({
      next: (data: any) => {
        console.log(data);
        this.isSuccessful = true;
        this.isBlogEditFailed = false;
        this.router.navigate(['/blogs', this.blog!.id]);
      },
      error: error => {
        console.log(error);
        this.isSuccessful = false;
        this.isBlogEditFailed = true;
      }
    })

  }

  onBack(): void {
    this.router.navigate(['/blogs'])
  }

}
