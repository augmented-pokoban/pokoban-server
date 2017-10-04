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
        return new Promise<Pokoban>((resolve, reject) => {
            this.http.get(`assets/data/${route.params['id']}.json`).subscribe(response => {
                resolve(response.json() as Pokoban);
            });
        });

        // return this.pokobanService.one(route.params['id']);
    }
}