import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { BasicResponse } from './basic-response';

@Injectable({
  providedIn: 'root'
})
export class ConversionService {

  // Base URL for conversions, extension will be appendeds
  private baseUrl = "http://localhost:8080/"

  readonly httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient) { }

  /** Attempts to convert the given values. Uses the extension for reusability. */
  doConversion(extension: string, value: string,
    fromUnit: string, toUnit: string): Observable<BasicResponse> {

    const url = `${this.baseUrl}/${extension}?from=${fromUnit}&to=${toUnit}&value=${value}`;
    return this.http.get<BasicResponse>(url).pipe(
      catchError(this.handleError<BasicResponse>(`doConversion ${extension}?from=${fromUnit}&to=${toUnit}&value=${value}`))
    );
  }

  /** Attempts to convert the given temperature. */
  doTemperatureConversion(value: string, fromUnit: string): Observable<BasicResponse> {

    const url = `${this.baseUrl}/temperature?from=${fromUnit}&value=${value}`;
    return this.http.get<BasicResponse>(url).pipe(
      catchError(this.handleError<BasicResponse>(`doConversion temperature?from=${fromUnit}&value=${value}`))
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
