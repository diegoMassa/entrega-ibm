import { Injectable } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import { Response } from './model/Response';
import { Login } from './model/login';

import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

import { environment } from './../environments/environment';
import { GenericResponse } from './model/genericResponse';
import { LogService } from './log.service';

const httpOptions = {
  headers: new HttpHeaders({ "Content-Type": "application/json" })
};

@Injectable()
export class UsuarioService {

  private urlAuth: string;
  public userInSession: string;
  public codigo: string;

  constructor(private httpClient: HttpClient,
    private logService: LogService) { 
    this.urlAuth = environment.url_base + 'auth/';
  }

  public autenticarUsuario (parametros: Login): Observable<GenericResponse> {

    const operation: string = "login";
    const url:  string = `${this.urlAuth}/${operation}`;

    return this.httpClient.post<GenericResponse>(url, parametros, httpOptions)
    .pipe(
      tap(usuario => {

        this.userInSession = 'Diego Muñoz';
        this.codigo = '0';
        
        console.log(`Petición realizada con éxito hacia : /${operation} [POST]`, "INFO");

      }),
      catchError(this.logService.handleServiceError<GenericResponse>(
        "POST", 
        operation,
        new GenericResponse(environment.HTTP_NOT_FOUND, "ERROR 404 NOT FOUND", [])
      ))
    );
  }

}

