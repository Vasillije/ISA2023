import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { RegisterComponent } from './pages/register/register.component';
import { register } from 'node:module';
import { AppointmentFormComponent } from './pages/appointment-form/appointment-form.component';

const routes: Routes = [
  { path: '', component: HomePageComponent},
  {path: 'register' , component: RegisterComponent},
  {path: 'login' , component: LoginPageComponent},
  {path: 'appointment-form', component: AppointmentFormComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
