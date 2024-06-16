import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';

import { MatSelectModule } from '@angular/material/select';
import { AppComponent } from './app.component';
import { HomePageComponent } from './pages/home-page/home-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { HttpClientModule } from '@angular/common/http';

import {MatToolbarModule} from '@angular/material/toolbar';
import {MatIconModule} from '@angular/material/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import {MatExpansionModule} from '@angular/material/expansion';
import { RegisterComponent } from './pages/register/register.component';
import { AppointmentFormComponent } from './pages/appointment-form/appointment-form.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatMenuModule} from '@angular/material/menu';
import { ShowAppointmentsComponent } from './pages/show-appointments/show-appointments.component';
import {MatCardModule} from '@angular/material/card';
import { ShowEquipmentComponent } from './pages/show-equipment/show-equipment.component';
import { ScheduledAppointmentsComponent } from './pages/scheduled-appointments/scheduled-appointments.component';
import { MakeComplaintComponent } from './pages/make-complaint/make-complaint.component';
import { UserProfileComponent } from './pages/user-profile/user-profile.component';
import { AdminComplaintsComponent } from './pages/admin-complaints/admin-complaints.component';
import { ComplaintHistoryComponent } from './pages/complaint-history/complaint-history.component';


@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,  
    LoginPageComponent,
    RegisterComponent,
    AppointmentFormComponent,
    ShowAppointmentsComponent,
    ShowEquipmentComponent,
    ScheduledAppointmentsComponent,
    MakeComplaintComponent,
    UserProfileComponent,
    AdminComplaintsComponent,
    ComplaintHistoryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatToolbarModule,
    MatIconModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    MatTableModule,
    HttpClientModule,
    MatExpansionModule,
    MatDatepickerModule,
    MatMenuModule,
    MatCardModule,
    MatSelectModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
