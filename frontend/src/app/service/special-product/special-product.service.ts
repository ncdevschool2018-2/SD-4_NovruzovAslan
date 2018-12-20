import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { SpecialProduct } from "../../model/specialProduct";
import {Product} from "../../model/product";

@Injectable({
  providedIn: "root"
})
export class SpecialProductService {
  constructor(private http: HttpClient) {}

  getSpecialProducts(page: number, size: string, categoryId?: string): Observable<SpecialProduct[]> {
    if(!categoryId)
      return this.http.get<SpecialProduct[]>("/api/special-products?page="+page+"&size="+size);
    return this.http.get<SpecialProduct[]>("/api/special-products?page="+page+"&size="+size+"&category_id="+categoryId);
  }

  getTotalPages(size: string, category_id?: string): Observable<number> {
    if(!category_id)
      return this.http.get<number>("/api/special-products/total-pages?size="+size);
    return this.http.get<number>("/api/special-products/total-pages?size="+size+"&category_id="+category_id);
  }

  getSpecialProductById(prodId: string): Observable<SpecialProduct> {
    return this.http.get<SpecialProduct>("/api/special-products/" + prodId);
  }

  saveSpecialProduct(product: Product): Observable<SpecialProduct> {
    return this.http.post<SpecialProduct>(
      "/api/special-products/" + product.category.id,
      product
    );
  }

  deleteSpecialProduct(productId: string): Observable<void> {
    return this.http.delete<void>(
      "/api/special-products/" + productId
    );
  }}
