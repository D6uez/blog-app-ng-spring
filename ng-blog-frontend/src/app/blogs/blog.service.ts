// This file will fetch a list of blogs and also a single blog from the database
//Makes the call to the apis

import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { IBlog } from './blog';
const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
    providedIn: 'root'
})
export class BlogService {
    private dbUrl = "http://localhost:8080/blog";

    constructor(private http: HttpClient) { }
    //Makes a get request to api and retrieves a list of all blogs
    getBlogs(): Observable<IBlog[]> {
        return this.http.get<IBlog[]>(this.dbUrl).pipe(tap(() => catchError(this.handleError)));
    }

    //Makes a get request to api and retrieves a single blog based on the id in the url parameter
    getSingleBlog(id: number): Observable<IBlog> {
        return this.http.get<IBlog>(this.dbUrl + "/" + id).pipe(tap(() => catchError(this.handleError)));
    }

    //Makes a post request to api and creates a single blog
    newBlog(body: string, title: string, topic: string, author: string): Observable<IBlog> {
        return this.http.post<IBlog>(this.dbUrl + "/newBlog", {
            body, title, topic, author
        }, httpOptions);
    }

    editBlog(id: number, body: string, title: string, topic: string): Observable<IBlog> {
        return this.http.put<IBlog>(this.dbUrl + "/editBlog/" + id, {
            body, title, topic
        }, httpOptions);
    }

    deleteBlog(id: number): Observable<IBlog> {
        return this.http.delete<IBlog>(this.dbUrl + "/deleteBlog/" + id).pipe(tap(() => catchError(this.handleError)));
    }

    private handleError(err: HttpErrorResponse) {
        let errorMessage = "";
        if (err.error instanceof ErrorEvent) {
            errorMessage = `An error occured:  ${err.error.message}`;
        } else {

            errorMessage = `Server returned code: ${err.status}, error message is: ${err.message}`;
        }

        return throwError(() => new Error(errorMessage));
    }
}