import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/internal/Observable";

import { Category } from "../../model/category";

@Injectable({
  providedIn: "root"
})
export class CategoryService {
  constructor(private http: HttpClient) {}

  // Ajax request for user data
  getCategorys(): Observable<Category[]> {
    return this.http.get<Category[]>("http://localhost:8081/api/categories");
  }

  saveCategory(category: Category): Observable<Category> {
    return this.http.post<Category>(
      "http://localhost:8081/api/categories",
      category
    );
  }

  deleteCategory(categoryId: string): Observable<void> {
    return this.http.delete<void>(
      "http://localhost:8081/api/categories/" + categoryId
    );
  }
}
