import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ExportBlogsService {
  private dbUrl = 'http://localhost:8080/export/blog';

  constructor(private http: HttpClient) { }

  exportBlogs(): Observable<any> {
    return this.http.get(this.dbUrl, { responseType: 'blob' });
  }


}
