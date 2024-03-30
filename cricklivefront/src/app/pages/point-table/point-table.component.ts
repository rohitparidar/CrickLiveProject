import { Component, OnInit } from '@angular/core';
import { ApiCallService } from '../../services/api-call.service';
import { error, log } from 'console';
import { CommonModule } from '@angular/common';
import { MatchCardComponent } from '../../card-components/match-card/match-card.component';

@Component({
  selector: 'app-point-table',
  standalone: true,
  imports: [CommonModule,MatchCardComponent],
  templateUrl: './point-table.component.html',
  styleUrl: './point-table.component.css'
})
export class PointTableComponent implements OnInit {
  allPointData: any;
  tableHeading:any;

  constructor(private _api: ApiCallService){}
  ngOnInit(): void {
    this.loadAllPointTable();
  }

  private loadAllPointTable(){
    this._api.getPointTable().subscribe({
      next:data =>{
        this.allPointData = data;
        this.tableHeading = [...this.allPointData[0]];
        console.log(this.tableHeading);
        
      },
      error:error=>{
        console.log(error);
      }
    })
  }
}
