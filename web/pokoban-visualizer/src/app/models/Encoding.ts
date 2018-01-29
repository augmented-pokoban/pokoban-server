import {PokobanState} from "./PokobanState";

export class Encoding {

  success: boolean;
  errors: number;
  missing_errors: number;
  overfit_errors: number;
  y_reward_exp: number;
  y_reward_eval: number;
  action: string;
  x_state: PokobanState;
  y_state_exp: PokobanState;
  y_state_eval: PokobanState;
  diff: PokobanState;
  missing: PokobanState;
  overfit: PokobanState;

  constructor() {
  }
}
