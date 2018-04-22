import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {RankDetailComponent} from './rank-detail.component';

describe('RankDetailComponent', () => {
  let component: RankDetailComponent;
  let fixture: ComponentFixture<RankDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [RankDetailComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RankDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
