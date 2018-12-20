import {Component, OnDestroy, OnInit } from "@angular/core";
import { Subscription } from "rxjs/internal/Subscription";
import { BsModalRef, BsModalService } from "ngx-bootstrap/modal";
import { NgxSpinnerService } from 'ngx-spinner';
import {SpecialProduct} from "../model/specialProduct";
import {SpecialProductService} from "../service/special-product/special-product.service";

@Component({
  selector: "special-products",
  templateUrl: "./special-products.component.html",
  styleUrls: ["./special-products.component.css", "../app.component.css"]
})
export class SpecialProductsComponent implements OnInit, OnDestroy {
  public currentPage: number;

  public titleOfPage: string;
  public numberOfProductsPerPage: string = '6';
  public totalPages: number;
  public pages: number[] = [];
  public products: SpecialProduct[];
  public modalRef: BsModalRef;

  private subscriptions: Subscription[] = [];

  constructor(
    private productService: SpecialProductService,
    private modalService: BsModalService,
    private loadingService: NgxSpinnerService,
  ) {}

  ngOnInit() {
    this.loadProducts();
  }

  public _closeModal(): void {
    this.modalRef.hide();
  }

  public _updateProducts(): void {
    this.loadProducts();
  }

  public _deleteProduct(productId: string): void {
    this.subscriptions.push(
      this.productService.deleteSpecialProduct(productId).subscribe(() => {
        this._updateProducts();
      })
    );
  }

  public loadProducts(): void {
    this.subscriptions.push(
      this.productService.getSpecialProducts(this.currentPage, this.numberOfProductsPerPage).subscribe(products => {
        this.products = products as SpecialProduct[];
        this.getTotalPagesNumber();
        console.log(this.products);
      })
    );
  }

  private getTotalPagesNumber(categoryId?: string): void {
    this.subscriptions.push(
      this.productService.getTotalPages(this.numberOfProductsPerPage, categoryId).subscribe(num => {
        this.totalPages = num;
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
