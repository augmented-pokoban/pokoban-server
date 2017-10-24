import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Pokoban} from "../models/Pokoban";
import {PokobanService} from "../services/PokobanService";
import {Injectable} from "@angular/core";

@Injectable()
export class PokobanReplaysGuard implements Resolve<Pokoban[]> {

    constructor(private pokobanService: PokobanService) {

    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<Pokoban[]> {
        return this.pokobanService.replays();
    }
}
