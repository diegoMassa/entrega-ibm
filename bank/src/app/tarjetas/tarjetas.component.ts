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
import { ClientesService } from '../clientes.service';
import { Clientes } from '../model/clientes';

@Component({
  selector: 'app-tarjetas',
  templateUrl: './tarjetas.component.html',
  styleUrls: ['./tarjetas.component.css']
})
export class TarjetasComponent implements OnInit, OnDestroy {

  user: string;
  codigo: string;
  mensajeError: string;
  mensajeExito: string;
  tarjeta : Tarjetas;
  listTarjetas : Array<Tarjetas> = [];
  listClientes : Array<Clientes> = [];

  //Suscripciones
  subSaveTarjetas: Subscription;
  subDeleteTarjetas: Subscription;
  subUpdateTarjetas: Subscription;
  subGetDataTarjetas: Subscription;
  subGetDataClientes: Subscription;
  subGetDataTarjetasCliente: Subscription;

  constructor(private router: Router,
    private usuarioService: UsuarioService,
    private tarjetasService: TarjetasService,
    private clientesService : ClientesService,
    private route: ActivatedRoute) {
    
    this.tarjeta = new Tarjetas();
   
    if (usuarioService.userInSession == null) {
      this.router.navigate(['/']);
    }
    this.user = 'Bienvenido, ' + this.usuarioService.userInSession;
    this.consultarTarjetas();
    this.consultarClientes();
  }

  ngOnInit() {
    feather.replace();
  }

  ngOnDestroy(): void {
    if (this.subSaveTarjetas !== null && this.subSaveTarjetas !== undefined) {
      this.subSaveTarjetas.unsubscribe();
    }

    if (this.subDeleteTarjetas !== null && this.subDeleteTarjetas !== undefined) {
      this.subDeleteTarjetas.unsubscribe();
    }

    if (this.subUpdateTarjetas !== null && this.subUpdateTarjetas !== undefined) {
      this.subUpdateTarjetas.unsubscribe();
    }

    if (this.subGetDataTarjetas !== null && this.subGetDataTarjetas !== undefined) {
      this.subGetDataTarjetas.unsubscribe();
    } 

    if (this.subGetDataClientes !== null && this.subGetDataClientes !== undefined) {
      this.subGetDataClientes.unsubscribe();
    } 

    if (this.subGetDataTarjetasCliente !== null && this.subGetDataTarjetasCliente !== undefined) {
      this.subGetDataTarjetasCliente.unsubscribe();
    }

    
  }
  
  public guardar(): void {
    try {

      console.log(this.tarjeta);

      this.tarjeta.numeroTarjeta = +this.tarjeta.numeroTarjeta;
      this.tarjeta.ccv = +this.tarjeta.ccv;

      if (this.tarjeta.clieId_Clientes == undefined || this.tarjeta.clieId_Clientes == null) {
        throw new Error('Por favor especifique a que cliente pertenece la tarjeta');
      }

      if (this.tarjeta.numeroTarjeta == undefined || this.tarjeta.numeroTarjeta == null) {
        throw new Error('Por favor especifique el número de la tarjeta');
      }

      if (this.tarjeta.ccv == undefined || this.tarjeta.ccv == null) {
        throw new Error('Por favor especifique el CCV de la tarjeta');
      }

      if (this.tarjeta.tipoTarjeta == undefined || this.tarjeta.tipoTarjeta == null) {
        throw new Error('Por favor especifique el tipo de tarjeta');
      }

      if(this.tarjeta.tarjeId !== undefined && this.tarjeta.tarjeId !== null){

        // Es modificacion
        this.subUpdateTarjetas = this.tarjetasService.updateTarjetas(this.tarjeta)
        .subscribe(data => {
          if (data.codigo === environment.HTTP_OK) {
            this.mensajeExito = 'Tarjeta actualizada exitosamente!';
            this.consultarTarjetas();
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
        this.subSaveTarjetas = this.tarjetasService.saveTarjetas(this.tarjeta)
        .subscribe(data => {

          if (data.codigo === environment.HTTP_OK) {
            this.mensajeExito = 'Tarjeta creada exitosamente!';
            this.consultarTarjetas();
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

  public borrar(tarjetaAEliminar: Tarjetas): void {

    try {
      if (tarjetaAEliminar.tarjeId == undefined || tarjetaAEliminar.tarjeId == null) {
        throw new Error('Por favor seleccione la tarjeta');
      }
      this.subDeleteTarjetas = this.tarjetasService.deleteTarjetas(tarjetaAEliminar)
        .subscribe(data => {
          if (data.codigo === environment.HTTP_OK) {
            this.mensajeExito = 'Tarjeta eliminada exitosamente!';
            this.consultarTarjetas();
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

  public consultarTarjetasDelCliente(clieId: Number): void {
    try {
      this.subGetDataTarjetasCliente = this.tarjetasService.getTarjetasByClieId(clieId)
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

  public selecccionarTarjeta(tarjetaAEditar: Tarjetas): void{
    this.tarjeta = new Tarjetas();
    this.tarjeta.tarjeId = tarjetaAEditar.tarjeId;
    this.tarjeta.numeroTarjeta = tarjetaAEditar.numeroTarjeta;
    this.tarjeta.ccv = tarjetaAEditar.ccv;
    this.tarjeta.tipoTarjeta = tarjetaAEditar.tipoTarjeta;
    this.tarjeta.clieId_Clientes = tarjetaAEditar.clieId_Clientes;
  }

  public limpiar(): void {
    this.tarjeta.tarjeId = null;
    this.tarjeta.numeroTarjeta = null;
    this.tarjeta.ccv = null;
    this.tarjeta.tipoTarjeta = null;
    this.tarjeta.clieId_Clientes = null;
    this.tarjeta.clientes = null;
    this.mensajeError = null;
    this.mensajeExito = null;
  }

  private limpiarCampos(): void {
    this.tarjeta.tarjeId = null;
    this.tarjeta.numeroTarjeta = null;
    this.tarjeta.ccv = null;
    this.tarjeta.tipoTarjeta = null;
    this.tarjeta.clieId_Clientes = null;
    this.tarjeta.clientes = null;
  }


  public consultarClientes(): void {

    try {
      this.subGetDataClientes = this.clientesService.getDataClientes()
        .subscribe(data => {
          if (data.codigo === environment.HTTP_OK) {
            this.listClientes = data.lista as Array<Clientes>;
          }
          
          if (data.codigo === environment.HTTP_BAD_REQUEST) {
            this.mensajeError = data.mensaje;
          }
        }, error => {
          throw new Error(error);
        });

    } catch (error) {
      this.mensajeError = error;
      this.listClientes = [];
    }
  }

  public cerrarSpan(): void {
    this.mensajeError = null;
    this.mensajeExito = null;
  }

}