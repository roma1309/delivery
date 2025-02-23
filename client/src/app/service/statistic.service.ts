import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Statistic } from '../models/Statistic';

const STATISTIC_API = 'http://localhost:8081/stat';

@Injectable({
  providedIn: 'root'
})
export class StatisticService {

  constructor(private http: HttpClient) { }

  getStatistics(dateFrom: string, dateTo: string):Observable<Statistic[]>{
    return this.http.get<Statistic[]>(STATISTIC_API +'?dateFrom=' + dateFrom + '&dateTo='+ dateTo)
  }
}
