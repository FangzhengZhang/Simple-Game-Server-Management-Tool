import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BackupJobComponent } from './backup-job.component';

describe('BackupJobComponent', () => {
  let component: BackupJobComponent;
  let fixture: ComponentFixture<BackupJobComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BackupJobComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BackupJobComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
