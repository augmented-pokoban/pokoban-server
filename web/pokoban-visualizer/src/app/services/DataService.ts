import {Http} from "@angular/http";
import {Injectable} from "@angular/core";
import {PaginationResponse} from "../models/PaginationResponse";
import {URLSearchParams} from '@angular/http';

@Injectable()
export abstract class DataService {

    endPoint: string;

    constructor(private http: Http) {
        this.endPoint = '/api'
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

  /**
   * T is the expected object to be wrapped in a PaginationResponse.
   * @param {string} url: Endpoint
   * @param {number} page: the page to view, starting at 1
   * @param {number} pageSize: The number of items per page
   * @returns {Promise<PaginationResponse<T>>}
   */
    protected paginate<T>(url: string, page: number, pageSize: number): Promise<PaginationResponse<T>> {
      let params = new URLSearchParams();
      params.set('skip', ((page-1) * pageSize).toString());
      params.set('limit', pageSize.toString());

      return new Promise((resolve, reject) => {
        this.http.get(`${this.endPoint}/${url}?${params.toString()}`).subscribe(response => {
          resolve(response.json() as PaginationResponse<T>);
        });
      });
    }
}
