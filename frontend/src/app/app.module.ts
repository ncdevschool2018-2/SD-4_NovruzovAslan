import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
// import { BsDropdownModule } from 'ngx-bootstrap/dropdown';
import { FormsModule } from "@angular/forms";
import { BsDropdownModule } from "ngx-bootstrap/dropdown";
import { TooltipModule } from "ngx-bootstrap/tooltip";
import { ModalModule } from "ngx-bootstrap/modal";

import { AppComponent } from "./app.component";
import { UserComponent } from "./user/user.component";
import { HttpClientModule } from "@angular/common/http";

@NgModule({
  declarations: [AppComponent, UserComponent],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    // BsDropDownModule.forRoot(),
    BsDropdownModule.forRoot(),
    TooltipModule.forRoot(),
    ModalModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
