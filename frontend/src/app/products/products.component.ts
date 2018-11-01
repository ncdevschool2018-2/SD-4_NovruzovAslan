import { Component, OnInit, TemplateRef } from "@angular/core";
import { Subscription } from "rxjs/internal/Subscription";
import { Product } from "../model/product";
import { ProductService } from "../service/product/product.service";
import { BsModalRef, BsModalService } from "ngx-bootstrap/modal";

@Component({
  selector: "app-cards",
  templateUrl: "./products.component.html",
  styleUrls: ["./products.component.css"]
})
export class ProductsComponent implements OnInit {
  public editMode = false;

  public products: Product[];
  public editableProduct: Product = new Product();
  public modalRef: BsModalRef;

  private subscriptions: Subscription[] = [];

  constructor(
    private productService: ProductService,
    private modalService: BsModalService
  ) {}


  ngOnInit() {
    this.loadProducts();
  }

  public _closeModal(): void {
    this.modalRef.hide();
  }

  public _openModal(template: TemplateRef<any>, product: Product): void {
    if (product) {
      this.editMode = true;
      this.editableProduct = Product.cloneBase(product);
    } else {
      this.refreshProduct();
      this.editMode = false;
    }

    this.modalRef = this.modalService.show(template); // and when the user clicks on the button to open the popup
    // we keep the modal reference and pass the template local name to the modalService.
  }

  public _addProduct(): void {
    this.subscriptions.push(
      this.productService.saveProduct(this.editableProduct).subscribe(() => {
        this._updateProducts();
        this.refreshProduct();
        this._closeModal();
      })
    );
  }

  public _updateProducts(): void {
    this.loadProducts();
  }

  public _deleteProduct(productId: string): void {
    this.subscriptions.push(
      this.productService.deleteProduct(productId).subscribe(() => {
        this._updateProducts();
      })
    );
  }

  private refreshProduct(): void {
    this.editableProduct = new Product();
  }

  private loadProducts(): void {
    // Get data from ProductService
    this.subscriptions.push(
      this.productService.getProducts().subscribe(products => {
        // Parse json response into local array
        this.products = products as Product[];
        // Check data in console
        console.log(this.products); // don't use console.log in angular :)
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
