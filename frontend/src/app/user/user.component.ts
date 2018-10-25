import { Component, OnInit, TemplateRef } from "@angular/core";
import { User } from "../model/user";
import { BsModalRef, BsModalService } from "ngx-bootstrap/modal";
import { Subscription } from "rxjs/internal/Subscription";
import { UserService } from "../service/user/user.service";

@Component({
  selector: "app-users",
  templateUrl: "./user.component.html",
  styleUrls: ["./user.component.css"]
})
export class UserComponent implements OnInit {
  public editMode = false;

  public users: User[];
  public editableBa: User = new User();
  public modalRef: BsModalRef; //we need a variable to keep a reference of our modal. This is going to be used to close the modal.

  private subscriptions: Subscription[] = [];

  // Dependency injection for UserService into Billing
  constructor(
    private userService: UserService,
    private modalService: BsModalService
  ) {
    //to show the modal, we also need the ngx-bootstrap service
  }

  // Calls on component init
  ngOnInit() {
    this.loadUsers();
  }

  public _closeModal(): void {
    this.modalRef.hide();
  }

  public _openModal(template: TemplateRef<any>, user: User): void {
    if (user) {
      this.editMode = true;
      this.editableBa = User.cloneBase(user);
    } else {
      this.refreshBa();
      this.editMode = false;
    }

    this.modalRef = this.modalService.show(template); // and when the user clicks on the button to open the popup
    // we keep the modal reference and pass the template local name to the modalService.
  }

  public _addUser(): void {
    this.subscriptions.push(
      this.userService.saveUser(this.editableBa).subscribe(() => {
        this._updateUsers();
        this.refreshBa();
        this._closeModal();
      })
    );
  }

  public _updateUsers(): void {
    this.loadUsers();
  }

  public _deleteUser(userId: string): void {
    this.subscriptions.push(
      this.userService.deleteUser(userId).subscribe(() => {
        this._updateUsers();
      })
    );
  }

  private refreshBa(): void {
    this.editableBa = new User();
  }

  private loadUsers(): void {
    // Get data from UserService
    this.subscriptions.push(
      this.userService.getUsers().subscribe(accounts => {
        // Parse json response into local array
        this.users = accounts as User[];
        // Check data in console
        console.log(this.users); // don't use console.log in angular :)
      })
    );
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }
}
