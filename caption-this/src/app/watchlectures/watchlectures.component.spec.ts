import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WatchlecturesComponent } from './watchlectures.component';

describe('WatchlecturesComponent', () => {
  let component: WatchlecturesComponent;
  let fixture: ComponentFixture<WatchlecturesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WatchlecturesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WatchlecturesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
