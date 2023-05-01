import { Component, Input, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-header-button',
  templateUrl: './header-button.component.html',
  styleUrls: ['./header-button.component.css']
})
export class HeaderButtonComponent {
  @Input() text: string = '';
  @Input() color: string = '';
  @Output() clickHeaderBtn: EventEmitter<any> = new EventEmitter();

  ngOnInit(): void {
    // throw new Error('Method not implemented.');
  }

  onClick(): void {
    this.clickHeaderBtn.emit();
  }
}
