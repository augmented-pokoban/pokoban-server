import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {LevelService} from "../services/LevelService";
import {Pokoban} from "../models/Pokoban";

@Injectable()
export class LevelGuard implements Resolve<Pokoban> {

    constructor(private http: Http,
                private levelService: LevelService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<Pokoban> {
        return new Promise((resolve, reject) => {
            this.http.get(`./assets/levels/${route.params['level']}`).subscribe(response => {
                this.levelService.levelState(response.text())
                .then(res => resolve(res))
                .catch(err => reject(err));
            });
        });
    }
}
