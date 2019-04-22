import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { DashboardComponent } from './dashboard/dashboard.component';
import { LoginComponent } from './login/login.component';
import { CoverComponent } from './cover/cover.component';
import { AsesoresComponent } from './asesores/asesores.component';
import { ClientesComponent } from './clientes/clientes.component';
import { TarjetasComponent } from './tarjetas/tarjetas.component';
import { ConsumosComponent } from './consumos/consumos.component';
import { ClientesDetalleComponent } from './clientes-detalle/clientes-detalle.component';
import { TarjetaDetalleComponent } from './tarjeta-detalle/tarjeta-detalle.component';

const routes: Routes = [
  { path: '', redirectTo: '/cover', pathMatch: 'full' },
  { path: 'asesores', component: AsesoresComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'login', component: LoginComponent },
  { path: 'cover', component: CoverComponent },
  { path: 'clientes', component: ClientesComponent },
  { path: 'tarjetas', component: TarjetasComponent },
  { path: 'consumos', component: ConsumosComponent },
  {path : 'detail/:clieId', component : ClientesDetalleComponent},
  {path : 'detailTarjeta/:tarjeId', component : TarjetaDetalleComponent}
]

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
