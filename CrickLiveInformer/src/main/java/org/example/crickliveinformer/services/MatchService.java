package org.example.crickliveinformer.services;

import org.example.crickliveinformer.entities.Commentary;
import org.example.crickliveinformer.entities.Match;

import java.util.List;

public interface MatchService {
//    get all Matches
List<Match> getAllMatches();

//get Live Matches
    List<Match> getLiveMatchScores();

//    get crick word cup 2023 point table
    List<List<String>> getCWC2023PointTable();

    List<Commentary> getLiveCommentary();
}
