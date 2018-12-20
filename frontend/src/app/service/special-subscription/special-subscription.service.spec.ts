import { TestBed } from '@angular/core/testing';

import { SpecialSubscriptionService } from './special-subscription.service';

describe('SpecialSubscriptionService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SpecialSubscriptionService = TestBed.get(SpecialSubscriptionService);
    expect(service).toBeTruthy();
  });
});
