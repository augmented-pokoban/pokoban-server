import {DataService} from "./DataService";
import {Http} from "@angular/http";
import {Injectable} from "@angular/core";
import {PaginationResponse} from "../models/PaginationResponse";
import {Encoding} from "../models/Encoding";

@Injectable()
export class EncodingService extends DataService {

  constructor(http: Http) {
    super(http);
  }

  getPage(page: number, pageSize: number): Promise<PaginationResponse<Encoding>> {
    return super.paginate<Encoding>('encoding', page, pageSize);
  }
}
