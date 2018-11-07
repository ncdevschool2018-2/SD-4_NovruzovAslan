import { TestBed, inject } from "@angular/core/testing";

import { SpecialProductService } from "./special-product.service";

describe("SpecialProductService", () => {
  beforeEach(() =>
    TestBed.configureTestingModule({
      providers: [SpecialProductService]
    }));

  it("should be created", inject([SpecialProductService], (service: SpecialProductService) => {
    expect(service).toBeTruthy();
  }));
});
