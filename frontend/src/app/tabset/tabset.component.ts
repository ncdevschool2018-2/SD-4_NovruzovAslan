import { Component, OnInit } from '@angular/core';

import { ProductsComponent } from '../products/products.component';
import {TabDirective} from "ngx-bootstrap/tabs";

@Component({
  selector: 'app-tabset',
  templateUrl: './tabset.component.html',
  styleUrls: ['./tabset.component.css']
})
export class TabsetComponent implements OnInit {

  temp: ProductsComponent;

  constructor() { }

  ngOnInit() {
  }

  setCategoryId(categoryId: number) {
    this.temp.setCategoryId(categoryId);
  }

  onSelect(data: TabDirective): void {
    console.log(data.id + " " + data.heading);
  }
}
