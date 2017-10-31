import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {LevelService} from "../services/LevelService";

@Injectable()
export class LevelsGuard implements Resolve<string[]> {

    constructor(private levelService: LevelService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<string[]> {
        return this.levelService.all();
    }
}
