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
import { Tarjetas } from '../model/tarjetas';
import { TarjetasService } from '../tarjetas.service';
import { Location } from '@angular/common';
import { Consumos } from '../model/consumos';
import { ConsumosService } from '../consumos.service';

@Component({
  selector: 'app-tarjeta-detalle',
  templateUrl: './tarjeta-detalle.component.html',
  styleUrls: ['./tarjeta-detalle.component.css']
})
export class TarjetaDetalleComponent implements OnInit, OnDestroy {

  user: string;
  codigo: string;
  mensajeError: string;
  mensajeExito: string;
  listConsumos: Array<Consumos> = [];
  tarjeta : Tarjetas;

  //Suscripciones
  subGetDataConsumos : Subscription;
  subGetTarjeta : Subscription;

  constructor(private router: Router,
    private usuarioService: UsuarioService,
    private tarjetasService: TarjetasService, 
    private activatedRoute : ActivatedRoute, 
    private location : Location,
    private consumosService : ConsumosService) {
    

    if (usuarioService.userInSession == null) {
      this.router.navigate(['/']);
    }
    this.user = 'Bienvenido, ' + this.usuarioService.userInSession;
  }

  ngOnInit() {
    feather.replace();
    this.consultarConsumosByTarjeta();
    this.consultarTarjetaById();
  }
 
  ngOnDestroy(){
    if (this.subGetDataConsumos !== null && this.subGetDataConsumos !== undefined) {
      this.subGetDataConsumos.unsubscribe();
    }
    if (this.subGetTarjeta !== null && this.subGetTarjeta !== undefined) {
      this.subGetTarjeta.unsubscribe();
    }
  }

  goBack() :void {
    this.location.back();
  }


  consultarTarjetaById() : void{
    try {
     const idParamCadena = this.activatedRoute.snapshot.paramMap.get('tarjeId');
      if(idParamCadena === null || idParamCadena === undefined){
        this.goBack();
      }
      //Casteo a number
      const id  = +idParamCadena;
      this.subGetTarjeta = this.tarjetasService.getTarjetaById(id).
      subscribe(data => {
        if (data.codigo === environment.HTTP_OK) {
          this.tarjeta = data.lista[0] as Tarjetas;
        }     
        if (data.codigo === environment.HTTP_BAD_REQUEST) {
          this.mensajeError = data.mensaje;
        }
      }, error => {
        throw new Error(error);
      });
    } catch (error) {
      this.mensajeError = error;
      this.tarjeta = null;
    }
  }

  consultarConsumosByTarjeta() : void{
    try {
      const idParamCadena = this.activatedRoute.snapshot.paramMap.get('tarjeId');
        if(idParamCadena === null || idParamCadena === undefined){
          this.goBack();
        }
         //Casteo a number
        const id  = +idParamCadena;
        this.subGetDataConsumos = this.consumosService.getConsumosByTarjeId(id).
        subscribe(data => {
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
}