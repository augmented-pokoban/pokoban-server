import {AfterContentInit, Component, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Pokoban} from "../../models/Pokoban";
import {PokobanState} from "../../models/PokobanState";

@Component({
    selector: 'visualizer',
    templateUrl: './visualizer.component.html',
    styleUrls: ['./visualizer.component.css']
})
export class VisualizerComponent implements OnInit, AfterContentInit {

    @ViewChild('canvas') canvas;

    pokoban: Pokoban;

    constructor(private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.pokoban = this.route.snapshot.data['pokoban'];

        console.log(this.pokoban);
    }

    ngAfterContentInit() {
        // state to draw
        let state = this.pokoban.initial;
        let canvas = this.canvas.nativeElement;
        this.drawState(canvas, state);
        this.moveAgent(canvas, this.pokoban.transitions[0].state);
    }

    /**
     * Draws game environment
     *
     * @param {HTMLCanvasElement} canvas
     * @param state
     */
    drawState(canvas: HTMLCanvasElement, state: PokobanState) {

        let ctx = canvas.getContext('2d');
        // clear the canvas
        ctx.clearRect(0, 0, canvas.width, canvas.height);

        let baseWidth = canvas.width / state.dimensions;
        let baseHeight = canvas.height / state.dimensions;

        ctx.font = '24px monospace';
        ctx.textAlign = "center";
        ctx.textBaseline = "middle";
        ctx.fillStyle = 'rgba(0, 0, 0, 0.5)';

        // draw all cells
        for (let x = 0; x < canvas.width; x += baseWidth) {
            for (let y = 0; y < canvas.height; y += baseHeight) {
                ctx.strokeRect(x, y, baseWidth, baseHeight);
            }
        }

        // draw the walls
        state.walls.forEach(wall => {
            ctx.fillStyle = 'rgba(0, 0, 0, 1)';
            ctx.fillRect(baseWidth * wall.col, baseHeight * wall.row, baseWidth, baseHeight);
        });

        // draw the goals
        state.goals.forEach(goal => {
            ctx.fillStyle = 'rgba(0, 0, 255, 0.7)';
            ctx.fillRect(baseWidth * goal.col, baseHeight * goal.row, baseWidth, baseHeight);

            ctx.fillStyle = 'rgba(255, 255, 255, 1)';
            ctx.fillText(
                goal.letter,
                baseWidth * goal.col + baseWidth / 2,
                baseHeight * goal.row + baseHeight / 2
            );
        });

        // draw the boxes
        state.boxes.forEach(box => {
            ctx.fillStyle = 'rgba(0, 0, 255, 0.7)';
            ctx.fillRect(baseWidth * box.col, baseHeight * box.row, baseWidth, baseHeight);

            ctx.fillStyle = 'rgba(255, 255, 255, 1)';
            ctx.fillText(
                box.letter,
                baseWidth * box.col + baseWidth / 2,
                baseHeight * box.row + baseHeight / 2
            );
        });

        // draw the agents
        state.agents.forEach(agent => {
            ctx.fillStyle = 'rgba(0, 0, 255, 1)';
            ctx.beginPath();
            ctx.arc(
                baseWidth * agent.col + baseWidth / 2,
                baseWidth * agent.row + baseHeight / 2,
                baseHeight / 3,
                0,
                Math.PI * 2,
                false
            );
            ctx.fill();
            ctx.stroke();
        });
    }

    /**
     * Moves the agent in the existing canvas
     *
     * @param {HTMLCanvasElement} canvas
     * @param {PokobanState} state
     */
    moveAgent(canvas: HTMLCanvasElement, state: PokobanState) {

        let ctx = canvas.getContext('2d');
    }
}
