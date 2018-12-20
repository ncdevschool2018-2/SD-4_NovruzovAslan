import {Wallet} from "./wallet";
import {SpecialProduct} from "./specialProduct";

export class SpecialSubscription {
  id: string;
  specialProduct: SpecialProduct;
  userWallet: Wallet;
  start: Date;
  end: Date;
  active: boolean;

  static cloneBase(subscription: SpecialSubscription): SpecialSubscription {
    let clonedSubscription: SpecialSubscription = new SpecialSubscription();
    clonedSubscription.id = subscription.id;
    clonedSubscription.specialProduct = subscription.specialProduct;
    clonedSubscription.userWallet = subscription.userWallet;
    clonedSubscription.start = subscription.start;
    clonedSubscription.end = subscription.end;
    clonedSubscription.active = subscription.active;
    return clonedSubscription;
  }
}
