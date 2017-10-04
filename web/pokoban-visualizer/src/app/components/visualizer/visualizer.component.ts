import {AfterContentInit, Component, OnInit, ViewChild} from '@angular/core';
import {Pokoban} from "../../models/Pokoban";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
    selector: 'visualizer',
    templateUrl: './visualizer.component.html',
    styleUrls: ['./visualizer.component.css']
})
export class VisualizerComponent implements OnInit, AfterContentInit {

    @ViewChild('canvas') canvas;

    pokoban: Pokoban;

    constructor(private route: ActivatedRoute,
                private router: Router) {

        console.log("hej");
    }

    ngOnInit() {
        console.log("hej");

        // this.pokoban = this.route.snapshot.data['pokoban'];

        console.log(this.pokoban);
    }

    ngAfterContentInit() {
        this.draw(this.canvas.nativeElement);
    }

    /**
     * Draws game environment
     *
     * @param {HTMLCanvasElement} canvas
     */
    draw(canvas: HTMLCanvasElement) {

        console.log(canvas);

        if (canvas.getContext) {

            let ctx = canvas.getContext('2d');

            ctx.fillStyle = 'rgb(200, 0, 0)';
            ctx.fillRect(10, 10, 50, 50);
        }
    }
}
