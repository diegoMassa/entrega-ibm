import { Component, OnInit, OnDestroy, Input } from '@angular/core';
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
import { Clientes } from '../model/clientes';
import { Tarjetas } from '../model/tarjetas';
import { TarjetasService } from '../tarjetas.service';
import { Location } from '@angular/common';
import { ClientesService } from '../clientes.service';

@Component({
  selector: 'app-clientes-detalle',
  templateUrl: './clientes-detalle.component.html',
  styleUrls: ['./clientes-detalle.component.css']
})

export class ClientesDetalleComponent implements OnInit, OnDestroy  {

  user: string;
  codigo: string;
  mensajeError: string;
  mensajeExito: string;
  listTarjetas: Array<Tarjetas> = [];
  cliente : Clientes;
  
  //Suscription
  subGetDataTarjetas : Subscription;
  subGetCliente : Subscription;

  constructor(private router: Router,
    private usuarioService: UsuarioService,
    private tarjetasService: TarjetasService, 
    private activatedRoute : ActivatedRoute, 
    private location : Location,
    private clienteService : ClientesService) {
    

    if (usuarioService.userInSession == null) {
      this.router.navigate(['/']);
    }
    this.user = 'Bienvenido, ' + this.usuarioService.userInSession;
  }

  ngOnInit() {
    feather.replace();
    this.consultarTarjetasByCliente();
    this.consultarClienteById();
  }

  
  consultarClienteById() : void{
    try {
     const idParamCadena = this.activatedRoute.snapshot.paramMap.get('clieId');
      if(idParamCadena === null || idParamCadena === undefined){
        this.goBack();
        //throw new Error("No ha seleccionado ningun cliente");
      }
      //Casteo a number
      const id  = +idParamCadena;
      this.subGetCliente = this.clienteService.getClienteById(id).
      subscribe(data => {
        if (data.codigo === environment.HTTP_OK) {
          this.cliente = data.lista[0] as Clientes;
        }     
        if (data.codigo === environment.HTTP_BAD_REQUEST) {
          this.mensajeError = data.mensaje;
        }
      }, error => {
        throw new Error(error);
      });
    } catch (error) {
      this.mensajeError = error;
      this.cliente = null;
    }
  }

  consultarTarjetasByCliente() : void{
    try {
        const id  = +this.activatedRoute.snapshot.paramMap.get('clieId');
        this.subGetDataTarjetas = this.tarjetasService.getTarjetasByClieId(id).
        subscribe(data => {
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

  goBack() :void {
    this.location.back();
  }

  ngOnDestroy(){
    if (this.subGetDataTarjetas !== null && this.subGetDataTarjetas !== undefined) {
      this.subGetDataTarjetas.unsubscribe();
    }
    if (this.subGetCliente !== null && this.subGetCliente !== undefined) {
      this.subGetCliente.unsubscribe();
    }
  }
}