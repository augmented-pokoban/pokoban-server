import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Pokoban} from "../models/Pokoban";
import {PokobanService} from "../services/PokobanService";
import {Injectable} from "@angular/core";
import {Http} from "@angular/http";

@Injectable()
export class PokobanGuard implements Resolve<Pokoban> {

    constructor(private http: Http,
                private pokobanService: PokobanService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<Pokoban> {
        return this.pokobanService
          .oneMeta(route.params['id'], route.queryParams['folder'])
          .then(meta => this.pokobanService.onePokoban(meta.fileRef))
    }
}
