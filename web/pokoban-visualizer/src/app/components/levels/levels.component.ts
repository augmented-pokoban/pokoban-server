import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {LevelService} from "../../services/LevelService";

@Component({
    selector: 'levels',
    templateUrl: './levels.component.html'
})
export class LevelsComponent implements OnInit {

    levels: string[];
    pageSize: number = 20;
    curPage: number = 1;
    total: number = 0;
    folders: string[] = ['Supervised', 'Unsuperised'];
    folder: string = this.folders[1];

    constructor(private route: ActivatedRoute,
                private levelService: LevelService) {
    }

    ngOnInit() {
        let resp =  this.route.snapshot.data['paginationResp'];
        console.log(resp);
        this.levels = resp.data;
        this.total = resp.total;
    }

    pageChange($event){
      this.levelService.getPage($event, this.pageSize, this.folder.toLowerCase())
        .then(resp => {
          console.log(resp);
          this.levels = resp.data;
          this.total = resp.total;
          this.curPage = $event;
        })

    }
}
