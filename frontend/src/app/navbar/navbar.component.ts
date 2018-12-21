import {Component, NgZone, OnDestroy, OnInit, TemplateRef} from "@angular/core";
import {AuthService} from "../service/auth/auth.service";
import {User} from "../model/user";
import {Subscription} from "rxjs";
import {TokenStorage} from "../service/auth/token.storage";
import {Router} from "@angular/router";
import {Category} from "../model/category";
import {CategoryService} from "../service/category/category.service";
import {toNumber} from "ngx-bootstrap/timepicker/timepicker.utils";
import {BsModalRef, BsModalService} from "ngx-bootstrap";
import {UserService} from "../service/user/user.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.css"]
})
export class NavbarComponent implements OnInit, OnDestroy {

  public role: string = '4';
  // public userName: string;
  public currentUser: User;
  public categoryName: string;

  public bestManager: User;

  public minDate: Date;
  public maxDate: Date;

  public categories: Category[] = [];
  public formGroup: FormGroup;
  public editUserGroup: FormGroup;

  public modalRef: BsModalRef;
  private subscriptions: Subscription[] = [];

  constructor(private authService: AuthService,
              private categoryService: CategoryService,
              private userService: UserService,
              private storage: TokenStorage,
              private modalService: BsModalService,
              private router: Router
  ) {}

  ngOnInit() {
    this.formGroup = new FormGroup({
      Name: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]/),
        Validators.maxLength(35)
      ])
    });
    this.editUserGroup = new FormGroup({
      Username: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]/),
        Validators.minLength(4),
        Validators.maxLength(35)
      ]),
      Name: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]/),
        Validators.maxLength(35)
      ]),
      DateOfBirth: new FormControl('', [
        Validators.required
      ])
    });
    this.setRole();
  }

  getBest(): void {
    this.subscriptions.push(
      this.userService.getBest().subscribe(user => {
        this.bestManager = user;
        console.log(this.bestManager);
      })
    )
  }

  setRole(): void {
    this.subscriptions.push(
      this.authService.getUser().subscribe( _user=>{
        if(_user) {
          this.role = _user.role.id;
          this.currentUser = _user;
          this.getFields();
        }
        console.log(_user.role.name);
        this.loadCategories();
      })
    )
  }

  loadCategories(): void {
    this.subscriptions.push(
      this.categoryService.getCategorys().subscribe(categories => {
        this.categories = categories;
      })
    )
  }

  signOut(): void {
    this.storage.signOut();
    this.setRole();
    this.router.navigate(['']);
  }

  public _openModal(template: TemplateRef<any>): void {
    this.modalRef = this.modalService.show(template);
  }

  editUser(): void {
    this.setFields();
    this.subscriptions.push(
      this.userService.saveUser(this.currentUser).subscribe(user => {
        this.currentUser = user;
      })
    )
  }

  private setDates(): void {
    this.minDate = new Date();
    this.minDate = new Date(this.minDate.getFullYear()-100);
    this.maxDate = new Date();
    this.maxDate.setFullYear(this.maxDate.getFullYear()-6);
    console.log(this.minDate + " " + this.maxDate);
  }

  addCategory(): void {
    this.subscriptions.push(
      this.categoryService.saveCategory(this.formGroup.value.Name).subscribe(() => {
        this._closeModal();
        this.loadCategories();
      })
    )
  }

  setFields(): void {
    this.currentUser.username = this.editUserGroup.value.Username;
    this.currentUser.userInfo.name = this.editUserGroup.value.Name;
    this.currentUser.userInfo.dateOfBirth = this.editUserGroup.value.DateOfBirth;
  }

  getFields(): void {
    this.editUserGroup.value.Username = this.currentUser.username;
    this.editUserGroup.value.Name = this.currentUser.userInfo.name;
    this.editUserGroup.value.DateOfBirth = this.currentUser.userInfo.dateOfBirth;
  }

  public _closeModal(): void {
    this.modalRef.hide();
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

}
