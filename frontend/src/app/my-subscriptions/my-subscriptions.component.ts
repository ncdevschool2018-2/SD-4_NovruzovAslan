import {Component, OnDestroy, OnInit} from '@angular/core';
import {Product} from "../model/product";
import {Subscription} from "rxjs";
import {Page} from "../model/page";
import {SubscriptionService} from "../service/subscription/subscription.service";
import {AuthService} from "../service/auth/auth.service";
import {User} from "../model/user";
import {NgxSpinnerService} from "ngx-spinner";

@Component({
  selector: 'my-subscriptions',
  templateUrl: './my-subscriptions.component.html',
  styleUrls: ['./my-subscriptions.component.css']
})
export class MySubscriptionsComponent implements OnInit, OnDestroy {

  public products: Product[];
  public currentPage: number = 1;
  private currentUser: User;

  public totalPages;
  public pages: number[] = [];

  private subscriptions: Subscription[] = [];


  constructor(private subscriptionService: SubscriptionService,
              private authService: AuthService,
              private loadingService: NgxSpinnerService
  ) {}

  ngOnInit() {
    this.loadUser();
  }

  private loadUser(): void {
    this.loadingService.show();
    this.subscriptions.push(
      this.authService.getUser().subscribe(user => {
        this.currentUser = user;
        this.loadProducts();
      })
    )
  }

  public log(smth): void {
    console.log(smth);
  }

  private loadProducts(): void {
    this.subscriptions.push(
      this.subscriptionService.getProductsByUserId(this.currentPage, this.currentUser.id).subscribe(subs => {
        // subs.forEach(sub=>{
        //   this.products.push(sub.)
        // })
        // this.products = products as Product[];
        // console.log(this.products);
        // this.totalPages = true;
        this.getTotalPagesNumber();
      })
    );
  }

  private getTotalPagesNumber(): void {
    this.subscriptions.push(
      this.subscriptionService.getTotalPages(this.currentUser.id).subscribe(num => {
        this.totalPages = num;
        this.pages = [];
        for(let i=1; i<=this.totalPages; i++) {
          this.pages.push(i);
        }
        this.loadingService.hide();
      })
    );

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

  public loadNext(): void {
    this.currentPage++;
    this.loadProducts();
  }

  public loadPrev(): void {
    this.currentPage--;
    this.loadProducts();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }


}
