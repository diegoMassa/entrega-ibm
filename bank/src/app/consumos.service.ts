import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { environment } from './../environments/environment';
import { GenericResponse } from './model/genericResponse';
import { LogService } from './log.service';
import { Observable } from 'rxjs/Observable';
import { Consumos } from './model/consumos';
import { Tarjetas } from './model/tarjetas';

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" })
};

@Injectable()
export class ConsumosService {

  private urlAuth: string;

  constructor(private httpClient: HttpClient,
    private logService: LogService) { 
    this.urlAuth = environment.url_base + 'transacciones/';
  }

  public saveConsumos (parametros: Consumos): Observable<GenericResponse> {

    const operation: string = "saveTransacciones";
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

  public deleteConsumos (parametros: Consumos): Observable<GenericResponse> {

    const operation: string = "deleteTransacciones";
    const url:  string = `${this.urlAuth}/${operation}`;

    return this.httpClient.delete<GenericResponse>(`${url}/${parametros.tranId}`,httpOptions)
    .pipe(
      catchError(this.logService.handleServiceError<GenericResponse>(
        "DELETE", 
        operation,
        new GenericResponse(environment.HTTP_NOT_FOUND, null, [])
      ))
    );
    
  }

  public updateConsumos (parametros: Consumos): Observable<GenericResponse> {

    const operation: string = "updateTransacciones";
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

  public getDataConsumos (): Observable<GenericResponse> {

    const operation: string = "getDataTransacciones";
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

  public getTransaccionesTarjeta (tarjeta: Tarjetas): Observable<GenericResponse> {

    const operation: string = "getTransaccionesTarjeta";
    const url:  string = `${this.urlAuth}/${operation}`;

    return this.httpClient.post<GenericResponse>(url, tarjeta, httpOptions)
    .pipe(
      catchError(this.logService.handleServiceError<GenericResponse>(
        "POST", 
        operation,
        new GenericResponse(environment.HTTP_NOT_FOUND, null, [])
      ))
    ); 
  }


  public getConsumosByTarjeId (tarjeId : Number): Observable<GenericResponse> {

    const operation: string = "getTransaccionesByTarjId";
    const url:  string = `${this.urlAuth}/${operation}/${tarjeId}`;
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