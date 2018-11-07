import { Component, OnInit, TemplateRef } from "@angular/core";
import { BsModalRef, BsModalService } from "ngx-bootstrap/modal";
import {User} from "../model/user";
import {AppComponent} from "../app.component";
import {UserService} from "../service/user/user.service";

@Component({
  selector: "signin-modal",
  templateUrl: "./signin-modal.component.html",
  styleUrls: ["./signin-modal.component.css"]
})
export class SigninModalComponent {
  modalRef: BsModalRef;
  registerModalRef: BsModalRef;
  // userService: UserService;

  constructor(private userService: UserService,private modalService: BsModalService) {}

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

  setCurrentUser(event: Event) {
    let _users: User[];
    console.log("started");
    this.userService.getUsers().subscribe(users => {
      _users = users as User[];
      console.log("middle");
      let email: string = document.getElementById('emailInput').innerText;
      let password: string = document.getElementById('passwordInput').innerText;
      console.log(email+" "+password);
      for(let user of _users) {
        // console.log(user);
        if(user.password == password && user.username == email) {
          AppComponent.currentUser = user;
          console.log("This user " + user);
        }
      }
      this.modalRef.hide();
    });
    console.log("ended");

  }
}
