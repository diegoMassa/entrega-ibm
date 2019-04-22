import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
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
import { ClientesService } from '../clientes.service';
import { Clientes } from '../model/clientes';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styleUrls: ['./clientes.component.css']
})
export class ClientesComponent implements OnInit, OnDestroy {

  user: string;
  codigo: string;
  mensajeError: string;
  mensajeExito: string;
  cliente : Clientes;
  listClientes: Array<Clientes> = [];

  //Suscripciones
  subSaveClientes: Subscription;
  subDeleteClientes: Subscription;
  subUpdateClientes: Subscription;
  subGetDataClientes: Subscription;
  
  constructor(private router: Router,
    private usuarioService: UsuarioService,
    private clientesService: ClientesService) {
    
    this.cliente = new Clientes();
   
    if (usuarioService.userInSession == null) {
      this.router.navigate(['/']);
    }
    this.user = 'Bienvenido, ' + this.usuarioService.userInSession;
    this.consultarClientes();
  }

  ngOnInit() {
    feather.replace();
  }

  ngOnDestroy() {
    if (this.subSaveClientes !== null && this.subSaveClientes !== undefined) {
      this.subSaveClientes.unsubscribe();
    }

    if (this.subDeleteClientes !== null && this.subDeleteClientes !== undefined) {
      this.subDeleteClientes.unsubscribe();
    }

    if (this.subUpdateClientes !== null && this.subUpdateClientes !== undefined) {
      this.subUpdateClientes.unsubscribe();
    }

    if (this.subGetDataClientes !== null && this.subGetDataClientes !== undefined) {
      this.subGetDataClientes.unsubscribe();
    }
  }


  public guardar(): void {

    try {

      if (this.cliente.nombre == undefined || this.cliente.nombre == null) {
        throw new Error('Por favor especifique el nombre del cliente');
      }

      if (this.cliente.ciudad == undefined || this.cliente.ciudad == null) {
        throw new Error('Por favor especifique la ciudad del cliente');
      }

      if (this.cliente.direccion == undefined || this.cliente.direccion == null) {
        throw new Error('Por favor especifique la dirección del cliente');
      }

      if (this.cliente.telefono == undefined || this.cliente.telefono == null) {
        throw new Error('Por favor especifique el teléfono del cliente');
      }

      if(this.cliente.clieId !== undefined && this.cliente.clieId !== null){

        // Es modificacion
        this.subUpdateClientes = this.clientesService.updateClientes(this.cliente)
        .subscribe(data => {
          if (data.codigo === environment.HTTP_OK) {
            this.mensajeExito = 'Cliente actualizado exitosamente!';
            this.consultarClientes();
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
        this.subSaveClientes = this.clientesService.saveClientes(this.cliente)
        .subscribe(data => {

          if (data.codigo === environment.HTTP_OK) {
            this.mensajeExito = 'Cliente creado exitosamente';
            this.consultarClientes();
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

  public borrar(clienteAEliminar: Clientes): void {

    try {
      if (clienteAEliminar.clieId == undefined || clienteAEliminar.clieId == null) {
        throw new Error('Por favor seleccione el cliente');
      }
      this.subDeleteClientes = this.clientesService.deleteClientes(clienteAEliminar)
        .subscribe(data => {
          if (data.codigo === environment.HTTP_OK) {
            this.mensajeExito = 'Cliente eliminado exitosamente';
            this.consultarClientes();
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

  public selecccionarCliente(clienteAEditar: Clientes): void{
    this.cliente = new Clientes();
    this.cliente.clieId = clienteAEditar.clieId;
    this.cliente.nombre = clienteAEditar.nombre;
    this.cliente.direccion = clienteAEditar.direccion;
    this.cliente.ciudad = clienteAEditar.ciudad;
    this.cliente.telefono = clienteAEditar.telefono;
  }

  public limpiar(): void {
    this.cliente.clieId = null;
    this.cliente.nombre = null;
    this.cliente.direccion = null;
    this.cliente.ciudad = null;
    this.cliente.telefono = null;
    this.mensajeError = null;
    this.mensajeExito = null;
  }

  private limpiarCampos(): void {
    this.cliente.clieId = null;
    this.cliente.nombre = null;
    this.cliente.direccion = null;
    this.cliente.ciudad = null;
    this.cliente.telefono = null;
  }

  public cerrarSpan(): void {
    this.mensajeError = null;
    this.mensajeExito = null;
  }
}