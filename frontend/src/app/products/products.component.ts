import {Component, Input, OnDestroy, OnInit, TemplateRef} from "@angular/core";
import { Subscription } from "rxjs/internal/Subscription";
import { Product } from "../model/product";
import { ProductService } from "../service/product/product.service";
import { BsModalRef, BsModalService } from "ngx-bootstrap/modal";
import { NgxSpinnerService } from 'ngx-spinner';
import {ActivatedRoute} from "@angular/router";
import {CategoryService} from "../service/category/category.service";
import {toNumber} from "ngx-bootstrap/timepicker/timepicker.utils";
import {Category} from "../model/category";
import {User} from "../model/user";
import {UserService} from "../service/user/user.service";
import {AuthService} from "../service/auth/auth.service";

@Component({
  selector: "products",
  templateUrl: "./products.component.html",
  styleUrls: ["./products.component.css", "../app.component.css"]
})
export class ProductsComponent implements OnInit, OnDestroy {
  public editMode = false;
  // @Input()
  public categoryId: string;
  public editCategoryId: number;
  public currentPage: number = 1;
  public currentUser: User;

  public titleOfPage: string;
  public numberOfProductsPerPage: string = '6';
  public totalPages: number;
  public pages: number[] = [];
  public products: Product[];
  public categories: Category[];
  public editableProduct: Product = new Product();
  public modalRef: BsModalRef;

  private subscriptions: Subscription[] = [];

  constructor(
    private productService: ProductService,
    private categoryService: CategoryService,
    private authService: AuthService,
    private modalService: BsModalService,
    private loadingService: NgxSpinnerService,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.getId();
    this.loadCategories();
    this.loadUser();
    // this.loadProducts();
  }

  private getId(): void {
    this.loadingService.show();
    this.subscriptions.push(
    this.route.paramMap.subscribe(params => {
      if(!params.has('id')) {
        this.categoryId = '0';
        this.titleOfPage = 'All products';
      }
      else {
        this.categoryId = params.get('id');
        this.subscriptions.push(
          this.categoryService.getCategoryById(this.categoryId).subscribe(category => {
            this.titleOfPage = category.name;
          })
        );
      }
      this.loadProducts();
    }))
  }

  public _closeModal(): void {
    this.modalRef.hide();
  }

  public _openModal(template: TemplateRef<any>, product: Product): void {
    this.editMode = true;
    this.editableProduct = Product.cloneBase(product);
    this.editCategoryId = toNumber(this.editableProduct.category.id);

    this.modalRef = this.modalService.show(template);
  }

  public _addProduct(): void {
    this.editableProduct.category.id = this.editCategoryId.toString();
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
    // if(this.categoryId !== '0') {
    //   this.editableProduct.category.id = this.categoryId;
    // }
  }

  public loadProducts(): void {
    if (this.categoryId == '0') {
      this.subscriptions.push(
        this.productService.getProducts(this.currentPage, this.numberOfProductsPerPage).subscribe(products => {
          this.products = products as Product[];
          this.getTotalPagesNumber();
          // console.log(this.products);
        })
      );
    } else {
      this.subscriptions.push(
        this.productService.getProducts(this.currentPage, this.numberOfProductsPerPage, this.categoryId).subscribe(products => {
          this.products = products as Product[];
          this.getTotalPagesNumber(this.categoryId);
          console.log(this.products);
        })
      );
    }
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

  public loadUser(): void {
    this.subscriptions.push(
      this.authService.getUser().subscribe(user => {
        this.currentUser = user;
      })
    )
  }

  public _shortDescription(description: string): string {
    let end: number;
    for(let j: number = 100; j>0; j--) {
      if(description[j] === ' ') {
        end = j;
        break;
      }
    }
    let shortDesc = description.slice(0,end);
    shortDesc+='...';
    return shortDesc;
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
