import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Pokoban} from "../../models/Pokoban";

@Component({
    selector: 'games',
    templateUrl: './games.component.html',
    styleUrls: []
})
export class GamesComponent implements OnInit {

    pokobans: Pokoban[];

    constructor(private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.pokobans = this.route.snapshot.data['pokobans'];
    }
}
