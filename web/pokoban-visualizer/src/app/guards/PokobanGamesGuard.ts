import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {PokobanService} from "../services/PokobanService";
import {Injectable} from "@angular/core";
import {PokobanMeta} from "../models/PokobanMeta";
import {PaginationResponse} from "../models/PaginationResponse";

@Injectable()
export class PokobanGamesGuard implements Resolve<PaginationResponse<PokobanMeta>> {

    constructor(private pokobanService: PokobanService) {

    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<PaginationResponse<PokobanMeta>> {
        return this.pokobanService.experts(1, 20);
    }
}
