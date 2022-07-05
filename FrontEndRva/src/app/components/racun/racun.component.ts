import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Racun } from 'src/app/models/racun';
import { RacunService } from 'src/app/services/racun.service';
import { RacunDialogComponent } from '../dialogs/racun-dialog/racun-dialog.component';

@Component({
  selector: 'app-racun',
  templateUrl: './racun.component.html',
  styleUrls: ['./racun.component.css']
})

export class RacunComponent implements OnInit, OnDestroy {

  displayedColumns= ['id', 'datum', 'nacin_placanja', 'actions'];
  dataSource!: MatTableDataSource<Racun>;
  subscription!: Subscription;
  selektovanRacun!: Racun;

  @ViewChild(MatSort,{static:false}) sort!: MatSort;
  @ViewChild(MatPaginator,{static:false}) paginator!: MatPaginator;

  constructor(private racunService: RacunService,
    public dialog: MatDialog) { }
  

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }
  public loadData() {
    this.subscription = this.racunService.getAllRacun().subscribe(data => {
     // console.log(data);
     this.dataSource = new MatTableDataSource(data);
     this.dataSource.sort = this.sort;
     this.dataSource.paginator = this.paginator;
    }, (error: Error) => {
      console.log(error.name + ' ' + error.message);
    });

  }
  public openDialog(flag: number, id?:number, datum?:Date, nacinPlacanja?: string) {
    const dialogRef = this.dialog.open(RacunDialogComponent, {data:{id,datum,nacinPlacanja}});

    dialogRef.componentInstance.flag = flag;
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

  selectRow(row: Racun) {
    this.selektovanRacun = row;
  }
}