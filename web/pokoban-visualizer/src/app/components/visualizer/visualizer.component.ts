import {Component, ViewChild} from '@angular/core';

@Component({
    selector: 'visualizer',
    templateUrl: './visualizer.component.html',
    styleUrls: ['./visualizer.component.css']
})
export class VisualizerComponent {

    @ViewChild('canvas') canvas;

    constructor() {

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
