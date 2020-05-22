import {TestBed} from '@angular/core/testing';

import {MakeTableService} from './make-table.service';

describe('MakeTableService', () => {
  let service: MakeTableService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MakeTableService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
