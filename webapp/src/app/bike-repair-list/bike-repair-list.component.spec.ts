import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BikeRepairListComponent } from './bike-repair-list.component';

describe('BikeRepairListComponent', () => {
  let component: BikeRepairListComponent;
  let fixture: ComponentFixture<BikeRepairListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BikeRepairListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BikeRepairListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
