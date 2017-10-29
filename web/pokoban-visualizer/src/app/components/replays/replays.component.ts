import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Pokoban} from "../../models/Pokoban";

@Component({
  selector: 'replays',
  templateUrl: './replays.component.html',
  styleUrls: []
})
export class ReplaysComponent implements OnInit {

  pokobans: Pokoban[];

  constructor(private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.pokobans = this.route.snapshot.data['pokobans'];
    // sort by date
    this.pokobans.sort((a, b) => a.date < b.date ? 1 : -1);
  }
}
