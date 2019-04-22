import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UsuarioService } from '../usuario.service';
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
import { TarjetasService } from '../tarjetas.service';
import { Tarjetas } from '../model/tarjetas';
import { Consumos } from '../model/consumos';
import { ConsumosService } from '../consumos.service';

@Component({
  selector: 'app-consumos',
  templateUrl: './consumos.component.html',
  styleUrls: ['./consumos.component.css']
})


export class ConsumosComponent implements OnInit, OnDestroy {

  user: string;
  codigo: string;
  mensajeError: string;
  mensajeExito: string;
  consumo : Consumos;
  listConsumos : Array<Consumos> = [];
  listTarjetas : Array<Tarjetas> = [];

  //Suscripciones
  subSaveConsumos: Subscription;
  subDeleteConsumos: Subscription;
  subUpdateConsumos: Subscription;
  subGetDataConsumos: Subscription;
  subGetDataTarjetas: Subscription;
  subConsumosTarjeta: Subscription;

  constructor(private router: Router,
    private usuarioService: UsuarioService,
    private tarjetasService: TarjetasService,
    private consumosService : ConsumosService,
    private route: ActivatedRoute) {
    
    this.consumo = new Consumos();
   
    if (usuarioService.userInSession == null) {
      this.router.navigate(['/']);
    }
    this.user = 'Bienvenido, ' + this.usuarioService.userInSession;

    this.consultarConsumos();
    this.consultarTarjetas();
  }

  ngOnInit() {
    feather.replace();
  }

  ngOnDestroy() : void{
    if (this.subSaveConsumos !== null && this.subSaveConsumos !== undefined) {
      this.subSaveConsumos.unsubscribe();
    }

    if (this.subDeleteConsumos !== null && this.subDeleteConsumos !== undefined) {
      this.subDeleteConsumos.unsubscribe();
    }

    if (this.subUpdateConsumos !== null && this.subUpdateConsumos !== undefined) {
      this.subUpdateConsumos.unsubscribe();
    }

    if (this.subGetDataConsumos !== null && this.subGetDataConsumos !== undefined) {
      this.subGetDataConsumos.unsubscribe();
    } 

    if (this.subGetDataTarjetas !== null && this.subGetDataTarjetas !== undefined) {
      this.subGetDataTarjetas.unsubscribe();
    } 

    if (this.subConsumosTarjeta !== null && this.subConsumosTarjeta !== undefined) {
      this.subConsumosTarjeta.unsubscribe();
    }

    
  }


  public guardar(): void {
    
    //let fechaActual = new Date();
    
    try {

      if (this.consumo.tarjeId_Tarjetas == undefined || this.consumo.tarjeId_Tarjetas == null) {
        throw new Error('Por favor especifique a que tarjeta pertenece el consumo');
      }

      if (this.consumo.fecha == undefined || this.consumo.fecha == null) {
        throw new Error('Por favor especifique la fecha del consumo');
      }
      
      /* 
      if(this.consumo.fecha > fechaActual){
        throw new Error('El consumo no puede ser mayor a la fecha actual');
      }
      */

      if (this.consumo.monto == undefined || this.consumo.monto == null) {
        throw new Error('Por favor especifique el monto del consumo');
      }

      if (this.consumo.descripcion == undefined || this.consumo.descripcion == null) {
        throw new Error('Por favor especifique una descripción sobre el consumo');
      }

      if(this.consumo.tranId !== undefined && this.consumo.tranId !== null){

        // Es modificacion
        this.subUpdateConsumos = this.consumosService.updateConsumos(this.consumo)
        .subscribe(data => {
          if (data.codigo === environment.HTTP_OK) {
            this.mensajeExito = 'Consumo actualizado exitosamente!';
            this.consultarConsumos();
            this.limpiarCampos();
          }
          
          if (data.codigo === environment.HTTP_BAD_REQUEST) {
            this.mensajeError = data.mensaje;
          }
        }, error => {
          throw new Error(error);
        });

      }else {
        // Es Creacion
        this.subSaveConsumos = this.consumosService.saveConsumos(this.consumo)
        .subscribe(data => {

          if (data.codigo === environment.HTTP_OK) {
            this.mensajeExito = 'Consumo creado exitosamente!';
            this.consultarConsumos();
            this.limpiarCampos();
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

  public borrar(consumoAEliminar: Consumos): void {

    try {
      if (consumoAEliminar.tranId == undefined || consumoAEliminar.tranId == null) {
        throw new Error('Por favor seleccione el consumo');
      }
      this.subDeleteConsumos = this.consumosService.deleteConsumos(consumoAEliminar)
        .subscribe(data => {
          if (data.codigo === environment.HTTP_OK) {
            this.mensajeExito = 'Consumo eliminado exitosamente!';
            this.consultarConsumos();
            this.limpiarCampos();
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

  public consultarConsumos(): void {

    try {
      this.subGetDataConsumos = this.consumosService.getDataConsumos()
        .subscribe(data => {
          if (data.codigo === environment.HTTP_OK) {
            this.listConsumos = data.lista as Array<Consumos>;
          }
          
          if (data.codigo === environment.HTTP_BAD_REQUEST) {
            this.mensajeError = data.mensaje;
          }
        }, error => {
          throw new Error(error);
        });

    } catch (error) {
      this.mensajeError = error;
      this.listConsumos = [];
    }
  }

  public consultarTarjetas(): void {

    try {
      this.subGetDataTarjetas = this.tarjetasService.getDataTarjetas()
        .subscribe(data => {
          if (data.codigo === environment.HTTP_OK) {
            this.listTarjetas = data.lista as Array<Tarjetas>;
          }
          
          if (data.codigo === environment.HTTP_BAD_REQUEST) {
            this.mensajeError = data.mensaje;
          }
        }, error => {
          throw new Error(error);
        });

    } catch (error) {
      this.mensajeError = error;
      this.listTarjetas = [];
    }
  }

  public selecccionarConsumo(consumoAEditar: Consumos): void{
    this.consumo = new Consumos();
    this.consumo.tranId = consumoAEditar.tranId;
    this.consumo.fecha = consumoAEditar.fecha;
    this.consumo.monto = consumoAEditar.monto;
    this.consumo.descripcion = consumoAEditar.descripcion;
    this.consumo.tarjeId_Tarjetas = consumoAEditar.tarjeId_Tarjetas;
  }

  public limpiar(): void {
    this.consumo.tranId = null;
    this.consumo.fecha = null;
    this.consumo.monto = null;
    this.consumo.descripcion = null;
    this.consumo.tarjeId_Tarjetas = null;
    this.consumo.tarjeta = null;
    this.mensajeError = null;
    this.mensajeExito = null;
  }

  private limpiarCampos(): void {
    this.consumo.tranId = null;
    this.consumo.fecha = null;
    this.consumo.monto = null;
    this.consumo.descripcion = null;
    this.consumo.tarjeId_Tarjetas = null;
    this.consumo.tarjeta = null;
  }


  public getTransaccionesTarjeta(tarjeId: Number): void {

    let tarjeta: Tarjetas = new Tarjetas();
    tarjeta.tarjeId = tarjeId;

    try {
      this.subConsumosTarjeta = this.consumosService.getTransaccionesTarjeta(tarjeta)
        .subscribe(data => {
          if (data.codigo === environment.HTTP_OK) {
            this.listConsumos = data.lista as Array<Consumos>;
          }
          
          if (data.codigo === environment.HTTP_BAD_REQUEST) {
            this.mensajeError = data.mensaje;
          }
        }, error => {
          throw new Error(error);
        });

    } catch (error) {
      this.mensajeError = error;
      this.listConsumos = [];
    }
  }

  public cerrarSpan(): void {
    this.mensajeError = null;
    this.mensajeExito = null;
  }

}