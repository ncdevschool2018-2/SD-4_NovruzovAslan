import { Component, OnDestroy, OnInit, TemplateRef } from "@angular/core";
import { Wallet } from "../model/wallet";
import { BsModalRef, BsModalService } from "ngx-bootstrap/modal";
import { Subscription } from "rxjs/internal/Subscription";
import { WalletService } from "../service/wallet/wallet.service";
import { UserService } from "../service/user/user.service";
import { User } from "../model/user";
import { NgxSpinnerService } from "ngx-spinner";
import { AuthService } from "../service/auth/auth.service";

@Component({
  selector: 'wallets',
  templateUrl: './wallets.component.html',
  styleUrls: ['./wallets.component.css', '../app.component.css']
})
export class WalletsComponent implements OnInit, OnDestroy {

  public currentPage: number = 1;
  public size: string = '5';
  public totalPages: number;
  public pages: number[] = [];

  public wallets: Wallet[];
  public walletsForTransfer: Wallet[] = [];
  public total: Wallet;
  private currentUser: User;
  public incorrect: boolean = false;

  public editMode: boolean = false;
  public editableWallet: Wallet;
  public transferWalletId: number;
  public amountForTransfer: number;

  public modalRef: BsModalRef;
  private subscriptions: Subscription[] = [];

  constructor(
    private walletService: WalletService,
    private modalService: BsModalService,
    private userService: UserService,
    private authService: AuthService,
    private loadingService: NgxSpinnerService
  ) { }

  ngOnInit() {
    this._updateWallets();
  }

  public _updateWallets() {
    this.loadingService.show();
    this.total = new Wallet();
    this.total.value = 0;
    this.total.valute = '$';
    this.total.description = "Your total money from all your wallets";
    this.total.name = "Total";
    this.amountForTransfer = null;
    this.transferWalletId = null;
    this.walletsForTransfer = [];
    this.loadWallets();
    this.refreshWallet();
  }

  public loadWallets() {
    this.subscriptions.push(
      this.authService.getUser().subscribe(user => {
        this.currentUser = user;
        this.subscriptions.push(
          this.walletService.getWalletsPageByUserId(this.currentPage, this.size, user.id).subscribe(wallets => {
            this.wallets = wallets as Wallet[];
            this.total.value = 0;
            // todo: replace this forEach by normal forEach with valute conversion
            this.wallets.forEach(wallet => {
              this.total.value += wallet.value;
            });
            this.getTotalPagesNumber(this.currentUser.id);
            // this.loadingService.hide();
          })
        )
      })
    )
  }

  private getTotalPagesNumber(userId?: string): void {
    this.subscriptions.push(
      this.walletService.getTotalPages(this.size, userId).subscribe(num => {
        this.totalPages = num;
        this.pages = [];
        for(let i=1; i<=this.totalPages; i++) {
          this.pages.push(i);
        }
        this.loadingService.hide();
      })
    );
  }

  public refreshWallet() {
    this.editableWallet = new Wallet();
    this.editableWallet.value = 0;
    this.editableWallet.valute = '$';this.subscriptions.push(
      this.authService.getUser().subscribe( user => {
        if(this.editableWallet)
          this.editableWallet.user = user;
      })
    );
  }

  public _openModal(template: TemplateRef<any>, wallet?: Wallet): void {
    if (wallet) {
      this.editMode = true;
      this.editableWallet = Wallet.cloneBase(wallet);
    } else {
      this.refreshWallet();
      this.editMode = false;
    }
    this.modalRef = this.modalService.show(template);
  }

  public _closeDeleteModal(): void {
    if(this.editableWallet)
      this.wallets.push(this.editableWallet);
    this.modalRef.hide();
  }

  public _closeModal(): void {
    this.walletsForTransfer = [];
    this.incorrect = false;
    this.amountForTransfer = null;
    this.modalRef.hide();
  }

  public _addWallet(): void {
    this.subscriptions.push(
      this.walletService.saveWallet(this.editableWallet).subscribe(() => {
        this._updateWallets();
        this._closeModal();
      })
    )
  }

  public _openDeleteModal(template: TemplateRef<any>, wallet: Wallet): void {
    this.loadingService.show();
    this.editableWallet = Wallet.cloneBase(wallet);
    this.wallets.splice(this.wallets.indexOf(wallet),1);
    this.loadingService.hide();
    this.modalRef = this.modalService.show(template);
  }

  public _deleteWallet(): void {
    this.loadingService.show();
    this.subscriptions.push(
      this.walletService.transaction(this.editableWallet.id, this.transferWalletId.toString(), this.editableWallet.value).subscribe(() => {
        this._closeModal();
        this.subscriptions.push(
          this.walletService.deleteWallet(this.editableWallet.id).subscribe(() => {
            this._updateWallets();
            this.editableWallet = null;
            // this.loadingService.hide();
          }))
      }))
  }

  public _openTransferModal(template: TemplateRef<any>, wallet: Wallet): void {
    this.loadingService.show();
    this.editableWallet = Wallet.cloneBase(wallet);
    this.wallets.forEach(wal=> {
      if(wal != wallet)
        this.walletsForTransfer.push(wal);
    });
    this.loadingService.hide();
    this.modalRef = this.modalService.show(template);
  }

  public _transaction(): void {
    if(this.amountForTransfer>this.editableWallet.value || this.amountForTransfer<=0) {
      this.incorrect = true;
      return;
    }
    this.subscriptions.push(
      this.walletService.transaction(this.editableWallet.id, this.transferWalletId.toString(), this.amountForTransfer).subscribe(() => {
        this._closeModal();
        this._updateWallets();
      })
    )
  }

  public _topUp(): void {
    if(this.amountForTransfer<=0) {
      this.incorrect = true;
      // this.modalRef.hide();
      return;
    }
    this.subscriptions.push(
      this.walletService.transaction(null, this.editableWallet.id, this.amountForTransfer).subscribe(() => {
        this._closeModal();
        this._updateWallets();
      }))
  }

  closeAlert(): void {
    this.incorrect = false;
  }

  public loadNext(): void {
    this.currentPage++;
    this.loadWallets();
  }

  public loadPrev(): void {
    this.currentPage--;
    this.loadWallets();
  }

  ngOnDestroy(): void{
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

}
