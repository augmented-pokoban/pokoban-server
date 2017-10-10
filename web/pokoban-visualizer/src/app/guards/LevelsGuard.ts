import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {PokobanLevel} from "../models/PokobanLevel";
import {LevelService} from "../services/LevelService";

@Injectable()
export class LevelsGuard implements Resolve<PokobanLevel[]> {

    constructor(private levelService: LevelService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<PokobanLevel[]> {
        return this.levelService.all();
    }
}
