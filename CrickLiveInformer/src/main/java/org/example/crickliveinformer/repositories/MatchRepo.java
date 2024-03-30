package org.example.crickliveinformer.repositories;

import org.example.crickliveinformer.entities.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepo extends JpaRepository<Match,Long> {
//    match fatch teamHeading
    Optional<Match> findByTeamHeading(String teamHeading);//class Property
}
