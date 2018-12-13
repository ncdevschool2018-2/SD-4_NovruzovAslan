import {Component, NgZone, OnDestroy, OnInit} from '@angular/core';
import {User} from "../model/user";
import {Router} from "@angular/router";
import {AuthService} from "../service/auth/auth.service";
import {TokenStorage} from "../service/auth/token.storage";
import {Role} from "../model/role";
import {UserInfo} from "../model/userInfo";

@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit, OnDestroy {

  public editableUser: User;
  public confPass: string;
  private subscriptions = [];


  constructor(private router: Router,
              private authService: AuthService,
              private zone: NgZone,
              private token: TokenStorage)
  { }

  ngOnInit() {
    this.refreshUser();
  }

  private refreshUser(): void {
    this.editableUser = new User();
    this.editableUser.role = new Role();
    this.editableUser.userInfo = new UserInfo();
  }

  register(): void {
    this.subscriptions.push(
      this.authService.signUp(this.editableUser).subscribe(
        () => {
          this.authService.attemptAuth(this.editableUser.username, this.editableUser.password).subscribe(
            data => {
              this.token.saveToken(data.token);
              this.reloadPage();
              this.router.navigate(['']);
            }
          );
        }
      )
    );
  }

  reloadPage() {
    this.zone.runOutsideAngular(() => {
      location.reload();
    })
  }


  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }


}
