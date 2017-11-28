import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PokobanMeta} from "../../models/PokobanMeta";

@Component({
    selector: 'games',
    templateUrl: './games.component.html',
    styleUrls: []
})
export class GamesComponent implements OnInit {

    pokobans: PokobanMeta[];
    total: number;

    constructor(private route: ActivatedRoute) {
    }

    ngOnInit() {
        let response = this.route.snapshot.data['pokobans'];
        this.pokobans = response.data;
        this.total = response.total;
    }
}
