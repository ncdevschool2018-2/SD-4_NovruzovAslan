import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MySpecialSubscriptionsComponent } from './my-special-subscriptions.component';

describe('MySpecialSubscriptionsComponent', () => {
  let component: MySpecialSubscriptionsComponent;
  let fixture: ComponentFixture<MySpecialSubscriptionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MySpecialSubscriptionsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MySpecialSubscriptionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
