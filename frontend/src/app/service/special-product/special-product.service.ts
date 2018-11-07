import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { SpecialProduct } from "../../model/specialProduct";

@Injectable({
  providedIn: "root"
})
export class SpecialProductService {
  constructor(private http: HttpClient) {}

  // Ajax request for specialProduct data
  getSpecialProducts(): Observable<SpecialProduct[]> {
    return this.http.get<SpecialProduct[]>("http://localhost:8081/api/specialproducts");
  }

  saveSpecialProduct(specialProduct: SpecialProduct): Observable<SpecialProduct> {
    return this.http.post<SpecialProduct>("http://localhost:8081/api/specialproducts", specialProduct);
  }

  deleteSpecialProduct(specialProductId: string): Observable<void> {
    return this.http.delete<void>("http://localhost:8081/api/specialproducts/" + specialProductId);
  }
}
