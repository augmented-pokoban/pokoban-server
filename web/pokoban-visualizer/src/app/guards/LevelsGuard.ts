import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Pokoban} from "../models/Pokoban";
import {PokobanService} from "../services/PokobanService";
import {Injectable} from "@angular/core";
import {PokobanLevel} from "../models/PokobanLevel";
import {logging} from "selenium-webdriver";
import {LevelService} from "../services/LevelService";

@Injectable()
export class LevelsGuard implements Resolve<PokobanLevel[]> {

    constructor(private levelService: LevelService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<PokobanLevel[]> {
        return this.levelService.all();
    }
}