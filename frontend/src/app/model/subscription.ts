import {Product} from "./product";
import {Wallet} from "./wallet";

export class Subscription {
  id: string;
  product: Product;
  userWallet: Wallet;
  start: Date;
  end: Date;
  active: boolean;

  static cloneBase(subscription: Subscription): Subscription {
    let clonedSubscription: Subscription = new Subscription();
    clonedSubscription.id = subscription.id;
    clonedSubscription.product = subscription.product;
    clonedSubscription.userWallet = subscription.userWallet;
    clonedSubscription.start = subscription.start;
    clonedSubscription.end = subscription.end;
    clonedSubscription.active = subscription.active;
    return clonedSubscription;
  }
}
