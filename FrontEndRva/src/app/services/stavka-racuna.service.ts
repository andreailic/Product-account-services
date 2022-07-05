import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { STAVKA_RACUNA_URL, STAVKE_RACUNA_ZA_RACUNID_URL } from '../app.constants';
import { StavkaRacuna } from '../models/stavka-racuna';

@Injectable({
  providedIn: 'root'
})
export class StavkaRacunaService {

  constructor(private httpClient:HttpClient) { }

  public getAllStavkeZaRacunID(idRacun: number):Observable<any> {
    return this.httpClient.get(`${STAVKE_RACUNA_ZA_RACUNID_URL}/${idRacun}`)
  }
  public addStavkaRacuna(stavka: StavkaRacuna): Observable<any> {
    stavka.id = 150;
    return this.httpClient.post(`${STAVKA_RACUNA_URL}`, stavka);
  }

  public updateStavkaRacuna(stavka: StavkaRacuna): Observable<any> {
    return this.httpClient.put(`${STAVKA_RACUNA_URL}`, stavka);
  }

  public deleteStavkaRacuna(id: number): Observable<any> {
    return this.httpClient.delete(`${STAVKA_RACUNA_URL}/${id}`);
  }
}
