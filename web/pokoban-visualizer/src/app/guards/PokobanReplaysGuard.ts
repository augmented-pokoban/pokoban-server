import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {PokobanService} from "../services/PokobanService";
import {Injectable} from "@angular/core";
import {PaginationResponse} from "../models/PaginationResponse";
import {PokobanMeta} from "../models/PokobanMeta";

@Injectable()
export class PokobanReplaysGuard implements Resolve<PaginationResponse<PokobanMeta>> {

    constructor(private pokobanService: PokobanService) {

    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<PaginationResponse<PokobanMeta>> {
        return this.pokobanService.replays(1, 20);
    }
}
