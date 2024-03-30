package org.example.crickliveinformer.services;

import org.example.crickliveinformer.entities.Commentary;
import org.example.crickliveinformer.entities.Match;
import org.example.crickliveinformer.repositories.MatchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class MatchServiceImpl implements  MatchService{
    @Autowired
    private MatchRepo matchRepo ;
    @Override
    public List<Match> getAllMatches() {
        return matchRepo.findAll();
    }

    @Override
    public List<Match> getLiveMatchScores() {
        List<Match> matches = new ArrayList<>();
        try {
            String url = "https://www.cricbuzz.com/cricket-match/live-scores";
            Document document = Jsoup.connect(url).get();
            Elements liveScoreElements = document.select("div.cb-mtch-lst.cb-tms-itm");
            for (Element match : liveScoreElements) {
                HashMap<String, String> liveMatchInfo = new LinkedHashMap<>();
                String teamsHeading = match.select("h3.cb-lv-scr-mtch-hdr").select("a").text();
                String matchNumberVenue = match.select("span").text();
                Elements matchBatTeamInfo = match.select("div.cb-hmscg-bat-txt");
                String battingTeam = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String score = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                Elements bowlTeamInfo = match.select("div.cb-hmscg-bwl-txt");
                String bowlTeam = bowlTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String bowlTeamScore = bowlTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                String textLive = match.select("div.cb-text-live").text();
                String textComplete = match.select("div.cb-text-complete").text();
                //getting match link
                String matchLink = match.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

                Match match1 = new Match();
                match1.setTeamHeading(teamsHeading);
                match1.setMatchNumberVenue(matchNumberVenue);
                match1.setBattingTeam(battingTeam);
                match1.setBattingTeamScore(score);
                match1.setBowlTeam(bowlTeam);
                match1.setBowlTeamScore(bowlTeamScore);
                match1.setLiveText(textLive);
                match1.setMatchLink(matchLink);
                match1.setTextComplete(textComplete);
                match1.setMatchStatus();


                matches.add(match1);

//                update the match in database


                updateMatch(match1);


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    private void updateMatch(Match match1) {

        Match match = this.matchRepo.findByTeamHeading(match1.getTeamHeading()).orElse(null);
        if (match == null) {
            this.matchRepo.save(match1);
        } else {

            match1.setMatchId(match.getMatchId());
            this.matchRepo.save(match1);
        }

    }
    @Override
    public List<List<String>> getCWC2023PointTable() {
        List<List<String>> pointTable = new ArrayList<>();
//        String tableURL = "https://www.cricbuzz.com/cricket-series/6732/icc-cricket-world-cup-2023/points-table";
        String tableURL = "https://www.cricbuzz.com/cricket-series/7607/indian-premier-league-2024/points-table";
        try {
            Document document = Jsoup.connect(tableURL).get();
            Elements table = document.select("table.cb-srs-pnts");
            Elements tableHeads = table.select("thead>tr>*");
            List<String> headers = new ArrayList<>();
            tableHeads.forEach(element -> {
                headers.add(element.text());
            });
            pointTable.add(headers);
            Elements bodyTrs = table.select("tbody>*");
            bodyTrs.forEach(tr -> {
                List<String> points = new ArrayList<>();
                if (tr.hasAttr("class")) {
                    Elements tds = tr.select("td");
                    String team = tds.get(0).select("div.cb-col-84").text();
                    points.add(team);
                    tds.forEach(td -> {
                        if (!td.hasClass("cb-srs-pnts-name")) {
                            points.add(td.text());
                        }
                    });
//                    System.out.println(points);
                    pointTable.add(points);
                }


            });

            System.out.println(pointTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pointTable;
    }

  // comentry method

    @Override
    public List<Commentary> getLiveCommentary() {
        List<Commentary> commentaryList = new ArrayList<>();
        try {
//            String url= "https://www.cricbuzz.com/cricket-full-commentary/88956/sl-vs-ban-1st-test-sri-lanka-tour-of-bangladesh-2024";
            String url = "https://www.cricbuzz.com/live-cricket-full-commentary/89654/csk-vs-rcb-1st-match-indian-premier-league-2024";
            Document document = Jsoup.connect(url).get();
            Elements liveScoreElements = document.select("div.cb-lv-scrs-col");
            for (Element scoreElement : liveScoreElements) {
                String matchTitle = scoreElement.select("a.text-hvr-underline").text();
                String matchLink = scoreElement.select("a.text-hvr-underline").attr("href");
                String commentary = fetchCommentaryForMatch(matchLink);
                Commentary commentaryObj = new Commentary();
                commentaryObj.setMatchTitle(matchTitle);
                commentaryObj.setCommentary(commentary);
                commentaryList.add(commentaryObj);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return commentaryList;
    }

    private String fetchCommentaryForMatch(String matchLink) {
        try {
            Document document = Jsoup.connect(matchLink).get();
            Elements commentaryElements = document.select("div.cb-col.cb-col-100.cb-comm-static");
            // Extract commentary text and return
            return commentaryElements.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}
