import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {PokobanMeta} from "../../models/PokobanMeta";
import {PokobanService} from "../../services/PokobanService";

@Component({
    selector: 'games',
    templateUrl: './games.component.html',
    styleUrls: []
})
export class GamesComponent implements OnInit {

    pokobans: PokobanMeta[];
    pageSize: number = 20;
    curPage: number = 1;
    total: number;

    constructor(private route: ActivatedRoute,
                private pokobanService: PokobanService) {
    }

    ngOnInit() {
        let response = this.route.snapshot.data['pokobans'];
        this.pokobans = response.data;
        this.total = response.total;
    }

    pageChange($event){
      this.pokobanService.experts($event, this.pageSize)
        .then(resp => {
          this.pokobans = resp.data;
          this.total = resp.total;
          this.curPage = $event;
        })
    }
}
