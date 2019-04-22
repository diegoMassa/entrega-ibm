import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Login } from '../model/login';
import { UsuarioService } from '../usuario.service';
import { Response } from '../model/Response';
import { Subscription } from 'rxjs/Subscription';
import { GenericResponse } from '../model/genericResponse';
import { environment } from './../../environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy {
  
  title = 'Angular Bank IBM'; 
  response: Response;
  codigo: String;
  mensaje: string;
  subLogin: Subscription;
  
  @Input() loginUser: Login;

  constructor(private router: Router,
              private usuarioService: UsuarioService) { 
    this.loginUser = new Login();
    this.mensaje = "";
  }

  ngOnInit() {
  }

  ngOnDestroy() {
    if (this.subLogin !== null && this.subLogin !== undefined) {
      this.subLogin.unsubscribe();
    }
  }

  login(): void{

    const started = Date.now();

    this.subLogin = this.usuarioService.autenticarUsuario(this.loginUser)
        .subscribe((data: GenericResponse) => {
          const elapsed = Date.now() - started;

          if (data.codigo === environment.HTTP_OK) {
            console.log(`Usuario autenticado con exito despues de ${elapsed} ms.`, "INFO");
            this.router.navigate(['/dashboard']);
          }

          if (data.codigo === environment.HTTP_BAD_REQUEST) {
            this.codigo = data.codigo;
            this.mensaje = data.mensaje;
            console.log(`Ha ocurrido un error después de ${elapsed} ms. El error fué: ${data.codigo} - ${data.mensaje}`, "ERROR");
          }
          
        }, error => {
          console.log(error, "ERROR");
        });

  }
  
}
