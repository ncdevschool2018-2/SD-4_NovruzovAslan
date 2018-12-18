import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { Subscription } from "../../model/subscription";

@Injectable({
  providedIn: "root"
})
export class SubscriptionService {
  constructor(private http: HttpClient) {}

  // Ajax request for subscription data
  getSubscriptions(): Observable<Subscription[]> {
    return this.http.get<Subscription[]>("/api/subscriptions");
  }

  saveSubscription(subscription: Subscription): Observable<Subscription> {
    return this.http.post<Subscription>("/api/subscriptions", subscription);
  }

  getSubscriptionsPageByUserId(page: number, size: string, userId: string): Observable<Subscription[]> {
    return this.http.get<Subscription[]>(
      "/api/subscriptions/products?page="+page+"&size="+size+"&user_id="+userId
    );
  }

  getAllSubscriptionsByUserId(userId: string): Observable<Subscription[]> {
    return this.http.get<Subscription[]>("/api/subscriptions?user_id="+userId);
  }

  getTotalPages(size: string, userId: string): Observable<number> {
    return this.http.get<number>("/api/subscriptions/total-pages?size="+size+"&user_id="+userId);
  }

  unsubscribe(productId: string, userId: string): Observable<void> {
    return this.http.delete<void>("/api/subscriptions?product_id="+productId+"&user_id="+userId);
  }

  deleteSubscription(subscriptionId: string): Observable<void> {
    return this.http.delete<void>("/api/subscriptions/" + subscriptionId);
  }
}
