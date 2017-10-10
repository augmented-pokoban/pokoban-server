import {PokobanState} from "./PokobanState";

export class PokobanTransition {

    done: boolean;
    success: boolean;
    reward: number;
    action: string;
    state: PokobanState;

    constructor() {
    }
}
