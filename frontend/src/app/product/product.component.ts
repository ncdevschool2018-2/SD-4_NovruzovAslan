import { Component, OnDestroy, OnInit, TemplateRef } from '@angular/core';
import { ProductService } from "../service/product/product.service";
import { Product } from "../model/product";
import { Subscription } from "../model/subscription";
import { BsModalRef, BsModalService } from "ngx-bootstrap";
import { Wallet } from "../model/wallet";
import { WalletService } from "../service/wallet/wallet.service";
import { AuthService } from "../service/auth/auth.service";
import { SubscriptionService } from "../service/subscription/subscription.service";
import { User } from "../model/user";

import { ActivatedRoute, Router } from "@angular/router";
import {NgxSpinnerService} from "ngx-spinner";

@Component({
  selector: 'product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit, OnDestroy {
  public prodId: string;
  public product: Product;
  public subscription: Subscription;
  public modalRef: BsModalRef;
  public isSubscribed: boolean = false;
  // public _wallet: Wallet;
  public wallets: Wallet[];

  private subscOfCurrentUserId: string;
  private currentUser: User;
  public userWalletId: string;

  private subscriptions = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private productService: ProductService,
    private walletService: WalletService,
    private subscriptionService: SubscriptionService,
    private authService: AuthService,
    private modalService: BsModalService,
    private loadingService: NgxSpinnerService
  ) {}

  ngOnInit() {
    this.getId();
  }

  private getId(): void {
    // this.loadingService.show();
    this.subscriptions.push(
      this.route.paramMap.subscribe(params => {
        this.prodId = params.get('id');
        // this.loadProduct();
        this.setUser();
        // this.loadingService.hide();
      })
    );
  }

  private loadProduct(): void {
    // this.loadingService.show();
    this.subscriptions.push(
      this.productService.getProductById(this.prodId).subscribe(
        product => {
          this.product = product as Product;
          console.log(this.product);
          this.loadingService.hide();
        }
      )
    );
  }

  private setUser(): void {
    this.loadingService.show();
    this.subscriptions.push(
      this.authService.getUser().subscribe(user => {
          this.currentUser = user;
          if(this.currentUser.role.id != '4')
            this.subscriptions.push(
              this.subscriptionService.getAllSubscriptionsByUserId(user.username).subscribe(
                subs => {
                  subs.forEach(sub => {
                    if(sub.product.id.toString() == this.prodId) {
                      this.isSubscribed = true;
                      this.subscOfCurrentUserId = sub.id;
                    }
                  });
                  this.loadProduct();
                }
              )
            );
        else this.loadProduct();
        }
      )
    );
  }

  subscribe(): void {
    this.subscription.active = true;
    // this.subscription.userWallet = this._wallet;
    this.subscription.product.id = this.product.id;
    console.log(this.subscription);
    this.subscription.userWallet = new Wallet();
    this.subscription.userWallet.id = this.userWalletId;
    console.log(this.subscription);
    this.subscriptions.push(
      this.subscriptionService.saveSubscription(this.subscription).subscribe(() => {
        this.modalRef.hide();
        this.isSubscribed = true;
        // this.router.navigate(['/subscriptions']);
      })
    )
  }

  unsubscribe(): void {
    console.log(this.subscOfCurrentUserId);
    this.loadingService.show();
    this.subscriptions.push(
     // this.subscriptionService.unsubscribe(this.prodId, this.currentUser.id).subscribe(() => {
      this.subscriptionService.deleteSubscription(this.subscOfCurrentUserId).subscribe(() => {
        this.loadingService.hide();
        this.isSubscribed = false;
        // this.router.navigate(['']);
      })
    )
  }

  public _openModal(template: TemplateRef<any>): void {
    if (!this.currentUser.id)
      this.router.navigate(['/login']);
    else {
      this.loadingService.show();
      this.subscriptions.push(
        this.walletService.getWalletsByUserId(this.currentUser.id).subscribe(
          wallets => {
            this.subscription = new Subscription();
            this.subscription.product = this.product;
            this.wallets = wallets as Wallet[];
            this.loadingService.hide();
            this.modalRef = this.modalService.show(template);
          }
        )
      );
    }
  }

  public _closeModal(): void {
    this.modalRef.hide();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }


}
