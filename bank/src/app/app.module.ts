import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { HttpClientModule } from '@angular/common/http'; // <-- Http requests lives here
import { NgxMaskModule } from 'ngx-mask';
import { CurrencyMaskModule } from "ngx-currency-mask";

import { AppRoutingModule } from './/app-routing.module';

import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { CoverComponent } from './cover/cover.component';
import { UsuarioService } from './usuario.service';
import { AsesoresComponent } from './asesores/asesores.component';
import { AsesoresService } from './asesores.service';
import { LogService } from './log.service';
import { ClientesComponent } from './clientes/clientes.component';
import { ClientesService } from './clientes.service';
import { TarjetasComponent } from './tarjetas/tarjetas.component';
import { ConsumosComponent } from './consumos/consumos.component';
import { ConsumosService } from './consumos.service';
import { TarjetasService } from './tarjetas.service';
import { ClientesDetalleComponent } from './clientes-detalle/clientes-detalle.component';
import { TarjetaDetalleComponent } from './tarjeta-detalle/tarjeta-detalle.component';


@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    LoginComponent,
    CoverComponent,
    AsesoresComponent,
    ClientesComponent,
    TarjetasComponent,
    ConsumosComponent,
    ClientesDetalleComponent,
    TarjetaDetalleComponent
  ],
  imports: [
    BrowserModule,
    CurrencyMaskModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    NgxMaskModule.forRoot()
  ],
  providers: [
    UsuarioService, 
    AsesoresService, 
    LogService, 
    ClientesService,
    TarjetasService,
    ConsumosService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
