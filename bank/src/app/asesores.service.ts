import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { environment } from './../environments/environment';
import { GenericResponse } from './model/genericResponse';
import { LogService } from './log.service';
import { Asesores } from './model/asesores';
import { Observable } from 'rxjs/Observable';

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" })
};

@Injectable()
export class AsesoresService {

  private urlAuth: string;

  constructor(private httpClient: HttpClient,
    private logService: LogService) { 
    this.urlAuth = environment.url_base + 'asesores/';
  }

  public saveAsesores (parametros: Asesores): Observable<GenericResponse> {

    const operation: string = "saveAsesores";
    const url:  string = `${this.urlAuth}/${operation}`;

    return this.httpClient.post<GenericResponse>(url, parametros, httpOptions)
    .pipe(
      catchError(this.logService.handleServiceError<GenericResponse>(
        "POST", 
        operation,
        new GenericResponse(environment.HTTP_NOT_FOUND, null, [])
      ))
    );

  }

  public deleteAsesores (parametros: Asesores): Observable<GenericResponse> {

    const operation: string = "deleteAsesores";
    const url:  string = `${this.urlAuth}/${operation}`;

    return this.httpClient.delete<GenericResponse>(`${url}/${parametros.asesId}`,httpOptions)
    .pipe(
      catchError(this.logService.handleServiceError<GenericResponse>(
        "DELETE", 
        operation,
        new GenericResponse(environment.HTTP_NOT_FOUND, null, [])
      ))
    );
    
  }

  public updateAsesores (parametros: Asesores): Observable<GenericResponse> {

    const operation: string = "updateAsesores";
    const url:  string = `${this.urlAuth}/${operation}`;

    return this.httpClient.put<GenericResponse>(url, parametros, httpOptions)
    .pipe(
      catchError(this.logService.handleServiceError<GenericResponse>(
        "PUT", 
        operation,
        new GenericResponse(environment.HTTP_NOT_FOUND, null, [])
      ))
    );

  }

  public getDataAsesores (): Observable<GenericResponse> {

    const operation: string = "getDataAsesores";
    const url:  string = `${this.urlAuth}/${operation}`;

    return this.httpClient.get<GenericResponse>(url, httpOptions)
    .pipe(
      catchError(this.logService.handleServiceError<GenericResponse>(
        "GET", 
        operation,
        new GenericResponse(environment.HTTP_NOT_FOUND, null, [])
      ))
    ); 
  }
}
