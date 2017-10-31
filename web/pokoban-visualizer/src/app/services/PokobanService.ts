import {DataService} from "./DataService";
import {Http} from "@angular/http";
import {Injectable} from "@angular/core";
import {Pokoban} from "../models/Pokoban";

@Injectable()
export class PokobanService extends DataService {

    private baseUrl: string = 'pokoban';

    constructor(http: Http) {
        super(http);
    }

    experts(): Promise<Pokoban[]> {
        return super.get<Pokoban[]>(`${this.baseUrl}/saves`);
    }

    replays(): Promise<Pokoban[]> {
      return super.get<Pokoban[]>(`${this.baseUrl}/replays`);
    }

    one(id: string, folder: string): Promise<Pokoban> {
        return super.get<Pokoban>(`${this.baseUrl}/${folder}/${id}`);
    }
}
