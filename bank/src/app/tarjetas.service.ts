import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { environment } from './../environments/environment';
import { GenericResponse } from './model/genericResponse';
import { LogService } from './log.service';
import { Observable } from 'rxjs/Observable';
import { Tarjetas } from './model/tarjetas';

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" })
};

@Injectable()
export class TarjetasService {

  private urlAuth: string;

  constructor(private httpClient: HttpClient,
    private logService: LogService) { 
    this.urlAuth = environment.url_base + 'tarjetas';
  }

  public saveTarjetas (parametros: Tarjetas): Observable<GenericResponse> {

    const operation: string = "saveTarjetas";
    const url:  string = `${this.urlAuth}/${operation}`;

    console.log(parametros);

    return this.httpClient.post<GenericResponse>(url, parametros, httpOptions)
    .pipe(
      catchError(this.logService.handleServiceError<GenericResponse>(
        "POST", 
        operation,
        new GenericResponse(environment.HTTP_NOT_FOUND, null, [])
      ))
    );
  }

  public deleteTarjetas (parametros: Tarjetas): Observable<GenericResponse> {

    const operation: string = "deleteTarjetas";
    const url:  string = `${this.urlAuth}/${operation}`;

    return this.httpClient.delete<GenericResponse>(`${url}/${parametros.tarjeId}`,httpOptions)
    .pipe(
      catchError(this.logService.handleServiceError<GenericResponse>(
        "DELETE", 
        operation,
        new GenericResponse(environment.HTTP_NOT_FOUND, null, [])
      ))
    );
  }

  public updateTarjetas (parametros: Tarjetas): Observable<GenericResponse> {

    const operation: string = "updateTarjetas";
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

  public getDataTarjetas (): Observable<GenericResponse> {

    const operation: string = "getDataTarjetas";
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

  public getTarjetasByClieId (idClie : Number): Observable<GenericResponse> {

    const operation: string = "getTarjetasByClieId";
    const url:  string = `${this.urlAuth}/${operation}/${idClie}`;
    console.log(url);
    return this.httpClient.get<GenericResponse>(url, httpOptions)
    .pipe(
      catchError(this.logService.handleServiceError<GenericResponse>(
        "GET", 
        operation,
        new GenericResponse(environment.HTTP_NOT_FOUND, null, [])
      ))
    ); 
  }

  public getTarjetaById(idTarje : Number) : Observable<GenericResponse>{

    const operation: string = "getTarjetas";
    const url:  string = `${this.urlAuth}/${operation}/${idTarje}`;
    console.log(url);
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