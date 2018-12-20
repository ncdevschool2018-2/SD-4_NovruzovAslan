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

  getOwnProducts(page: number, size: string, managerId: string): Observable<Product[]> {
    return this.http.get<Product[]>("/api/products/own?page="+page+"&size="+size+"&manager_id="+managerId);
  }

  getTotalPages(size: string, category_id?: string, manager_id?: string): Observable<number> {
    let url: string = "/api/products/total-pages?size="+size;
    if(category_id)
      url += "&category_id=" + category_id;
    else if(manager_id)
      url += "&manager_id=" + manager_id;
    return this.http.get<number>(url);
  }

  getProductById(prodId: string): Observable<Product> {
    return this.http.get<Product>("/api/products/" + prodId);
  }

  // saveProduct(product: Product): Observable<Product> {
  //   return this.http.post<Product>(
  //     "/api/products/" + product.category.id,
  //     product
  //   );
  // }

  saveProduct(product: Product): Observable<Product> {
    return this.http.post<Product>("/api/products", product);
  }

  deleteProduct(productId: string): Observable<void> {
    return this.http.delete<void>("/api/products/" + productId);
  }
}
