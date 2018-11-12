import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";

import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { TooltipModule } from "ngx-bootstrap/tooltip";
import { ModalModule } from "ngx-bootstrap/modal";
import { CollapseModule } from "ngx-bootstrap/collapse";
import { BsDatepickerModule } from "ngx-bootstrap/datepicker";
import { CarouselModule } from "ngx-bootstrap/carousel";
import { TabsModule } from 'ngx-bootstrap/tabs';

import { AppComponent } from "./app.component";
import { UserComponent } from "./user/user.component";
import { NavbarComponent } from "./navbar/navbar.component";
import { SigninModalComponent } from "./signin-modal/signin-modal.component";
import { CarouselComponent } from "./carousel/carousel.component";
import { TabsetComponent } from './tabset/tabset.component';
import { ProductsComponent } from './products/products.component';
import { RouterModule, Routes } from "@angular/router";
import { WalletTableComponent } from './walletTable/walletTable.component';

const appRoutes: Routes = [
  { path: ''}
]

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    NavbarComponent,
    SigninModalComponent,
    CarouselComponent,
    TabsetComponent,
    ProductsComponent,
    WalletTableComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot(),
    CollapseModule.forRoot(),
    BsDatepickerModule.forRoot(),
    CarouselModule.forRoot(),
    TabsModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
