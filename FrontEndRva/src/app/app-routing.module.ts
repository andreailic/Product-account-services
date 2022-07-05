import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RacunComponent } from './components/racun/racun.component';
import { AboutComponent } from './components/core/about/about.component';
import { AuthorComponent } from './components/core/author/author.component';
import { HomeComponent } from './components/core/home/home.component';
import { ProizvodjacComponent } from './components/proizvodjac/proizvodjac.component';
import { ProizvodComponent } from './components/proizvod/proizvod.component';


const routes: Routes = [
  { path: 'racun', component: RacunComponent },
  { path: 'proizvodjac', component: ProizvodjacComponent },
  { path: 'proizvod', component: ProizvodComponent },
  { path: 'home', component: HomeComponent },
  { path: 'author', component: AuthorComponent },
  { path: 'about', component: AboutComponent },
  { path: '', redirectTo: '/home', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }