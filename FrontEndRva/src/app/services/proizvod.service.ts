import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PROIZVOD_URL } from '../app.constants';
import { Proizvod } from '../models/proizvod';

@Injectable({
  providedIn: 'root'
})
export class ProizvodService {

    constructor(private httpClient: HttpClient) { }
  
    public getAllProizvod(): Observable<any> {
      return this.httpClient.get(`${PROIZVOD_URL}`);
    }
    public insertProizvod(proizvod: Proizvod): Observable<any> {
      proizvod.id = 150;
      return this.httpClient.post(`${PROIZVOD_URL}`, proizvod);
    }
  
    public updateProizvod(proizvod: Proizvod): Observable<any> {
      return this.httpClient.put(`${PROIZVOD_URL}`, proizvod);
    }
  
    public deleteProizvod(id: number): Observable<any> {
      return this.httpClient.delete(`${PROIZVOD_URL}/${id}`);
    }
}