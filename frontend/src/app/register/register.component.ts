import {Component, NgZone, OnDestroy, OnInit} from '@angular/core';
import {User} from "../model/user";
import {Router} from "@angular/router";
import {AuthService} from "../service/auth/auth.service";
import {TokenStorage} from "../service/auth/token.storage";
import {Role} from "../model/role";
import {UserInfo} from "../model/userInfo";
import {FormControl, FormGroup, ValidationErrors, Validators} from "@angular/forms";

@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy {

  public editableUser: User;
  public confPass: string;
  private subscriptions = [];

  minDate: Date;
  maxDate: Date;

  formGroup: FormGroup;
  public incorrect: boolean = false;


  constructor(private router: Router,
              private authService: AuthService,
              private zone: NgZone,
              private token: TokenStorage)
  { }

  ngOnInit() {
    this.formGroup = new FormGroup({
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
      Password: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(20)
      ]),
      // ConfirmPassword: new FormControl('', [
      //   Validators.required,
      //   // this.confirmPasswordValidator
      // ]),
      DateOfBirth: new FormControl('', [
        Validators.required
      ])
    });
    this.setDates();
    // this.refreshUser();
  }

  onSubmit() {
    this.register();
  }

  register(): void {
    this.setFields();
    this.subscriptions.push(
      this.authService.signUp(this.editableUser).subscribe(
        newUser => {
          if(newUser!=null) {
            this.authService.attemptAuth(this.editableUser.username, this.editableUser.password).subscribe(
              data => {
                this.token.saveToken(data.token);
                this.reloadPage();
                this.router.navigate(['']);
              }
            );
          } else this.incorrect = true;
        }
      )
    );
  }

  private setFields(): void {
    this.refreshUser();
    this.editableUser.userInfo.name = this.formGroup.value.Name;
    this.editableUser.userInfo.dateOfBirth = this.formGroup.value.DateOfBirth;
    this.editableUser.username = this.formGroup.value.Username;
    this.editableUser.password = this.formGroup.value.Password;
  }

  private refreshUser(): void {
    this.editableUser = new User();
    this.editableUser.role = new Role();
    this.editableUser.userInfo = new UserInfo();
  }

  private setDates(): void {
    this.minDate = new Date();
    this.minDate = new Date(this.minDate.getFullYear()-100);
    this.maxDate = new Date();
    this.maxDate.setFullYear(this.maxDate.getFullYear()-6);
    console.log(this.minDate + " " + this.maxDate);
  }

  closeAlert(): void {
    this.incorrect = false;
  }

  // private confirmPasswordValidator(): ValidationErrors {
  //   const confPasValid = this.formGroup.value.Password == this.formGroup.value.ConfirmPassword;
  //
  //   if(!confPasValid) {
  //     return { invalidConfPassword: 'Incorrect confirm password' };
  //   }
  //   return null;
  // }

  reloadPage() {
    this.zone.runOutsideAngular(() => {
      location.reload();
    })
  }


  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }


}
