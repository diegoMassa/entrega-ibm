import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { environment } from './../environments/environment';
import { GenericResponse } from './model/genericResponse';
import { LogService } from './log.service';
import { Observable } from 'rxjs/Observable';
import { Clientes } from './model/clientes';

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" })
};

@Injectable()
export class ClientesService {

  private urlAuth: string;

  constructor(private httpClient: HttpClient,
    private logService: LogService) { 
    this.urlAuth = environment.url_base + 'clientes/';
  }


  public saveClientes (parametros: Clientes): Observable<GenericResponse> {

    const operation: string = "saveClientes";
    const url:  string = `${this.urlAuth}/${operation}`;

    console.log(url);

    return this.httpClient.post<GenericResponse>(url, parametros, httpOptions)
    .pipe(
      catchError(this.logService.handleServiceError<GenericResponse>(
        "POST", 
        operation,
        new GenericResponse(environment.HTTP_NOT_FOUND, null, [])
      ))
    );
  }

  public deleteClientes (parametros: Clientes): Observable<GenericResponse> {

    const operation: string = "deleteClientes";
    const url:  string = `${this.urlAuth}/${operation}`;

    return this.httpClient.delete<GenericResponse>(`${url}/${parametros.clieId}`,httpOptions)
    .pipe(
      catchError(this.logService.handleServiceError<GenericResponse>(
        "DELETE", 
        operation,
        new GenericResponse(environment.HTTP_NOT_FOUND, null, [])
      ))
    );
  }

  public updateClientes (parametros: Clientes): Observable<GenericResponse> {

    const operation: string = "updateClientes";
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

  public getDataClientes(): Observable<GenericResponse> {

    const operation: string = "getDataClientes";
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


  public getClienteById(idCliente : Number) : Observable<GenericResponse>{

    const operation: string = "getClientes";
    const url:  string = `${this.urlAuth}/${operation}/${idCliente}`;
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