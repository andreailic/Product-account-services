import { Racun } from "./racun"
import { Proizvod } from "./proizvod";

export class StavkaRacuna {
    id!: number;
    redniBroj!: number;
    kolicina!: number;
    jedinicaMere!: string;
    cena!: number;
    racun!: Racun;
    proizvod!:Proizvod;
}