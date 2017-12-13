import {Component} from '@angular/core'
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'running',
  templateUrl: './running.component.html',
  styleUrls: []
})

export class RunningComponent{

  running: string[];

  constructor(private route: ActivatedRoute){
  }

  ngOnInit(){
    this.running = this.route.snapshot.data['running'];
  }
}
