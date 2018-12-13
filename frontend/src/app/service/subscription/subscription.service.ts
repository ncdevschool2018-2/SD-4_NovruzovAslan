import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";

import { Subscription } from "../../model/subscription";
import {Page} from "../../model/page";
import {Product} from "../../model/product";

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

  getProductsByUserId(page: number, userId: string): Observable<Product[]> {
    return this.http.get<Product[]>(
      "/api/subscriptions/products?page="+page+"&user_id="+userId
    );
  }

  getAllSubscriptionsByUserId(userId: string): Observable<Subscription[]> {
    return this.http.get<Subscription[]>("/api/subscriptions?user_id="+userId);
  }

  getTotalPages(userId: string): Observable<number> {
    return this.http.get<number>("/api/subscriptions/total-pages?user_id="+userId);
  }

  unsubscribe(productId: string, userId: string): Observable<void> {
    return this.http.delete<void>("/api/subscriptions?product_id="+productId+"&user_id="+userId);
  }

  deleteSubscription(subscriptionId: string): Observable<void> {
    return this.http.delete<void>("/api/subscriptions/" + subscriptionId);
  }
}
