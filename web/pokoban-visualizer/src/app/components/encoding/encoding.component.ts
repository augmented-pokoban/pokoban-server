import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Encoding} from "../../models/Encoding";
import {EncodingStorage} from "../../services/EncodingStorage";

@Component({
    selector: 'encoding',
    templateUrl: './encoding.component.html',
    styleUrls: []
})
export class EncodingComponent implements OnInit {

    total: number;
    encodings: Encoding[];

    constructor(private route: ActivatedRoute,
                private router: Router,
                private encodingStorage: EncodingStorage) {
    }

    ngOnInit() {
        let response = this.route.snapshot.data['encodings'];
        this.encodings = response.data;
        this.total = response.total;
    }

    onClick(encoding){
        this.encodingStorage.storage = encoding;
        this.router.navigate(['encoding/visualizer'])
    }
}
