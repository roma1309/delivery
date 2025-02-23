import { Component } from '@angular/core';
import { Statistic } from '../../models/Statistic';
import { StatisticService } from '../../service/statistic.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatSelectModule } from '@angular/material/select';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-statistic',
  imports: [CommonModule, ReactiveFormsModule, MatSelectModule],
  templateUrl: './statistic.component.html',
  styleUrl: './statistic.component.css'
})
export class StatisticComponent {
  statistics!: Statistic[];
  statisticForm!:FormGroup;
   constructor(private statisticService:StatisticService,
      private fb:FormBuilder, private router:Router) { }

      ngOnInit(): void {
        this.statisticForm = this.createStatistic();
      }

      createStatistic():FormGroup {
        return this.fb.group({
          dateFrom:['', Validators.compose([Validators.required])],
          dateTo: ['', Validators.compose([Validators.required])]
        });
      }

      submit(): void {
        const { dateFrom, dateTo } = this.statisticForm.value;
        this.statisticService.getStatistics(dateFrom, dateTo).subscribe( data => {
          this.statistics = data;
          
          console.log(this.statistics);  
          // window.location.reload();
      });
      }
}
