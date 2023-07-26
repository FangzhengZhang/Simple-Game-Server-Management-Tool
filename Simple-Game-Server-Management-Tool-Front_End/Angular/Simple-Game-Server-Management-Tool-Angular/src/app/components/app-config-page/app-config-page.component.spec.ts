import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppConfigPageComponent } from './app-config-page.component';

describe('AppConfigPageComponent', () => {
  let component: AppConfigPageComponent;
  let fixture: ComponentFixture<AppConfigPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppConfigPageComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppConfigPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
