import { TestBed } from '@angular/core/testing';

import { ExportBlogsService } from './export-blogs.service';

describe('ExportBlogsService', () => {
  let service: ExportBlogsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExportBlogsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
