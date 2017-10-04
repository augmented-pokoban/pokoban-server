import {PokobanObject} from "./PokobanObject";

export class PokobanState {

    dimensions: number;
    goals: PokobanObject[];
    agents: PokobanObject[];
    walls: PokobanObject[];
    boxes: PokobanObject[];

    constructor() {
    }
}
