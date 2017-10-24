import {DataService} from "./DataService";
import {Http} from "@angular/http";
import {Injectable} from "@angular/core";
import {Pokoban} from "../models/Pokoban";

@Injectable()
export class PokobanService extends DataService {

    constructor(http: Http) {
        super(http);
    }

    experts(): Promise<Pokoban[]> {
        return super.get<Pokoban[]>('?folder=replays');
    }

    replays(): Promise<Pokoban[]> {
      return super.get<Pokoban[]>('?folder=replays');
    }

    one(id: string): Promise<Pokoban> {
        return super.get<Pokoban>(`${id}`);
    }
}
