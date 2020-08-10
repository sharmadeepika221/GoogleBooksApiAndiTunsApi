import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AppComponent} from "./app.component";
import {BookComponent} from "./book/book.component";
import {AllSearchComponent} from "./allSearch/allsearch.component";

const routes: Routes = [
  {
    path: '',
    component: BookComponent
  },
  {
    path: 'books',
    component: BookComponent
  },
  {
    path: 'allbooks/:title',
    component: AllSearchComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
