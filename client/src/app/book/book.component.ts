import { Component, OnInit } from '@angular/core';
import {SearchService} from "../shared/search.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-book',
  templateUrl: './book.component.html',
  styleUrls: ['./book.component.css']
})
export class BookComponent implements OnInit {
  bookForm: FormGroup;
  validMessage = "";
  constructor(private SearchService: SearchService,
              private route: Router,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.bookForm = this.formBuilder.group({
      title: ['', Validators.required]
    });
  }

  onSubmit() {
    let title = this.bookForm.get('title').value;
    console.log('title is ' + title);
    this.route.navigate(['allbooks', title]);
  }

}
