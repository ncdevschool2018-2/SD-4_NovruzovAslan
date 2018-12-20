import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {Product} from "../model/product";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {Wallet} from "../model/wallet";
import {User} from "../model/user";
import {ActivatedRoute, Router} from "@angular/router";
import {ProductService} from "../service/product/product.service";
import {WalletService} from "../service/wallet/wallet.service";
import {SubscriptionService} from "../service/subscription/subscription.service";
import {AuthService} from "../service/auth/auth.service";
import {NgxSpinnerService} from "ngx-spinner";
import {CategoryService} from "../service/category/category.service";
import {Subscription} from "rxjs";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Category} from "../model/category";

@Component({
  selector: 'app-manager-products',
  templateUrl: './manager-products.component.html',
  styleUrls: ['./manager-products.component.css']
})
export class ManagerProductsComponent implements OnInit, OnDestroy {
  public editMode = false;
  // @Input()
  public currentPage: number = 1;
  private currentManager: User;
  public formGroup: FormGroup;

  public categories: Category[];
  public wallets: Wallet[];

  public numberOfProductsPerPage: string = '5';
  public totalPages: number;
  public pages: number[] = [];
  public products: Product[];
  public editableProduct: Product = new Product();
  public modalRef: BsModalRef;

  private subscriptions: Subscription[] = [];

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private walletService: WalletService,
    private authService: AuthService,
    private modalService: BsModalService,
    private loadingService: NgxSpinnerService,
  ) {
  }

  ngOnInit() {
    this.formGroup = new FormGroup({
      Name: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]/),
        Validators.maxLength(35)
      ]),
      Description: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]/),
        Validators.maxLength(250)
      ]),
      Cost: new FormControl('', [
        Validators.required,
        Validators.maxLength(5)
      ]),
      Category: new FormControl('', [
        Validators.required
      ]),
      Wallet: new FormControl('', [
        Validators.required
      ])
    });
    this.loadCategories();
    this.loadMaker();
  }

  onSubmit() {
    this._addProduct();
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

    this.modalRef = this.modalService.show(template);
  }

  public _addProduct(): void {
    this.setFields();
    console.log(this.editableProduct);
    this.subscriptions.push(
      this.productService.saveProduct(this.editableProduct).subscribe(() => {
        this._updateProducts();
        this.refreshProduct();
        this._closeModal();
      })
    );
  }

  private setFields(): void {
    this.editableProduct.name = this.formGroup.value.Name;
    this.editableProduct.description = this.formGroup.value.Description;
    this.editableProduct.cost = this.formGroup.value.Cost;
    this.editableProduct.wallet = new Wallet();
    this.editableProduct.category = new Category();
    this.editableProduct.wallet.id = this.formGroup.value.Wallet;
    this.editableProduct.category.id = this.formGroup.value.Category;
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

  public loadProducts(): void {
    this.loadingService.show();
    this.subscriptions.push(
      this.productService.getOwnProducts(this.currentPage, this.numberOfProductsPerPage, this.currentManager.id).subscribe(products => {
        this.products = products as Product[];
        this.getTotalPagesNumber();
        console.log(this.products);
      })
    );
  }

  private getTotalPagesNumber(): void {
    this.subscriptions.push(
      this.productService.getTotalPages(this.numberOfProductsPerPage, null, this.currentManager.id).subscribe(num => {
        this.totalPages = num;
        this.pages = [];
        for (let i = 1; i <= this.totalPages; i++) {
          this.pages.push(i);
        }
        this.loadingService.hide();
      })
    )
  }

  private loadMaker(): void {
    this.subscriptions.push(
      this.authService.getUser().subscribe(user => {
        this.currentManager = user;
        this.loadProducts();
        this.loadWallets();
      })
    )
  }

  private loadWallets(): void {
    this.subscriptions.push(
      this.walletService.getWalletsByUserId(this.currentManager.id).subscribe(wallets => {
        this.wallets = wallets;
      })
    )
  }

  private loadCategories(): void {
    this.subscriptions.push(
      this.categoryService.getCategorys().subscribe(categories => {
        this.categories = categories;
      })
    )
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
    for (let j: number = 100; j > 0; j--) {
      if (description[j] === ' ') {
        end = j;
        break;
      }
    }
    let shortDesc = description.slice(0, end)
    shortDesc += '...';
    return shortDesc;
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
