import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {SubscriptionService} from "../service/subscription/subscription.service";
import {AuthService} from "../service/auth/auth.service";
import {User} from "../model/user";
import {NgxSpinnerService} from "ngx-spinner";
import {Subscription} from "../model/subscription";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {WalletService} from "../service/wallet/wallet.service";
import {Wallet} from "../model/wallet";

@Component({
  selector: 'my-subscriptions',
  templateUrl: './my-subscriptions.component.html',
  styleUrls: ['./my-subscriptions.component.css']
})
export class MySubscriptionsComponent implements OnInit, OnDestroy {

  public _subscriptions: Subscription[] = [];
  public size: string = '6';
  public currentPage: number = 1;
  public totalPages;
  public pages: number[] = [];

  private currentUser: User;
  public wallets: Wallet[] = [];

  public modalRef: BsModalRef;
  public editableSubscription: Subscription;

  private subscriptions = [];


  constructor(private subscriptionService: SubscriptionService,
              private authService: AuthService,
              private walletService: WalletService,
              private modalService: BsModalService,
              private loadingService: NgxSpinnerService
  ) {}

  ngOnInit() {
    this.loadUser();
  }

  private loadUser(): void {
    this.subscriptions.push(
      this.authService.getUser().subscribe(user => {
        this.currentUser = user;
        this.loadWallets();
        this.loadSubscriptions();
      })
    )
  }

  private loadSubscriptions(): void {
    this.loadingService.show();
    this.subscriptions.push(
      this.subscriptionService.getSubscriptionsPageByUserId(this.currentPage, this.size, this.currentUser.id).subscribe(subs => {
        this._subscriptions = subs as Subscription[];
        this.getTotalPagesNumber();
      })
    );
  }

  private loadWallets(): void {
    this.subscriptions.push(
      this.walletService.getWalletsByUserId(this.currentUser.id).subscribe(wallets => {
        this.wallets = wallets as Wallet[];
      })
    )
  }

  private getTotalPagesNumber(): void {
    this.subscriptions.push(
      this.subscriptionService.getTotalPages(this.size, this.currentUser.id).subscribe(num => {
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
    let shortDesc = description.slice(0,end);
    shortDesc+='...';
    return shortDesc;
  }

  public loadNext(): void {
    this.currentPage++;
    this.loadSubscriptions();
  }

  public loadPrev(): void {
    this.currentPage--;
    this.loadSubscriptions();
  }

  public changeTerms(): void {
    this.subscriptions.push(
      this.subscriptionService.saveSubscription(this.editableSubscription).subscribe(() => {
        this.loadWallets();
        this.loadSubscriptions();
        this._closeModal();
      })
    )
  }

  public _openModal(template: TemplateRef<any>, subscription: Subscription): void {
    this.editableSubscription = Subscription.cloneBase(subscription);

    this.modalRef = this.modalService.show(template);
  }

  public _closeModal(): void {
    this.modalRef.hide();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }


}
