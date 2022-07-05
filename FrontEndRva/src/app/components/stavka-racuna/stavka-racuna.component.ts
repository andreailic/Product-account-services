import { identifierName } from '@angular/compiler';
import { Component, OnChanges, OnDestroy, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { Input } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Proizvod } from 'src/app/models/proizvod';
import { Racun } from 'src/app/models/racun';
import { StavkaRacuna } from 'src/app/models/stavka-racuna';
import { StavkaRacunaService } from 'src/app/services/stavka-racuna.service';
import { StavkaRacunaDialogComponent } from '../dialogs/stavka-racuna-dialog/stavka-racuna-dialog.component';

@Component({
  selector: 'app-stavka-racuna',
  templateUrl: './stavka-racuna.component.html',
  styleUrls: ['./stavka-racuna.component.css']
})
export class StavkaRacunaComponent implements OnInit,OnDestroy,OnChanges {
  
  displayedColumns= ['id', 'redniBroj', 'kolicina', 'jedinicaMere','cena', 'racun','proizvod','actions'];
  dataSource!:MatTableDataSource<StavkaRacuna>;
  subscription!:Subscription;
  @Input () selektovanRacun!: Racun;

  @ViewChild (MatSort,{static:false}) sort!: MatSort;
  @ViewChild(MatPaginator,{static:false}) paginator!: MatPaginator;

  constructor(private stavkaRacunaService: StavkaRacunaService,
    public dialog: MatDialog) { }
   
    ngOnChanges(): void {
    if(this.selektovanRacun)
    this.loadData();  
    }
     
    ngOnDestroy(): void {
      this.subscription.unsubscribe();
    }
  
    ngOnInit(): void {
      this.loadData();
    }
  
  public loadData() {
    this.subscription = this.stavkaRacunaService.getAllStavkeZaRacunID(this.selektovanRacun.id)
    .subscribe(data => {
     // console.log(data);
     this.dataSource = new MatTableDataSource(data);
     this.dataSource.sort = this.sort;
     this.dataSource.paginator = this.paginator;
    }, (error: Error) => {
      console.log(error.name + ' ' + error.message);
    });

  }
  public openDialog(flag: number, id?:number, redniBroj?:number, kolicina?:number,jedinicaMere?:string,cena?:number,racun?:Racun,proizvod?:Proizvod) {
    const dialogRef = this.dialog.open(StavkaRacunaDialogComponent, {data:{id,redniBroj,kolicina,jedinicaMere,cena,racun,proizvod}});

    dialogRef.componentInstance.flag = flag;

    if(flag===1) {
      dialogRef.componentInstance.data.racun = this.selektovanRacun;
    }
    dialogRef.afterClosed().subscribe(result => {
      if(result == 1)
        this.loadData();
    });
  }
  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value;
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue;
  }
  
}