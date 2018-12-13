import {Component, OnDestroy, OnInit, TemplateRef} from "@angular/core";
import { Wallet } from "../model/wallet";
import { BsModalRef, BsModalService } from "ngx-bootstrap/modal";
import { Subscription } from "rxjs/internal/Subscription";
import { WalletService } from "../service/wallet/wallet.service";
import { UserService } from "../service/user/user.service";
import { User } from "../model/user";
import {NgxSpinnerService} from "ngx-spinner";
import {AuthService} from "../service/auth/auth.service";

@Component({
  selector: 'wallets',
  templateUrl: './wallets.component.html',
  styleUrls: ['./wallets.component.css', '../app.component.css']
})
export class WalletsComponent implements OnInit, OnDestroy {

  public wallets: Wallet[];
  public total: Wallet;
  private currentUser: User;

  public editMode: boolean = false;
  public editableWallet: Wallet;

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
    this.loadWallets();
    this.refreshWallet();
    this.total = new Wallet();
    this.total.value = 0;
    this.total.valute = '$';
    this.total.description = "Your total money from all your wallets";
    this.total.name = "Total";
    this.loadingService.hide();
  }

  public loadWallets() {
    this.subscriptions.push(
      this.authService.getUser().subscribe(user => {
        this.currentUser = user;
        this.subscriptions.push(
          this.walletService.getWalletsByUserId(user.id).subscribe(wallets => {
            this.wallets = wallets as Wallet[];
            this.total.value = 0;
            // todo: replace this forEach by normal forEach with valute conversion
            this.wallets.forEach(wallet => {
              this.total.value += wallet.value;
            })
          })
        )
      })
    )
  }

  public refreshWallet() {
    this.editableWallet = new Wallet();
    this.subscriptions.push(
      this.authService.getUser().subscribe( user => {
        this.editableWallet.user = user;
      })
    );
    // this.editableWallet.user = new User(); // todo: need to replace by current user
    this.editableWallet.value = 0;
    this.editableWallet.valute = '$';
    // this.subscriptions.push(
    //   this.userService.getUsers().subscribe(users => {
    //     let _users = users as User[];
    //     this.editableWallet.user = _users[0];
    //   })
    // )
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

  public _closeModal(): void {
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

  /*
   todo: this method will open modal window were will be next fields:
          - name of the wallet you want to delete (this will be select)
          - another wallet name (were you want to push money from the first wallet) (this will be also a select)
          - (+/-) also there will be a select menu near the second wallet name where you select valute of the second wallet (you can change it)
    */
  public _deleteWallet(template: TemplateRef<any>): void {
    this.modalRef = this.modalService.show(template);
    // this.editableWallet = wallet that we want to delete
    this.subscriptions.push(
      this.walletService.deleteWallet(this.editableWallet.id).subscribe(() => {
        this._updateWallets();
      })
    )
  }

  ngOnDestroy(): void{
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

}
