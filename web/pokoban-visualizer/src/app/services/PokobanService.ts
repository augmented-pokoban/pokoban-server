import {DataService} from "./DataService";
import {Http} from "@angular/http";
import {Injectable} from "@angular/core";
import {Pokoban} from "../models/Pokoban";
import {PaginationResponse} from "../models/PaginationResponse";
import {PokobanMeta} from "../models/PokobanMeta";
import * as JSZIP from 'jszip';
import {JSZipObject} from "jszip";
import {Observable} from "rxjs/Observable";
import 'rxjs/add/operator/toPromise';

@Injectable()
export class PokobanService extends DataService {

    private baseUrl: string = 'pokoban';

    constructor(http: Http) {
        super(http);
    }

    experts(page: number, pageSize: number): Promise<PaginationResponse<PokobanMeta>> {
        return super.paginate<PokobanMeta>(`${this.baseUrl}/saves`, page, pageSize);
    }

    replays(page: number, pageSize: number): Promise<PaginationResponse<PokobanMeta>> {
      return super.paginate<PokobanMeta>(`${this.baseUrl}/replays`, page, pageSize);
    }

    oneMeta(id: string, folder: string): Promise<PokobanMeta> {
        return super.get<PokobanMeta>(`${this.baseUrl}/${folder}/${id}`);
    }

    onePokoban(fileRef: string): Promise<Pokoban>{
      return new Observable<Pokoban>(observer => {
        let zipper = new JSZIP();
        window.fetch(fileRef)
          .then(response => {
            return Promise.resolve(response.arrayBuffer())
          })
          .then(data => zipper.loadAsync(data))
          .then(zip => {
            let result: JSZipObject[] = [];
            zip.forEach((name, file) => result.push(file));
            return result;
          })
          .then(data => data[0].async('text')
            .then(content => {
              observer.next(<Pokoban> JSON.parse(content));
              observer.complete();
            }))
      }).toPromise();
    }

    running(): Promise<string[]>{
      return super.get<string[]>(`${this.baseUrl}/running`);
    }

}
