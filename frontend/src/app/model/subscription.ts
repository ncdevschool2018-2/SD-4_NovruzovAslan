import {Product} from "./product";
import {User} from "./user";
import {Wallet} from "./wallet";

export class Subscription {
  id: string;
  product: Product;
  user: User; // Don't need
  duration: number;
  start: Date;
  end: Date;
  active: boolean;
  wallet: Wallet;

  static cloneBase(subscription: Subscription): Subscription {
    let clonedSubscription: Subscription = new Subscription();
    clonedSubscription.id = subscription.id;
    clonedSubscription.product = subscription.product;
    clonedSubscription.user = subscription.user;
    clonedSubscription.duration = subscription.duration;
    clonedSubscription.start = subscription.start;
    clonedSubscription.end = subscription.end;
    clonedSubscription.active = subscription.active;
    clonedSubscription.wallet = subscription.wallet;
    return clonedSubscription;
  }
}
