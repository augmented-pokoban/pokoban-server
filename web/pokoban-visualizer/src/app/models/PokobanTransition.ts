import {PokobanState} from "./PokobanState";

export class PokobanTransition {

    done: boolean;
    succes: boolean;
    reward: number;
    action: string;
    state: PokobanState;

    constructor() {
    }
}
