import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { IUser } from './user-list/user';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {
  private dbUrl = "http://localhost:8080/user";

  constructor(private http: HttpClient) { }

  getUsers(): Observable<IUser[]> {
    return this.http.get<IUser[]>(this.dbUrl).pipe(tap(() => catchError(this.handleError)));
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
