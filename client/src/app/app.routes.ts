import { Routes } from '@angular/router';
import { MainComponent } from './component/main/main.component';
import { ProductComponent } from './component/product/product.component';
import { StatisticComponent } from './component/statistic/statistic.component';

export const routes: Routes = [
    {path:'main', component:MainComponent},
    {path:'', redirectTo:'main', pathMatch:'full'},
    {path:'statistic', component:StatisticComponent},
    {path:'products', component:ProductComponent},
];
