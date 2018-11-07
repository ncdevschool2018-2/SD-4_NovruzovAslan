import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/internal/Observable";

import { Product } from "../../model/product";

@Injectable({
  providedIn: "root"
})
export class ProductService {
  constructor(private http: HttpClient) {}

  // Ajax request for user data
  // getProducts(): Observable<Product[]> {
  //   return this.http.get<Product[]>("http://localhost:8081/api/products");
  // }

  getProducts(categoryId: number = 0): Observable<Product[]> {
    let products: Observable<Product[]>;
    let temp: Observable<Product[]>;
    products = this.http.get<Product[]>("http://localhost:8081/api/products");
    if(categoryId == 0)
      return products;
    // for(let product in products) {
    //   if(product.category.id == 1)
    // }
    // todo: fill temp
  }

  saveProduct(product: Product): Observable<Product> {
    return this.http.post<Product>(
      "http://localhost:8081/api/products/" + product.category.id,
      product
    );
  }

  deleteProduct(productId: string): Observable<void> {
    return this.http.delete<void>(
      "http://localhost:8081/api/products/" + productId
    );
  }
}
