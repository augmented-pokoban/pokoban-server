import {DataService} from "./DataService";
import {Http} from "@angular/http";
import {Injectable} from "@angular/core";
import {PokobanLevel} from "../models/PokobanLevel";
import {Pokoban} from "../models/Pokoban";

@Injectable()
export class LevelService extends DataService {

    constructor(http: Http) {
        super(http);
    }

    all(): Promise<PokobanLevel[]> {
        return super.get<PokobanLevel[]>('levels');
    }

    one(filename: string): Promise<PokobanLevel> {
        return super.get<PokobanLevel>(`levels/${filename}`);
    }

    state(filename: string): Promise<Pokoban> {
      return super.get<Pokoban>(`levels/${filename}/state`);
    }
}
