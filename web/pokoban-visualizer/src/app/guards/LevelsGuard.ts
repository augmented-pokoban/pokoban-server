import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {LevelService} from "../services/LevelService";
import {PaginationResponse} from "../models/PaginationResponse";

@Injectable()
export class LevelsGuard implements Resolve<PaginationResponse<string>> {

    constructor(private levelService: LevelService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<PaginationResponse<string>> {
        return this.levelService.getPage(1, 20);
    }
}
