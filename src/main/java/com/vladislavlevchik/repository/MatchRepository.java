package com.vladislavlevchik.repository;

import com.vladislavlevchik.entity.Match;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MatchRepository extends BaseRepository<Match, Long> {

    public MatchRepository() {
        super(Match.class);
    }

    public List<Match> findAllByPlayerNameWithPagination(String name, int pageSize, int page) {
        try (Session session = sessionFactory.openSession()) {
            Query<Match> query = session
                    .createQuery("SELECT m FROM Match m " +
                                    "WHERE m.playerOne.name = :name OR m.playerTwo.name = :name", Match.class)
                    .setParameter("name", name);

            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        }
    }

    public List<Match> findAllWithPagination(int pageSize, int page) {
        try (Session session = sessionFactory.openSession()) {
            Query<Match> query = session.createQuery("SELECT m FROM Match m", Match.class);

            query.setFirstResult((page - 1) * pageSize);
            query.setMaxResults(pageSize);
            return query.list();
        }
    }

    public long getCount() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT COUNT(m) FROM Match m", Long.class).uniqueResult();
        }
    }

    public long getCountByPlayerName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("SELECT COUNT(m) FROM Match m " +
                                    "WHERE m.playerOne.name = :name OR m.playerTwo.name = :name", Long.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }
}
