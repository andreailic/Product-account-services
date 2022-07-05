import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Racun } from 'src/app/models/racun';
import { RacunService } from 'src/app/services/racun.service';

@Component({
  selector: 'app-racun-dialog',
  templateUrl: './racun-dialog.component.html',
  styleUrls: ['./racun-dialog.component.css']
})
export class RacunDialogComponent implements OnInit {

  public flag!: number;

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<RacunDialogComponent>,
    @Inject (MAT_DIALOG_DATA) public data: Racun,
    public racunService: RacunService) { }

  ngOnInit(): void {
  }

  public add(): void {
    this.racunService.insertRacun(this.data).subscribe(() => {
      this.snackBar.open('Uspešno dodat racun ' + this.data.id, 'OK', {duration:2500});
    }, (error:Error) => {
      this.snackBar.open('Došlo je do greške prilikom dodavanja novog racuna',
      'Zatvori', {duration: 2500});
    });
  }

  public update(): void {
    this.racunService.updateRacun(this.data).subscribe(() => {
      this.snackBar.open('Uspešno izmenjen racun ' + this.data.id, 'OK', {duration:2500});
    }, (error:Error) => {
      this.snackBar.open('Došlo je do greške prilikom izmene racuna',
      'Zatvori', {duration: 2500});
    });
  }

  public delete(): void {
    this.racunService.deleteRacun(this.data.id).subscribe(() => {
      this.snackBar.open('Uspešno obrisan racun ' + this.data.id, 'OK', {duration:2500});
    }, (error:Error) => {
      this.snackBar.open('Došlo je do greške prilikom brisanja racuna',
      'Zatvori', {duration: 2500});
    });
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste.', 'Zatvori', {duration:1000});
  }
}
