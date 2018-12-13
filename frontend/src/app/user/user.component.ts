import { Component, OnDestroy, OnInit, TemplateRef } from "@angular/core";
import { User } from "../model/user";
import { BsModalRef, BsModalService } from "ngx-bootstrap/modal";
import { Subscription } from "rxjs/internal/Subscription";
import { UserService } from "../service/user/user.service";
import {NgxSpinnerService} from "ngx-spinner";

@Component({
  selector: "app-users",
  templateUrl: "./user.component.html",
  styleUrls: ["./user.component.css"]
})
export class UserComponent implements OnInit, OnDestroy {
  public editMode = false;

  public users: User[];
  public editableUser: User = new User();
  public modalRef: BsModalRef;

  private subscriptions: Subscription[] = [];

  constructor(
    private userService: UserService,
    private modalService: BsModalService,
    private loadingService: NgxSpinnerService,
  ) {}

  ngOnInit() {
    this.loadUsers();
  }

  public _closeModal(): void {
    this.modalRef.hide();
  }

  public _openModal(template: TemplateRef<any>, user?: User): void {
    if (user) {
      this.editMode = true;
      this.editableUser = User.cloneBase(user);
    } else {
      this.refreshUser();
      this.editMode = false;
    }

    this.modalRef = this.modalService.show(template);
  }

  public _addUser(): void {
    this.loadingService.show();
    this.subscriptions.push(
      this.userService.saveUser(this.editableUser).subscribe(() => {
        this._updateUsers();
        this.refreshUser();
        this._closeModal();
        this.loadingService.hide();
      })
    );
  }

  public _updateUsers(): void {
    this.loadUsers();
  }

  public _deleteUser(userId: string): void {
    this.loadingService.show();
    this.subscriptions.push(
      this.userService.deleteUser(userId).subscribe(() => {
        this._updateUsers();
        this.loadingService.hide();
        // todo: check how it will work, because there is loadingService using in updateUsers method
      })
    );
  }

  private refreshUser(): void {
    this.editableUser = new User();
  }

  private loadUsers(): void {
    this.loadingService.show();
    this.subscriptions.push(
      this.userService.getUsers().subscribe(users => {
        this.users = users as User[];
        console.log(this.users);
        this.loadingService.hide();
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
