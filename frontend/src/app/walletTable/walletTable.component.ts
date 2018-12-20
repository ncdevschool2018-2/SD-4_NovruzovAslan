import {Component, OnDestroy, OnInit, TemplateRef} from "@angular/core";
import { Wallet } from "../model/wallet";
import { User } from "../model/user";
import { BsModalRef, BsModalService } from "ngx-bootstrap/modal";
import { Subscription } from "rxjs/internal/Subscription";
import { WalletService } from "../service/wallet/wallet.service";
import { toNumber } from "ngx-bootstrap/timepicker/timepicker.utils";
import {NgxSpinnerService} from "ngx-spinner";

@Component({
  selector: "walletTable",
  templateUrl: "./walletTable.component.html",
  styleUrls: ["./walletTable.component.css"]
})
export class WalletTableComponent implements OnInit, OnDestroy {
  public editMode = false;

  public size: string = '10';
  public currentPage: number = 1;
  public totalPages: number;
  public pages: number[] = [];

  public wallets: Wallet[];
  public usersForSelect: User[];
  public editableWallet: Wallet;
  public modalRef: BsModalRef;

  public tempUserId: number;

  private subscriptions: Subscription[] = [];

  constructor(
    private walletService: WalletService,
    private modalService: BsModalService,
    private loadingService: NgxSpinnerService
  ) {
  }

  ngOnInit() {
    this._updateWallets();
  }

  public _closeModal(): void {
    this.modalRef.hide();
  }

  public _openModal(template: TemplateRef<any>, wallet: Wallet): void {
    if (wallet) {
      this.editMode = true;
      this.editableWallet = Wallet.cloneBase(wallet);
      this.tempUserId = toNumber(wallet.user.id);
    } else {
      this.refreshWallet();
      this.editMode = false;
      this.tempUserId = 1;
    }

    this.modalRef = this.modalService.show(template);
  }

  public _addWallet(): void {

    this.usersForSelect.forEach(user => {
      if( user.id == this.tempUserId.toString() ) {
        this.editableWallet.user = user;
      }
    });

    console.log(this.editableWallet);
    this.subscriptions.push(
      this.walletService.saveWallet(this.editableWallet).subscribe(() => {
        this._updateWallets();
        this._closeModal();
      })
    );
  }

  public _updateWallets(): void {
    this.loadingService.show();
    this.loadWallets();
    this.refreshWallet();
  }

  public _deleteWallet(walletId: string): void {
    this.subscriptions.push(
      this.walletService.deleteWallet(walletId).subscribe(() => {
        this._updateWallets();
      })
    );
  }

  private refreshWallet(): void {
    this.editableWallet = new Wallet();
    this.editableWallet.user = new User();
    this.editableWallet.value = 0;
    this.editableWallet.valute = '$';
  }

  private loadWallets(): void {
    this.subscriptions.push(
      this.walletService.getWalletsPageByUserId(this.currentPage, this.size).subscribe(wallets => {
        this.wallets = wallets as Wallet[];
        this.getTotalPagesNumber();
      })
    );
  }

  // private loadWallets(): void {
  //   this.subscriptions.push(
  //     this.walletService.getUsers().subscribe(users => {
  //       this.usersForSelect = users as User[];
  //       console.log(this.usersForSelect);
  //       this.loadingService.hide();
  //     })
  //   )
  // }

  private getTotalPagesNumber(): void {
    this.subscriptions.push(
      this.walletService.getTotalPages(this.size).subscribe(num => {
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
    this.loadWallets();
  }

  public loadPrev(): void {
    this.currentPage--;
    this.loadWallets();
  }


  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
