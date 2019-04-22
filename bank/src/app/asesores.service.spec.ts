import { TestBed, inject } from '@angular/core/testing';

import { AsesoresService } from './asesores.service';

describe('AsesoresService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AsesoresService]
    });
  });

  it('should be created', inject([AsesoresService], (service: AsesoresService) => {
    expect(service).toBeTruthy();
  }));
});
