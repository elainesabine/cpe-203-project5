import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadvidsComponent } from './uploadvids.component';

describe('UploadvidsComponent', () => {
  let component: UploadvidsComponent;
  let fixture: ComponentFixture<UploadvidsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UploadvidsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadvidsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
