import {PokobanState} from "./PokobanState";
import {PokobanTransition} from "./PokobanTransition";

export class Pokoban {

    id: string;
    date: Date;
    level: string;
    initial: PokobanState;
    transitions: PokobanTransition[];
    description: string;

    constructor() {
    }
}
