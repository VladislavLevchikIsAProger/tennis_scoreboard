package com.vladislavlevchik.dao;

import com.vladislavlevchik.entity.Match;
import org.hibernate.Session;

import java.util.List;

public class MatchRepository extends BaseRepository<Match, Long> {

    public MatchRepository() {
        super(Match.class);
    }

    public List<Match> findAllMatchesByPlayerName(String name) {

        try (Session session = sessionFactory.openSession()) {

            return session.createQuery("SELECT m FROM Match m WHERE m.playerOne.name = :name OR m.playerTwo.name = :name", Match.class)
                    .setParameter("name", name)
                    .list();

        }

    }
}
