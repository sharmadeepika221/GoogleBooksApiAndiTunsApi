import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router} from "@angular/router";
import { SearchService } from "../shared/search.service";

@Component({
  selector: 'app-allSearch',
  templateUrl: './allSearch.component.html',
  styleUrls: ['./allSearch.component.css']
})
export class AllSearchComponent implements OnInit {
  books: Object;
  isLoading = true;
  isEmpty = false;
  color = 'primary';
  mode='indeterminate';
  value=50;
  constructor(private route: ActivatedRoute,
              private router: Router,
              private searchService: SearchService) { }

  ngOnInit() {
    this.getAllSearchResults();
  }

  getAllSearchResults() {
    const title = this.route.snapshot.params['title'];
    console.log('title is ' + title);
    this.searchService.getAllResultsByTitle(title)
      .subscribe(data => {
        this.books = data;
        if(data.length === 0) {
          this.isEmpty = true;
        }
        this.isLoading = false;
      });

  }
}
