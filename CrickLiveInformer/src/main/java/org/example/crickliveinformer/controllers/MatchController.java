package org.example.crickliveinformer.controllers;


import org.example.crickliveinformer.entities.Commentary;
import org.example.crickliveinformer.entities.Match;
import org.example.crickliveinformer.services.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/match")
@CrossOrigin("*")
public class MatchController {

    @Autowired
    private MatchService matchService;

    // get Live MAtches
    @GetMapping("/live")
    public ResponseEntity<List<Match>> getLiveMatchScores() {
        return new ResponseEntity<>(matchService.getLiveMatchScores(), HttpStatus.OK);
    }

//    getAll Matches
    @GetMapping("/all")
    public ResponseEntity<List<Match>> getAllMatches() {
        return new ResponseEntity<>(matchService.getAllMatches(), HttpStatus.OK);
    }

//    get point table
    @GetMapping("/point-table")
    public ResponseEntity<?> getCWC2023PointTable() {
        return new ResponseEntity<>(matchService.getCWC2023PointTable(), HttpStatus.OK);
    }


//    commentary
@GetMapping("/liveCommentary")
public ResponseEntity<List<Commentary>> getLiveCommentary() {
    List<Commentary> commentaryList = matchService.getLiveCommentary();
    return new ResponseEntity<>(commentaryList, HttpStatus.OK);
}
}


















//@GetMapping("/live-video")
//public ResponseEntity<Resource> getLiveVideo() {
//    // Replace "https://example.com/live-video-stream" with your actual live video source URL
//    String videoUrl = "https://example.com/live-video-stream";
//
//    try {
//        // Convert the video URL to URI
//        URI uri = new URI(videoUrl);
//        // Create a resource representing the video file
//        Resource videoResource = loadVideoResource(uri);
//        // Set the Content-Type header
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        // Return the video file as ResponseEntity
//        return ResponseEntity.ok()
//                .headers(headers)
//                .body(videoResource);
//    } catch (URISyntaxException e) {
//        // Handle URI syntax exception
//        e.printStackTrace();
//        return ResponseEntity.notFound().build();
//    }
//}
//
//private Resource loadVideoResource(URI uri) {
//    // Implement logic to load the video resource from the URI
//    // For example, you can use ResourceLoader or create a new Resource object from the URI
//    // Here's a simplified example assuming the video file is stored locally:
//    Path videoPath = Paths.get(uri);
//    Resource videoResource = new FileSystemResource(videoPath);
//    return videoResource;
//}