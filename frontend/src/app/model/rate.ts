import {Product} from "./product";
import {User} from "./user";

export class Rate {
  id: string;
  product: Product;
  user: User;
  rate: number;
  date: Date;

  static cloneBase(rate: Rate): Rate {
    let clonedRate: Rate = new Rate();
    clonedRate.id = rate.id;
    clonedRate.product = rate.product;
    clonedRate.user = rate.user;
    clonedRate.rate = rate.rate;
    clonedRate.date = rate.date;
    return clonedRate;
  }
}
