import { Component, OnInit, ElementRef, Input } from '@angular/core';
import { NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'app-image-upload-component',
  templateUrl: './image-upload-component.component.html',
  styleUrls: ['./image-upload-component.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: ImageUploadComponentComponent,
      multi: true
    }
  ]
})
export class ImageUploadComponentComponent implements OnInit {

  @Input() imgSrc : string = "/assets/add.png"

  private file : File = null
  onChange : Function;

  constructor(private host: ElementRef<HTMLInputElement>) { }

  ngOnInit(): void {
  }

  photoChange(files : FileList) {
    const file = files && files.item(0)
    this.onChange(file)
    this.file = file

    const reader = new FileReader();
    reader.onload = (e) => {
      this.imgSrc = String(e.target.result);
    }
    reader.readAsDataURL(file);
  }

  writeValue(value : null) {
    this.host.nativeElement.value = '';
    this.file = null;
  }

  registerOnChange(fn : Function) {
    this.onChange = fn;
  }

  registerOnTouched(fn : Function) {}

}
