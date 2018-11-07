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
    return this.http.get<Subscription[]>("http://localhost:8081/api/subscriptions");
  }

  saveSubscription(subscription: Subscription): Observable<Subscription> {
    return this.http.post<Subscription>("http://localhost:8081/api/subscriptions", subscription);
  }

  deleteSubscription(subscriptionId: string): Observable<void> {
    return this.http.delete<void>("http://localhost:8081/api/subscriptions/" + subscriptionId);
  }
}
