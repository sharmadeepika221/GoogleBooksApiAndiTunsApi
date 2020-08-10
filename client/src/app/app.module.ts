import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {AllSearchComponent} from "./allSearch/allsearch.component";
import { BookComponent } from './book/book.component';
import {HttpClientModule} from "@angular/common/http";
import {
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatListModule, MatProgressSpinnerModule, MatTabsModule,
  MatToolbarModule
} from "@angular/material";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";

import {SearchService} from "./shared/search.service";


@NgModule({
  declarations: [
    AppComponent,
    AllSearchComponent,
    BookComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatTabsModule,
    FormsModule,
    MatProgressSpinnerModule
  ],
  providers: [SearchService],
  bootstrap: [AppComponent]
})
export class AppModule { }
