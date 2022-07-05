import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Proizvodjac } from 'src/app/models/proizvodjac';
import { ProizvodjacService } from 'src/app/services/proizvodjac.service';

@Component({
  selector: 'app-proizvodjac-dialog',
  templateUrl: './proizvodjac-dialog.component.html',
  styleUrls: ['./proizvodjac-dialog.component.css']
})
export class ProizvodjacDialogComponent implements OnInit {
  
  public flag!: number;

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<ProizvodjacDialogComponent>,
    @Inject (MAT_DIALOG_DATA) public data: Proizvodjac,
    public proizvodjacService: ProizvodjacService) { }

  ngOnInit(): void {
  }

  public add(): void {
    this.proizvodjacService.insertProizvodjac(this.data).subscribe(() => {
      this.snackBar.open('Uspešno dodat proizvođač ' + this.data.id, 'OK', {duration:2500});
    }, (error:Error) => {
      this.snackBar.open('Došlo je do greške prilikom dodavanja novog proizvođača',
      'Zatvori', {duration: 2500});
    });
  }

  public update(): void {
    this.proizvodjacService.updateProizvodjac(this.data).subscribe(() => {
      this.snackBar.open('Uspešno izmenjen proizvođač ' + this.data.id, 'OK', {duration:2500});
    }, (error:Error) => {
      this.snackBar.open('Došlo je do greške prilikom izmene proizvođača',
      'Zatvori', {duration: 2500});
    });
  }

  public delete(): void {
    this.proizvodjacService.deleteProizvodjac(this.data.id).subscribe(() => {
      this.snackBar.open('Uspešno obrisan proizvođač ' + this.data.id, 'OK', {duration:2500});
    }, (error:Error) => {
      this.snackBar.open('Došlo je do greške prilikom brisanja proizvođača',
      'Zatvori', {duration: 2500});
    });
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste.', 'Zatvori', {duration:1000});
  }
}