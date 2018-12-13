import {Component, OnDestroy, OnInit, TemplateRef} from '@angular/core';
import { TabDirective } from "ngx-bootstrap";

@Component({
  selector: 'tabs',
  templateUrl: './tabset.component.html',
  styleUrls: ['./tabset.component.css']
})
export class TabsetComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  // onSelect(data: TabDirective): void {
  //   this.openModal(categoryModal)
  // }

  // todo: here is opening modal window were admin create new category
  openModal(template: TemplateRef<any>): void {

  }

}
