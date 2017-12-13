import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {PokobanService} from "../services/PokobanService";
import {Injectable} from "@angular/core";
import {PaginationResponse} from "../models/PaginationResponse";
import {PokobanMeta} from "../models/PokobanMeta";

@Injectable()
export class PokobanRunningGuard implements Resolve<string[]> {

  constructor(private pokobanService: PokobanService) {

  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<string[]> {
    return this.pokobanService.running();
  }
}
