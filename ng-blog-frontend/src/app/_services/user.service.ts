import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
const USER_API_URL = 'http://localhost:8080/user';
const BLOG_API_URL = 'http://localhost:8080/blog'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getAllBlogs(): Observable<any> {
    return this.http.get(BLOG_API_URL + '', { responseType: 'text' });
  }
}
