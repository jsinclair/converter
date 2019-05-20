import { Injectable, Inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { SESSION_STORAGE, WebStorageService } from 'angular-webstorage-service';

import { BasicResponse } from './basic-response';
import { AuditAction } from './audit-action';

@Injectable({
  providedIn: 'root'
})
export class AuditorService {

  private auditUrl = "http://localhost:8080/log_action"

  readonly httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient,
  @Inject(SESSION_STORAGE) private storage: WebStorageService) { }

  // Attempt to log a given action
  logAction(action: string): Observable<BasicResponse> {
    return this.http.post<BasicResponse>(this.auditUrl, {sessionId: this.storage.get('sessionID'), action: action} as AuditAction, this.httpOptions).pipe(
      tap((response: BasicResponse) => console.log(`response: ${response.content}`)),
      catchError(this.handleError<BasicResponse>('logAction'))
    );
  }

  /**
    * Handle Http operation that failed.
    * Let the app continue.
    * @param operation - name of the operation that failed
    * @param result - optional value to return as the observable result
    */
  private handleError<T> (operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      console.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
