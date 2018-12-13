import {Component, NgZone, OnDestroy, OnInit} from "@angular/core";
import {AuthService} from "../service/auth/auth.service";
import {User} from "../model/user";
import {Subscription} from "rxjs";
import {TokenStorage} from "../service/auth/token.storage";
import {Router} from "@angular/router";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.css"]
})
export class NavbarComponent implements OnInit, OnDestroy {

  // public isAdmin: boolean = false;
  // public isManager: boolean = false;
  // public isUser: boolean = false;
  // public isGuest: boolean = true;

  public role: string = '4';

  private subscriptions: Subscription[] = [];

  constructor(private authService: AuthService,
              private storage: TokenStorage,
              private router: Router) {}

  ngOnInit() {
    this.setRole();
  }

  setRole(): void {
    this.subscriptions.push(
      this.authService.getUser().subscribe( _user=>{
        if(_user)
          this.role = _user.role.id;
        console.log(_user.role.name);
      })
    )

  }

  // signIn(): void {
  //   this.router.navigate(['login']);
  //   this.setRole();
  // }

  signOut(): void {
    this.storage.signOut();
    this.setRole();
    this.router.navigate(['']);
  }

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

}
