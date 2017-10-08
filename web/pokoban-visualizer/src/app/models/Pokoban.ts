import {PokobanState} from "./PokobanState";
import {PokobanTransition} from "./PokobanTransition";

export class Pokoban {

    level: string;
    id: string;
    initial: PokobanState;
    transitions: PokobanTransition[];

    constructor() {
    }
}