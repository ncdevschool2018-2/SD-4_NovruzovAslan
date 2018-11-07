import {Category} from "./category";
import {SpecialProduct} from "./specialProduct";

export class Product {
  id: number;
  name: string;
  short_description: string;
  full_description: string;
  img_src: string;
  cost: number;
  category: Category;
  specialProducts: SpecialProduct[];

  constructor() {
    this.category = new Category();
  }

  static cloneBase(product: Product): Product {
    let clonedProduct: Product = new Product();
    clonedProduct.id = product.id;
    clonedProduct.name = product.name;
    clonedProduct.short_description = product.short_description;
    clonedProduct.full_description = product.full_description;
    clonedProduct.img_src = product.img_src;
    clonedProduct.cost = product.cost;
    clonedProduct.category = product.category;
    clonedProduct.specialProducts = product.specialProducts;
    return clonedProduct;
  }
}
