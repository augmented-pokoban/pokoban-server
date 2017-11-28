import {DataService} from "./DataService";
import {Http} from "@angular/http";
import {Injectable} from "@angular/core";
import {PokobanLevel} from "../models/PokobanLevel";
import {Pokoban} from "../models/Pokoban";
import {PaginationResponse} from "../models/PaginationResponse";

@Injectable()
export class LevelService extends DataService {

    constructor(http: Http) {
        super(http);
    }

    getPage(page: number, pageSize: number, folder: string): Promise<PaginationResponse<string>> {
      return super.paginate<string>(`levels/${folder}?`, page, pageSize);
    }

    one(filename: string): Promise<PokobanLevel> {
        return super.get<PokobanLevel>(`levels/${filename}`);
    }

    state(folder: string, filename: string): Promise<Pokoban> {
      return super.get<Pokoban>(`levels/${folder}/${filename}/state`);
    }
}
