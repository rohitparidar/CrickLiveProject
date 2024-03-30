package org.example.crickliveinformer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.crickliveinformer.util.enums.MatchStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "crick_live_match")
public class Match
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long matchId;
    private String teamHeading;
    private String matchNumberVenue;
    private String battingTeam;
    private String battingTeamScore;
    private String bowlTeam;
    private String bowlTeamScore;

    private String liveText;

    private String matchLink;

    private String textComplete;
    private String liveVideos;

    @Enumerated
    private MatchStatus status;


    private Date date=new Date();

    public void setMatchStatus() {
        if (this.textComplete.trim().isBlank()) {
            this.status = MatchStatus.LIVE;
        } else {
            this.status = MatchStatus.COMPLETED;
        }
    }
}
