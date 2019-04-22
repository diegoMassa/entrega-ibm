import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioService } from '../usuario.service';
import { AsesoresService } from '../asesores.service';
import { Asesores } from '../model/asesores';
import { Subscription } from 'rxjs/Subscription';
import { environment } from '../../environments/environment.prod';

/*
- feather-icons is a directory installed in node_modules.
- I dont have to specify the whole path like '../node_modules/path/to/feather-icons'.
- Also rememeber to call the feather.replace() inside ngOnInit
- because it needs to first make sure the component loads first
- Source: https://stackoverflow.com/questions/44995317/how-to-use-svg-icon-set-package-from-node-modules-in-angular4?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa
*/
import * as feather from 'feather-icons';

@Component({
  selector: 'app-asesores',
  templateUrl: './asesores.component.html',
  styleUrls: ['./asesores.component.css']
})
export class AsesoresComponent implements OnInit, OnDestroy {

  user: string;
  codigo: string;
  mensajeError: string;
  mensajeExito: string;
  subSaveAsesores: Subscription;
  subDeleteAsesores: Subscription;
  subUpdateAsesores: Subscription;
  subGetDataAsesores: Subscription;
  listAsesores: Array<Asesores> = [];

  asesor: Asesores;

  constructor(private router: Router,
    private usuarioService: UsuarioService,
    private asesoresService: AsesoresService) {

    this.asesor = new Asesores();

    if (usuarioService.userInSession == null) {
      this.router.navigate(['/']);
    }

    this.user = 'Bienvenido, ' + this.usuarioService.userInSession;
    
    this.consultarAsesores();

  }

  ngOnInit() {
    feather.replace();
  }

  ngOnDestroy() {
    if (this.subSaveAsesores !== null && this.subSaveAsesores !== undefined) {
      this.subSaveAsesores.unsubscribe();
    }

    if (this.subDeleteAsesores !== null && this.subDeleteAsesores !== undefined) {
      this.subDeleteAsesores.unsubscribe();
    }

    if (this.subUpdateAsesores !== null && this.subUpdateAsesores !== undefined) {
      this.subUpdateAsesores.unsubscribe();
    }

    if (this.subGetDataAsesores !== null && this.subGetDataAsesores !== undefined) {
      this.subGetDataAsesores.unsubscribe();
    }
  }
 
  public guardar(): void {

    try {

      if (this.asesor.nombre == undefined || this.asesor.nombre == null) {
        throw new Error('Por favor especifique el nombre del asesor');
      }

      if (this.asesor.especialidad == undefined || this.asesor.especialidad == null) {
        throw new Error('Por favor especifique la especialidad del asesor');
      }

      if(this.asesor.asesId !== undefined && this.asesor.asesId !== null){

        this.subUpdateAsesores = this.asesoresService.updateAsesores(this.asesor)
        .subscribe(data => {
          if (data.codigo === environment.HTTP_OK) {
            this.mensajeExito = 'Asesor actualizado exitosamente';
            this.consultarAsesores();
            this.limpiarCampos();
          }
          
          if (data.codigo === environment.HTTP_BAD_REQUEST) {
            this.mensajeError = data.mensaje;
          }
        }, error => {
          throw new Error(error);
        });

      }else {
        this.subSaveAsesores = this.asesoresService.saveAsesores(this.asesor)
        .subscribe(data => {

          if (data.codigo === environment.HTTP_OK) {
            this.consultarAsesores();
            this.limpiarCampos();
            this.mensajeExito = 'Asesor creado exitosamente';
          }
          
          if (data.codigo === environment.HTTP_BAD_REQUEST) {
            this.mensajeError = data.mensaje;
          }

        }, error => {
          throw new Error(error);
        });
      }

    } catch (error) {
      this.mensajeError = error;
    }

  }

  public borrar(asesorAEliminar: Asesores): void {

    try {

      if (asesorAEliminar.asesId == undefined || asesorAEliminar.asesId == null) {
        throw new Error('Por favor seleccione el asesor');
      }

      this.subDeleteAsesores = this.asesoresService.deleteAsesores(asesorAEliminar)
        .subscribe(data => {
          if (data.codigo === environment.HTTP_OK) {
            this.consultarAsesores();
            this.limpiarCampos();
            this.mensajeExito = 'Asesor eliminado exitosamente';
          }
          
          if (data.codigo === environment.HTTP_BAD_REQUEST) {
            this.mensajeError = data.mensaje;
          }
        }, error => {
          throw new Error(error);
        });

    } catch (error) {
      this.mensajeError = error;
    }

  }

  public consultarAsesores(): void {

    try {

      this.subGetDataAsesores = this.asesoresService.getDataAsesores()
        .subscribe(data => {
          if (data.codigo === environment.HTTP_OK) {
            this.listAsesores = data.lista as Array<Asesores>;
          }
          
          if (data.codigo === environment.HTTP_BAD_REQUEST) {
            this.mensajeError = data.mensaje;
          }
        }, error => {
          throw new Error(error);
        });

    } catch (error) {
      this.mensajeError = error;
      this.listAsesores = [];
    }

  }

  public selecccionarAsesor(asesorAEditar: Asesores): void{
    this.asesor = new Asesores();
    this.asesor.asesId = asesorAEditar.asesId;
    this.asesor.nombre = asesorAEditar.nombre;
    this.asesor.especialidad = asesorAEditar.especialidad;
  }

  public limpiar(): void {
    this.asesor.asesId = null;
    this.asesor.nombre = null;
    this.asesor.especialidad = null;
    this.mensajeError = null;
    this.mensajeExito = null;
  }

  private limpiarCampos(): void {
    this.asesor.asesId = null;
    this.asesor.nombre = null;
    this.asesor.especialidad = null;
  }

  public cerrarSpan(): void {
    this.mensajeError = null;
    this.mensajeExito = null;
  }
}