import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LiveCommentaryComponent } from './live-commentary.component';

describe('LiveCommentaryComponent', () => {
  let component: LiveCommentaryComponent;
  let fixture: ComponentFixture<LiveCommentaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LiveCommentaryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LiveCommentaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
