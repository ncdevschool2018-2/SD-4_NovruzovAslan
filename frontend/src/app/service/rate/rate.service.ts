import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { Rate } from "../../model/rate";

@Injectable({
  providedIn: "root"
})
export class RateService {
  constructor(private http: HttpClient) {}

  // Ajax request for rate data
  getRates(): Observable<Rate[]> {
    return this.http.get<Rate[]>("http://localhost:8081/api/rates");
  }

  saveRate(rate: Rate): Observable<Rate> {
    return this.http.post<Rate>("http://localhost:8081/api/rates", rate);
  }

  deleteRate(rateId: string): Observable<void> {
    return this.http.delete<void>("http://localhost:8081/api/rates/" + rateId);
  }
}
