import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/internal/Observable";

import { Product } from "../../model/product";
import { Page } from "../../model/page";

@Injectable({
  providedIn: "root"
})
export class ProductService {
  constructor(private http: HttpClient) {}

  // getProducts(): Observable<Page<Product>> {
  //   return this.http.get<Page<Product>>('/api/products?page=0');
  // }

  // Ajax request for user data
  // getProducts(): Observable<Product[]> {
  //   return this.http.get<Product[]>("/api/products");
  // }

  getProducts(page: number, size: string, categoryId?: string): Observable<Product[]> {
    if(!categoryId)
      return this.http.get<Product[]>("/api/products?page="+page+"&size="+size);
    return this.http.get<Product[]>("/api/products?page="+page+"&size="+size+"&category_id="+categoryId);
  }

  getTotalPages(size: string, category_id?: string): Observable<number> {
    if(!category_id)
      return this.http.get<number>("/api/products/total-pages?size="+size);
    return this.http.get<number>("/api/products/total-pages?size="+size+"&category_id="+category_id);
  }

  getProductById(prodId: string): Observable<Product> {
    return this.http.get<Product>("/api/products/" + prodId);
  }

  saveProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(
      "/api/products/" + product.category.id,
      product
    );
  }

  deleteProduct(productId: string): Observable<void> {
    return this.http.delete<void>(
      "http://localhost:8081/api/products/" + productId
    );
  }
}
