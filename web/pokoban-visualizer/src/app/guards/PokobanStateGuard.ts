import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Pokoban} from "../models/Pokoban";
import {LevelService} from "../services/LevelService";
import {Injectable} from "@angular/core";
import {Http} from "@angular/http";

@Injectable()
export class PokobanStateGuard implements Resolve<Pokoban> {

  constructor(private http: Http,
              private levelService: LevelService) {

  }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<Pokoban> {
    return this.levelService.state(route.params['folder'].toLowerCase(), route.params['file']);
  }
}
