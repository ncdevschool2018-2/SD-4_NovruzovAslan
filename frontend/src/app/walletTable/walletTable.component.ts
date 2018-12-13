import {Component, OnDestroy, OnInit, TemplateRef} from "@angular/core";
import { Wallet } from "../model/wallet";
import { User } from "../model/user";
import { BsModalRef, BsModalService } from "ngx-bootstrap/modal";
import { Subscription } from "rxjs/internal/Subscription";
import { WalletService } from "../service/wallet/wallet.service";
import { UserService } from "../service/user/user.service";
import { toNumber } from "ngx-bootstrap/timepicker/timepicker.utils";
import {NgxSpinnerService} from "ngx-spinner";

@Component({
  selector: "walletTable",
  templateUrl: "./walletTable.component.html",
  styleUrls: ["./walletTable.component.css"]
})
export class WalletTableComponent implements OnInit, OnDestroy {
  public editMode = false;

  public wallets: Wallet[];
  public usersForSelect: User[];
  public editableWallet: Wallet;
  public modalRef: BsModalRef;

  public tempUserId: number;

  private subscriptions: Subscription[] = [];

  constructor(
    private walletService: WalletService,
    private modalService: BsModalService,
    private userService: UserService,
    private loadingService: NgxSpinnerService
  ) {
  }

  ngOnInit() {
    // this.loadWallets();
    // this.loadUsers();
    // this.editableWallet.user = new User();
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
    // this.loadUsers();
    this.refreshWallet();
    // this.loadingService.hide();
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
      this.walletService.getWallets().subscribe(wallets => {
        this.wallets = wallets as Wallet[];
        console.log(this.wallets);
        this.loadUsers();
      })
    );
  }

  private loadUsers(): void {
    this.subscriptions.push(
      this.userService.getUsers().subscribe(users => {
        this.usersForSelect = users as User[];
        console.log(this.usersForSelect);
        this.loadingService.hide();
      })
    )
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
