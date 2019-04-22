import { TestBed, inject } from '@angular/core/testing';

import { TarjetasService } from './tarjetas.service';

describe('TarjetasService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TarjetasService]
    });
  });

  it('should be created', inject([TarjetasService], (service: TarjetasService) => {
    expect(service).toBeTruthy();
  }));
});
