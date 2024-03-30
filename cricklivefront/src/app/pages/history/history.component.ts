import { Component, OnInit } from '@angular/core';
import { ApiCallService } from '../../services/api-call.service';
import { error } from 'console';
import { CommonModule } from '@angular/common';
import { MatchCardComponent } from '../../card-components/match-card/match-card.component';

@Component({
  selector: 'app-history',
  standalone: true,
  imports: [CommonModule,MatchCardComponent],
  templateUrl: './history.component.html',
  styleUrl: './history.component.css'
})
export class HistoryComponent implements OnInit {
  allMatch: any;
  constructor(private _api:ApiCallService){}
  ngOnInit(): void {
    this.loadAllHistory();
  }
  private loadAllHistory(){
    this._api.getAllMatches().subscribe({
      next:data=>{
        this.allMatch = data;
      },
      error:error=>{
        console.log(error);
      }
    })
  }

}
