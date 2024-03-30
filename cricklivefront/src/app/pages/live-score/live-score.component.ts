import { Component, OnInit } from '@angular/core';
import { ApiCallService } from '../../services/api-call.service';
import { error, log } from 'console';
import { CommonModule } from '@angular/common';
import { MatchCardComponent } from '../../card-components/match-card/match-card.component';
import { response } from 'express';

@Component({
  selector: 'app-live-score',
  standalone: true,
  imports: [CommonModule,MatchCardComponent],
  templateUrl: './live-score.component.html',
  styleUrl: './live-score.component.css'
})
export class LiveScoreComponent implements OnInit {

  liveMatches: any;
  commentaryLive:any;

  constructor(private api: ApiCallService) { }

  ngOnInit(): void {
  this.loadLiveMatches();
  this.livecommentries();
  }
  private loadLiveMatches(){
    this.api.getAllLiveMatches().subscribe({
      next: data => {
        console.log(data);
        this.liveMatches=data;
      },
      error: error => {
        console.log(error);
      }
    })
  }

  private livecommentries(){
    this.api.getCommentary().subscribe({
      next: response => {
        console.log(response);
        this.commentaryLive=response;
      },
      error: error => {
        console.log(error);
      }
    })
  }
}
