import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SearchService {
  url = "http://localhost:8080/api/v1/search/retrieveResult/";

  constructor(private http: HttpClient) { }

  getAllResultsByTitle(title: string): Observable<any> {
    const body = "{" +
      "searchByString(title: \"" + title + "\"" + ") {" +
      "title " +
      "authors" +
      "itemName" +
      "}}";
  console.log("in service")
    return this.http.get(this.url+title);
  }

}
