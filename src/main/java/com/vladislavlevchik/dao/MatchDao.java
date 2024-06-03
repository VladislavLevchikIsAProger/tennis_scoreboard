package com.vladislavlevchik.dao;

import com.vladislavlevchik.entity.Match;
import com.vladislavlevchik.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MatchDao extends BaseDao<Match, Long> {

    public MatchDao() {
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
