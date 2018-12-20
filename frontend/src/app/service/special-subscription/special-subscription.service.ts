import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { SpecialSubscription } from "../../model/specialSubscription";

@Injectable({
  providedIn: "root"
})
export class SpecialSubscriptionService {
  constructor(private http: HttpClient) {}

  getSpecialSubscriptions(): Observable<SpecialSubscription[]> {
    return this.http.get<SpecialSubscription[]>("/api/special-subscriptions");
  }

  saveSpecialSubscription(subscription: SpecialSubscription): Observable<SpecialSubscription> {
    return this.http.post<SpecialSubscription>("/api/special-subscriptions", subscription);
  }

  getSpecialSubscriptionsPageByUserId(page: number, size: string, userId: string): Observable<SpecialSubscription[]> {
    return this.http.get<SpecialSubscription[]>(
      "/api/special-subscriptions/products?page="+page+"&size="+size+"&user_id="+userId
    );
  }

  getAllSpecialSubscriptionsByUserId(userId: string): Observable<SpecialSubscription[]> {
    return this.http.get<SpecialSubscription[]>("/api/special-subscriptions?user_id="+userId);
  }

  getTotalPages(size: string, userId: string): Observable<number> {
    return this.http.get<number>("/api/special-subscriptions/total-pages?size="+size+"&user_id="+userId);
  }

  unsubscribe(productId: string, userId: string): Observable<void> {
    return this.http.delete<void>("/api/special-subscriptions?product_id="+productId+"&user_id="+userId);
  }

  deleteSpecialSubscription(subscriptionId: string): Observable<void> {
    return this.http.delete<void>("/api/special-subscriptions/" + subscriptionId);
  }
}
