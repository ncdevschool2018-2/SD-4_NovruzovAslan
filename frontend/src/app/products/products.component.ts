import {Component, Input, OnDestroy, OnInit, TemplateRef} from "@angular/core";
import { Subscription } from "rxjs/internal/Subscription";
import { Product } from "../model/product";
import { ProductService } from "../service/product/product.service";
import { BsModalRef, BsModalService } from "ngx-bootstrap/modal";
import { NgxSpinnerService } from 'ngx-spinner';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: "products",
  templateUrl: "./products.component.html",
  styleUrls: ["./products.component.css", "../app.component.css"]
})
export class ProductsComponent implements OnInit, OnDestroy {
  public editMode = false;
  // @Input()
  public categoryId: string;
  public currentPage: number = 1;

  public totalPages: number;
  public pages: number[] = [];
  public products: Product[];
  public editableProduct: Product = new Product();
  public modalRef: BsModalRef;

  private subscriptions: Subscription[] = [];

  constructor(
    private productService: ProductService,
    private modalService: BsModalService,
    private loadingService: NgxSpinnerService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.getId();
    // this.loadProducts();
  }

  private getId(): void {
    this.loadingService.show();
    this.route.paramMap.subscribe(params => {
      if(!params.has('id'))
        this.categoryId = '0';
      else this.categoryId = params.get('id');
      this.loadProducts();
      // this.setUser();
      // this.loadingService.hide();
    });
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
    if(this.categoryId !== '0') {
      this.editableProduct.category.id = this.categoryId;
    }
  }

  public loadProducts(): void {
    // this.loadingService.show();
    if (this.categoryId == '0') {
      this.subscriptions.push(
        this.productService.getProducts(this.currentPage).subscribe(products => {
          this.products = products as Product[];
          this.getTotalPagesNumber();
          console.log(this.products);
          // this.loadingService.hide();
        })
      );
    } else {
      this.subscriptions.push(
        this.productService.getProducts(this.currentPage, this.categoryId).subscribe(products => {
          this.products = products as Product[];
          this.getTotalPagesNumber(this.categoryId);
          console.log(this.products);
          // this.loadingService.hide();
        })
      );
    }
  }

  private getTotalPagesNumber(categoryId?: string): void {
    this.subscriptions.push(
      this.productService.getTotalPages(categoryId).subscribe(num => {
        this.totalPages = num;
        console.log(categoryId + " " + num + " " + this.totalPages);
        this.pages = [];
        for(let i=1; i<=this.totalPages; i++) {
          this.pages.push(i);
        }
        this.loadingService.hide();
      })
    );

  }

  public loadNext(): void {
    this.currentPage++;
    this.loadProducts();
  }

  public loadPrev(): void {
    this.currentPage--;
    this.loadProducts();
  }

  public _shortDescription(description: string): string {
    let end: number;
    for(let j: number = 100; j>0; j--) {
      if(description[j] === ' ') {
        end = j;
        break;
      }
    }
    let shortDesc = description.slice(0,end)
    shortDesc+='...';
    return shortDesc;
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
