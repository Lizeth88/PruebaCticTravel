import { Injectable} from '@angular/core';
import {BehaviorSubject, Observable, tap} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {SweetAlertService} from "../util/sweet-alert.service";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private api = 'http://localhost:8080/api/auth'
  private conectado = new BehaviorSubject<boolean>(this.tokenDisponible());

  get estaConectado(){
    return this.conectado.asObservable();
  }
  constructor(private sweetAlertService: SweetAlertService, private http: HttpClient) { }

  private tokenDisponible(): boolean{
    return typeof localStorage !== 'undefined' && localStorage.getItem('token') === null;
  }



  login(email: string, password: string): Observable<any>{
    return this.http.post<any>(`${this.api}/login`, {email, password})
      .pipe(
        tap(response =>{
          if (response.token){
            localStorage.setItem('token', response.token);
            this.conectado.next(true);
          }
        })
      );
  }

  register(nombre:string, email: string, password:string): Observable<any>{
    return this.http.post<any>(`${this.api}/register`, {nombre, email, password})
      .pipe(
        tap(response =>{
          if (response.token){
            localStorage.setItem('token', response.token);
            this.conectado.next(true);
          }
        })
      );
  }

  logout(){
    localStorage.removeItem('token');
    this.conectado.next(false);
  }

}
