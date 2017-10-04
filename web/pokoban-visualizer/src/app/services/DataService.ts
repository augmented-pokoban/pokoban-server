import {Http} from "@angular/http";
import {Injectable} from "@angular/core";

@Injectable()
export abstract class DataService {

    endPoint: string;

    constructor(private http: Http) {
        this.endPoint = 'http://localhost:8080/pokoban-server/api'
    }

    /**
     *
     * @param {string} url
     * @returns {Promise}
     */
    protected get<T>(url: string): Promise<T> {
        return new Promise((resolve, reject) => {
            this.http.get(`${this.endPoint}/${url}`).subscribe(response => {
                resolve(response.json() as T);
            });
        });
    }
}