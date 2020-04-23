import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { ToolbarComponent } from './toolbar.component';

describe('ToolbarComponent', () => {
  let component: ToolbarComponent;
  let fixture: ComponentFixture<ToolbarComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ToolbarComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ToolbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('shoud set the selected class on the selected element', () => {
    component.selectedOption = component.Options[0];
    fixture.detectChanges();

    const selectedButtonDE = fixture.debugElement.query(By.css('.nav-button-selected'))
    const selectedButtonHTML = selectedButtonDE.nativeElement;
    expect(selectedButtonHTML.textContent).toEqual(component.Options[0]);
  })
});
