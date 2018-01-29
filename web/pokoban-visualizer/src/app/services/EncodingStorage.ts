import { Injectable } from '@angular/core';
import {Encoding} from "../models/Encoding";

@Injectable()
export class EncodingStorage {

  public storage: Encoding;

  public constructor() { }

}
