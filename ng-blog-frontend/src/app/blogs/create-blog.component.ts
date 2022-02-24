import { Component, OnInit } from '@angular/core';
import { BlogService } from './blog.service';

@Component({
  selector: 'app-create-blog',
  templateUrl: './create-blog.component.html',
  styleUrls: ['./create-blog.component.css']
})
export class CreateBlogComponent implements OnInit {
  pageTitle = "New Blog";

  form: any = {
    body: null,
    title: null,
    topic: null,
    authorId: 0
  };

  isSuccessful: boolean = false;
  isBlogCreationFailed: boolean = false;
  errorMessage: string = '';

  constructor(private blogService: BlogService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    const { body, title, topic, authorId } = this.form;
    this.blogService.newBlog(body, title, topic, authorId).subscribe({
      next: (data: any) => {
        console.log(data);
        this.isSuccessful = true;
        this.isBlogCreationFailed = false;
      },
      error: err => {
        console.log(err);
        this.isBlogCreationFailed = true;
      }
    })
  }

}
