import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {NgxSpinnerService} from "ngx-spinner";
import {SubscriptionService} from "../service/subscription/subscription.service";
import {Subscription} from "../model/subscription";

@Component({
  selector: 'app-subscriptions',
  templateUrl: './subscriptions.component.html',
  styleUrls: ['./subscriptions.component.css']
})
export class SubscriptionsComponent implements OnInit, OnDestroy {

  public editMode = false;

  public size: string = '10';
  public currentPage: number = 1;
  public totalPages: number;
  public pages: number[] = [];

  public _subscriptions: Subscription[];
  public editableSubscription: Subscription = new Subscription();
  public modalRef: BsModalRef;

  private subscriptions = [];

  constructor(
    private subscriptionService: SubscriptionService,
    private modalService: BsModalService,
    private loadingService: NgxSpinnerService,
  ) {}

  ngOnInit() {
    this.loadSubscriptions();
  }

  public _closeModal(): void {
    this.modalRef.hide();
  }

  public _openModal(template: TemplateRef<any>, subscription?: Subscription): void {
    if (subscription) {
      this.editMode = true;
      this.editableSubscription = Subscription.cloneBase(subscription);
    } else {
      this.refreshSubscription();
      this.editMode = false;
    }

    this.modalRef = this.modalService.show(template);
  }

  public _addSubscription(): void {
    this.loadingService.show();
    this.subscriptions.push(
      this.subscriptionService.saveSubscription(this.editableSubscription).subscribe(() => {
        this._updateSubscriptions();
        this.refreshSubscription();
        this._closeModal();
        this.loadingService.hide();
      })
    );
  }

  public _updateSubscriptions(): void {
    this.loadSubscriptions();
  }

  public _deleteSubscription(subscriptionId: string): void {
    this.loadingService.show();
    this.subscriptions.push(
      this.subscriptionService.deleteSubscription(subscriptionId).subscribe(() => {
        this._updateSubscriptions();
        this.loadingService.hide();
      })
    );
  }

  private refreshSubscription(): void {
    this.editableSubscription = new Subscription();
  }

  private loadSubscriptions(): void {
    this.loadingService.show();
    this.subscriptions.push(
      this.subscriptionService.getSubscriptionsPageByUserId(this.currentPage, this.size).subscribe(_subscriptions => {
        this._subscriptions = _subscriptions as Subscription[];
        this.getTotalPagesNumber();
        this.loadingService.hide();
      })
    );
  }

  private getTotalPagesNumber(): void {
    this.subscriptions.push(
      this.subscriptionService.getTotalPages(this.size).subscribe(num => {
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
    this.loadSubscriptions();
  }

  public loadPrev(): void {
    this.currentPage--;
    this.loadSubscriptions();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
