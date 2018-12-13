import { Component, TemplateRef } from "@angular/core";
import { BsModalRef, BsModalService } from "ngx-bootstrap/modal";
import { UserService } from "../service/user/user.service";
import { Router } from "@angular/router";
import { AuthService } from "../service/auth/auth.service";
import { TokenStorage } from "../service/auth/token.storage";
import { User } from "../model/user";

@Component({
  selector: "signin-modal",
  templateUrl: "./signin-modal.component.html",
  styleUrls: ["./signin-modal.component.css"]
})
export class SigninModalComponent {
  modalRef: BsModalRef;
  registerModalRef: BsModalRef;

  username: string;
  password: string;
  editableUser: User;
  confPass: string;

  private subscriptions;

  constructor(private userService: UserService,
              private modalService: BsModalService,
              private router: Router,
              private authService: AuthService,
              private token: TokenStorage)
  {}

  openModal(template: TemplateRef<any>) {
    if (this.registerModalRef) {
      this.registerModalRef.hide();
    }
    this.modalRef = this.modalService.show(template);
  }

  openRegisterModal(template: TemplateRef<any>) {
    if (this.modalRef) {
      this.modalRef.hide();
    }
    this.registerModalRef = this.modalService.show(template, { class: "second" });
  }

  login(): void {
    this.subscriptions.push(
      this.authService.attemptAuth(this.username, this.password).subscribe(
        data => {
          if (this.modalRef) {
            this.modalRef.hide();
          }
          this.token.saveToken(data.token);
          this.router.navigate(['user']);
        }
      )
    );
  }

  register(): void {
    this.subscriptions.push(
      this.authService.signUp(this.editableUser).subscribe(
        () => {
          if (this.registerModalRef) {
            this.registerModalRef.hide();
          }
          this.username = this.editableUser.username;
          this.password = this.editableUser.password;
          this.login();
        }
      )
    );
  }

}
