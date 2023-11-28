import { Routes } from '@angular/router';
import {LoginComponent} from "./component/login/login.component";
import {DashboardComponent} from "./component/dashboard/dashboard.component";
import {RegisterComponent} from "./component/register/register.component";
import {DestinosComponent} from "./component/destinos/destinos.component";
import {SitiosComponent} from "./component/sitios/sitios.component";
import {PlanesComponent} from "./component/planes/planes.component";

export const routes: Routes = [
  { path: 'login', component: LoginComponent},
  { path: 'register', component: RegisterComponent},
  { path: 'dashboard', component: DashboardComponent},
  { path: 'destinos', component: DestinosComponent},
  { path: 'sitios', component: SitiosComponent},
  { path: 'planes', component: PlanesComponent},
  { path: '**', redirectTo: 'login' },

];
