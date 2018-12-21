import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/internal/Observable";

import { Category } from "../../model/category";

@Injectable({
  providedIn: "root"
})
export class CategoryService {
  constructor(private http: HttpClient) {}

  getCategorys(): Observable<Category[]> {
    return this.http.get<Category[]>("/api/categories");
  }

  getCategoryById(id: string): Observable<Category> {
    return this.http.get<Category>("/api/categories/"+id);
  }

  saveCategory(categoryName: string): Observable<Category> {
    let category: Category = new Category();
    category.name = categoryName;
    return this.http.post<Category>(
      "/api/categories",
      category
    );
  }

  deleteCategory(categoryId: string): Observable<void> {
    return this.http.delete<void>(
      "/api/categories/" + categoryId
    );
  }
}
