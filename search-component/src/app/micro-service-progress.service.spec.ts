import { TestBed } from '@angular/core/testing';

import { MicroServiceProgressService } from './micro-service-progress.service';

describe('MicroServiceProgressService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: MicroServiceProgressService = TestBed.get(MicroServiceProgressService);
    expect(service).toBeTruthy();
  });
});
