import {Wallet} from "./wallet";
import {SpecialProduct} from "./specialProduct";

export class SpecialSubscription {
  id: string;
  product: SpecialProduct;
  userWallet: Wallet;
  start: Date;
  end: Date;
  active: boolean;

  static cloneBase(subscription: SpecialSubscription): SpecialSubscription {
    let clonedSubscription: SpecialSubscription = new SpecialSubscription();
    clonedSubscription.id = subscription.id;
    clonedSubscription.product = subscription.product;
    clonedSubscription.userWallet = subscription.userWallet;
    clonedSubscription.start = subscription.start;
    clonedSubscription.end = subscription.end;
    clonedSubscription.active = subscription.active;
    return clonedSubscription;
  }
}
