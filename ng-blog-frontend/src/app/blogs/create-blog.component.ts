import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
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
    topic: null
  };

  isSuccessful: boolean = false;
  isBlogCreationFailed: boolean = false;
  isLoggedIn: boolean = false;
  author: string = '';
  errorMessage: string = '';

  constructor(private blogService: BlogService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      console.log(user);
      this.author = user.username;
    }
  }

  onSubmit(): void {
    const { body, title, topic } = this.form;
    this.blogService.newBlog(body, title, topic, this.author).subscribe({
      next: (data: any) => {
        this.isSuccessful = true;
        this.isBlogCreationFailed = false;
        window.location.reload();
      },
      error: err => {
        console.log(err);
        this.isBlogCreationFailed = true;
      }
    })
  }

}
