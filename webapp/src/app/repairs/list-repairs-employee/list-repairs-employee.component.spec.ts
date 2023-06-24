import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListRepairsEmployeeComponent } from './list-repairs-employee.component';

describe('ListRepairsEmployeeComponent', () => {
  let component: ListRepairsEmployeeComponent;
  let fixture: ComponentFixture<ListRepairsEmployeeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListRepairsEmployeeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListRepairsEmployeeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
