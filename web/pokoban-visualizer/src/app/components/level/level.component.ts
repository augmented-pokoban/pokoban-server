import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Pokoban} from "../../models/Pokoban";

@Component({
    selector: 'levels',
    templateUrl: './level.component.html'
})
export class LevelComponent implements OnInit {

    level: Pokoban;

    constructor(private route: ActivatedRoute,) {
    }

    ngOnInit() {
        this.level = this.route.snapshot.data['level'];
    }
}
