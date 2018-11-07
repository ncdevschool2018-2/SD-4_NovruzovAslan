import {Product} from "./product";

export class SpecialProduct {
  id: string;
  product: Product;
  cost: number;
  products: Product[];

  static cloneBase(specialProduct: SpecialProduct): SpecialProduct {
    let clonedSpecialProduct: SpecialProduct = new SpecialProduct();
    clonedSpecialProduct.id = specialProduct.id;
    clonedSpecialProduct.product = specialProduct.product;
    clonedSpecialProduct.cost = specialProduct.cost;
    clonedSpecialProduct.products = specialProduct.products;
    return clonedSpecialProduct;
  }
}
