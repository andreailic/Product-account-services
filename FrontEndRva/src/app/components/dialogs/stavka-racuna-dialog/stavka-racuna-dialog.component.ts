import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Proizvod } from 'src/app/models/proizvod';
import { StavkaRacuna } from 'src/app/models/stavka-racuna';
import { ProizvodService } from 'src/app/services/proizvod.service';
import { StavkaRacunaService } from 'src/app/services/stavka-racuna.service';

@Component({
  selector: 'app-stavka-racuna-dialog',
  templateUrl: './stavka-racuna-dialog.component.html',
  styleUrls: ['./stavka-racuna-dialog.component.css']
})
export class StavkaRacunaDialogComponent implements OnInit {

  flag!:number;
  proizvodi!:Proizvod[]

  constructor(public snackBar: MatSnackBar,
    public dialogRef:MatDialogRef<StavkaRacunaDialogComponent>,
    @Inject (MAT_DIALOG_DATA) public data: StavkaRacuna,
    private stavkaRacunaService: StavkaRacunaService,
    private proizvodService: ProizvodService ) { }

  ngOnInit(): void {
    this.proizvodService.getAllProizvod().subscribe(data => {
      this.proizvodi = data;
    });
  }
  compareTo(a:any, b:any) {
    return a.id == b.id;
  }

  public add(): void {
    this.stavkaRacunaService.addStavkaRacuna(this.data).subscribe(() => {
      this.snackBar.open('Uspešno dodata stavka računa ' + this.data.id, 'OK', {duration:2500});
    }, (error:Error) => {
      this.snackBar.open('Došlo je do greške prilikom dodavanja nove stavke računa',
      'Zatvori', {duration: 2500});
    });
  }

  public update(): void {
    this.stavkaRacunaService.updateStavkaRacuna(this.data).subscribe(() => {
      this.snackBar.open('Uspešno izmenjena stavka računa ' + this.data.id, 'OK', {duration:2500});
    }, (error:Error) => {
      this.snackBar.open('Došlo je do greške prilikom izmene stavke računa',
      'Zatvori', {duration: 2500});
    });
  }

  public delete(): void {
    this.stavkaRacunaService.deleteStavkaRacuna(this.data.id).subscribe(() => {
      this.snackBar.open('Uspešno obrisana stavka računa ' + this.data.id, 'OK', {duration:2500});
    }, (error:Error) => {
      this.snackBar.open('Došlo je do greške prilikom brisanja stavke računa',
      'Zatvori', {duration: 2500});
    });
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste.', 'Zatvori', {duration:1000});
  }
 
}