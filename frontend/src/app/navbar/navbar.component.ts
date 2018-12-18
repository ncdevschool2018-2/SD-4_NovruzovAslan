import {Component, NgZone, OnDestroy, OnInit} from "@angular/core";
import {AuthService} from "../service/auth/auth.service";
import {User} from "../model/user";
import {Subscription} from "rxjs";
import {TokenStorage} from "../service/auth/token.storage";
import {Router} from "@angular/router";
import {Category} from "../model/category";
import {CategoryService} from "../service/category/category.service";

@Component({
  selector: "app-navbar",
  templateUrl: "./navbar.component.html",
  styleUrls: ["./navbar.component.css"]
})
export class NavbarComponent implements OnInit, OnDestroy {

  public role: string = '4';

  public categories: Category[] = [];

  private subscriptions: Subscription[] = [];

  constructor(private authService: AuthService,
              private categoryService: CategoryService,
              private storage: TokenStorage,
              private router: Router
  ) {}

  ngOnInit() {
    this.setRole();
  }

  setRole(): void {
    this.subscriptions.push(
      this.authService.getUser().subscribe( _user=>{
        if(_user)
          this.role = _user.role.id;
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

  ngOnDestroy() {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

}
