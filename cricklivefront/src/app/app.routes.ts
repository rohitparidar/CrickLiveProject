import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { HistoryComponent } from './pages/history/history.component';
import { LiveScoreComponent } from './pages/live-score/live-score.component';
import { PointTableComponent } from './pages/point-table/point-table.component';

export const routes: Routes = [
    {
        path:'',
        redirectTo:'/liveScore',
        pathMatch:'full'
    },
    {
        path:"home",
        component: HomeComponent,
        title: "Home | CrickLiveInformer"
    },
    {
        path:"history",
        component: HistoryComponent,
        title: "Match History | CrickLiveInformer"
    },
    {
        path:"liveScore",
        component: LiveScoreComponent,
        title: "Live Matches | CrickLiveInformer"
    },
    {
        path:"point-table",
        component: PointTableComponent,
        title: "Point Table | CrickLiveInformer"
    }
];
