import {Component, NgZone, OnInit} from '@angular/core';
import { Router } from "@angular/router";
import { AuthService } from "../service/auth/auth.service";
import { TokenStorage } from "../service/auth/token.storage";
import {NavbarComponent} from "../navbar/navbar.component";

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public username: string;
  public password: string;
  // private navbar: NavbarComponent;


  constructor(private router: Router,
              private authService: AuthService,
              private zone: NgZone,
              private token: TokenStorage)
  {}

  ngOnInit() {
  }

  login(): void {
    this.authService.attemptAuth(this.username, this.password).subscribe(
      data => {
        this.token.saveToken(data.token);
        this.reloadPage();
        this.router.navigate(['']);
        // this.navbar.setRole();
      }
    );
  }

  reloadPage() {
    this.zone.runOutsideAngular(() => {
      location.reload();
    })
  }


}
