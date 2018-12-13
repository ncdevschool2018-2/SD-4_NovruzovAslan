import {Category} from "./category";
import {SpecialProduct} from "./specialProduct";
import {SubscriptionService} from "../service/subscription/subscription.service";
import {Subscription} from "./subscription";
import {Wallet} from "./wallet";

export class Product {
  id: number;
  name: string;
  description: string;
  img_src: string;
  cost: number;
  category: Category;
  wallet: Wallet;
  specialProducts: SpecialProduct[];

  constructor() {
    this.category = new Category();
  }

  static cloneBase(product: Product): Product {
    let clonedProduct: Product = new Product();
    clonedProduct.id = product.id;
    clonedProduct.name = product.name;
    clonedProduct.description = product.description;
    clonedProduct.img_src = product.img_src;
    clonedProduct.cost = product.cost;
    clonedProduct.category = product.category;
    clonedProduct.wallet = product.wallet;
    clonedProduct.specialProducts = product.specialProducts;
    return clonedProduct;
  }

  subscribe(subscriptionService: SubscriptionService): void {
    let subscription: Subscription;
    // subscription.
    subscriptionService.saveSubscription(subscription);
  }

}
