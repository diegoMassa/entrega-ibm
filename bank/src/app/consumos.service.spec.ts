import { TestBed, inject } from '@angular/core/testing';

import { ConsumosService } from './consumos.service';

describe('ConsumosService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ConsumosService]
    });
  });

  it('should be created', inject([ConsumosService], (service: ConsumosService) => {
    expect(service).toBeTruthy();
  }));
});
