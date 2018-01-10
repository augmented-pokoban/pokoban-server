import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {PaginationResponse} from "../models/PaginationResponse";
import {Encoding} from "../models/Encoding";
import {EncodingService} from "../services/EncodingService";

@Injectable()
export class EncodingGuard implements Resolve<PaginationResponse<Encoding>> {

  constructor(private encodingService: EncodingService) {
  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<PaginationResponse<Encoding>> {
    return this.encodingService.getPage(1, 20);
  }
}
