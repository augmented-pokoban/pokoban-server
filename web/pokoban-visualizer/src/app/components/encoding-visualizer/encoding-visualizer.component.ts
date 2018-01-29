import {AfterContentInit, Component, HostListener, OnInit, ViewChild} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Pokoban} from "../../models/Pokoban";
import {PokobanState} from "../../models/PokobanState";
import {PokobanTransition} from "../../models/PokobanTransition";
import {PokobanObject} from "../../models/PokobanObject";
import {Encoding} from "../../models/Encoding";
import {EncodingStorage} from "../../services/EncodingStorage";

@Component({
  selector: 'encoding-visualizer',
  templateUrl: './encoding-visualizer.component.html',
  styleUrls: ['./encoding-visualizer.component.css']
})
export class EncodingVisualizerComponent implements OnInit, AfterContentInit {

  @ViewChild('canvas') canvas;

  ctx: CanvasRenderingContext2D;
  baseWidth: number;
  baseHeight: number;
  msPerAction: number = 250;
  play: boolean = false;
  nextIndex: number = 1;
  timer: any;
  encoding: Encoding;
  selected: PokobanState;


  constructor(private encodingStorage: EncodingStorage) {
  }

  ngOnInit() {
    this.encoding = this.encodingStorage.storage;
  }

  ngAfterContentInit() {
    // state to draw
    let state = this.encoding.x_state;
    let canvas = this.canvas.nativeElement;

    this.baseWidth = canvas.width / state.dimensions;
    this.baseHeight = canvas.height / state.dimensions;
    this.ctx = canvas.getContext('2d');

    // draw initial state
    this.drawState(canvas, state);
    this.selected = state;

    // Insert initial state into transition list as first element
    // let init = new PokobanTransition();
    // init.state = state;
    // init.action = 'INIT';
    // init.success = true;
    // this.encoding.transitions.unshift(init);
    //
    // if(this.play){
    //   // recursively perform transition
    //   this.transition(state, this.nextIndex);
    // }
  }

  // playOnOff(){
  //   // this.play has been updated here
  //   // restart playing, or clear the timer
  //   if(this.play){
  //     this.transition(this.encoding.transitions[this.nextIndex - 1].state, this.nextIndex);
  //   } else if(this.timer) {
  //     clearTimeout(this.timer);
  //   }
  // }

  selectState(state){
    // Get current state which is the previous index
    this.drawState(this.canvas.nativeElement, state);
    this.selected = state;
  }

  /**
   * Draws game environment
   *
   * @param {HTMLCanvasElement} canvas
   * @param state
   */
  drawState(canvas: HTMLCanvasElement, state: PokobanState) {

    // clear the canvas
    this.ctx.clearRect(0, 0, canvas.width, canvas.height);

    this.ctx.font = '18px monospace';
    this.ctx.textAlign = "center";
    this.ctx.textBaseline = "middle";
    this.ctx.fillStyle = 'rgba(0, 0, 0, 0.5)';

    // draw getPage cells
    for (let x = 0; x < canvas.width; x += this.baseWidth) {
      for (let y = 0; y < canvas.height; y += this.baseHeight) {
        this.ctx.strokeRect(x, y, this.baseWidth, this.baseHeight);
      }
    }

    // draw the walls
    state.walls.forEach(wall => {
      this.ctx.fillStyle = 'rgba(0, 0, 0, 1)';
      this.ctx.fillRect(this.baseWidth * wall.col, this.baseHeight * wall.row, this.baseWidth, this.baseHeight);
    });

    // draw the goals
    state.goals.forEach(goal => this.drawGoal(goal));

    // draw the boxes
    state.boxes.forEach(box => this.drawBox(box, this.isSolved(box, state)));

    // draw the agents
    state.agents.forEach(agent => this.drawAgent(agent));
  }

  /**
   * Animates a single transition
   *
   * @param {PokobanState} currentState
   * @param currentIndex
   * @returns {PokobanState}
   */
  // transition(currentState: PokobanState, currentIndex: number): PokobanState {
  //   if(this.encoding.transitions.length <= currentIndex) return currentState;
  //
  //   this.timer = setTimeout(() => {
  //     return this.transition(
  //       this.drawTransition(currentState, this.encoding.transitions[currentIndex].state),
  //       this.nextIndex = currentIndex + 1)
  //   }, this.msPerAction);
  // }

  /**
   * Transitions canvas into new state
   *
   * @param currentState
   * @param {PokobanState} nextState
   */
  private drawTransition(currentState: PokobanState,
                         nextState: PokobanState): PokobanState {

    // remove agents & boxes from old position
    currentState.agents.concat(currentState.boxes).forEach(agent => {
      this.ctx.clearRect(this.baseWidth * agent.col, this.baseHeight * agent.row, this.baseWidth, this.baseHeight);
      this.ctx.strokeRect(this.baseWidth * agent.col, this.baseHeight * agent.row, this.baseWidth, this.baseHeight);
    });

    // insert gaols (might have been removed)
    nextState.goals.forEach(goal => {
      this.drawGoal(goal);
    });

    // insert agents at new position
    nextState.agents.forEach(agent => {
      this.drawAgent(agent);
    });

    // insert boxes at new position
    nextState.boxes.forEach(box => {
      this.drawBox(box, this.isSolved(box, nextState));
    });

    return nextState;
  }

  /**
   * Draws a goal
   *
   * @param {PokobanObject} goal
   */
  private drawGoal(goal: PokobanObject) {
    this.ctx.fillStyle = 'rgba(0, 255, 255, 1)';
    this.ctx.fillRect(this.baseWidth * goal.col, this.baseHeight * goal.row, this.baseWidth, this.baseHeight);

    this.ctx.fillStyle = 'rgba(255, 255, 255, 1)';
    this.ctx.fillText(
      goal.letter,
      this.baseWidth * goal.col + this.baseWidth / 2,
      this.baseHeight * goal.row + this.baseHeight / 2
    );
  }

  /**
   * Draws a box
   *
   * @param {PokobanObject} box
   * @param isSolved
   */
  private drawBox(box: PokobanObject, isSolved: boolean) {
    this.ctx.fillStyle = isSolved ? 'rgba(0, 255, 0, 0.8)' : 'rgba(0, 0, 255, 1)';
    this.ctx.fillRect(this.baseWidth * box.col, this.baseHeight * box.row, this.baseWidth, this.baseHeight);

    this.ctx.fillStyle = 'rgba(255, 255, 255, 1)';
    this.ctx.fillText(
      box.letter,
      this.baseWidth * box.col + this.baseWidth / 2,
      this.baseHeight * box.row + this.baseHeight / 2
    );
  }

  /**
   * Draws an agent
   *
   * @param agent
   */
  private drawAgent(agent: PokobanObject) {
    // circle
    this.ctx.fillStyle = 'rgba(0, 0, 255, 1)';
    this.ctx.beginPath();
    this.ctx.arc(
      this.baseWidth * agent.col + this.baseWidth / 2,
      this.baseWidth * agent.row + this.baseHeight / 2,
      this.baseHeight / 3,
      0,
      Math.PI * 2,
      false
    );
    this.ctx.fill();

    // text
    this.ctx.fillStyle = 'rgba(255, 255, 255, 1)';
    this.ctx.fillText(
      agent.letter,
      this.baseWidth * agent.col + this.baseWidth / 2,
      this.baseHeight * agent.row + this.baseHeight / 2
    );
  }

  /**
   * Returns true if given box solves a goal
   *
   * @param {PokobanObject} box
   * @param state
   */
  private isSolved(box: PokobanObject, state: PokobanState): boolean {
    return state.goals.some(goal => {
      return goal.row == box.row &&
        goal.col == box.col &&
        goal.letter === box.letter.toLowerCase();
    });
  }
}
