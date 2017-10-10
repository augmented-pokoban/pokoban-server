import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PokobanLevel} from "../../models/PokobanLevel";

@Component({
    selector: 'levels',
    templateUrl: './levels.component.html',
    styleUrls: []
})
export class LevelsComponent implements OnInit {

    levels: PokobanLevel[];

    constructor(private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.levels = this.route.snapshot.data['levels'];
        // sort by filename
        this.levels.sort((a, b) => a.filename.toUpperCase() > b.filename.toUpperCase() ? 1 : -1);
    }
}
