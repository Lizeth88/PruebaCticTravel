import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Destino} from "../model/destino.model";

@Injectable({
  providedIn: 'root'
})
export class DestinosService {
  private api = 'http://localhost:8080/api'; // Ajusta la URL de tu API
  constructor(private http: HttpClient) { }

  getAllDestinos(): Observable<Destino[]> {
    return this.http.get<Destino[]>(`${this.api}/listar`);
  }

  getDestinoById(id: number): Observable<Destino> {
    return this.http.get<Destino>(`${this.api}/${id}`);
  }
}
