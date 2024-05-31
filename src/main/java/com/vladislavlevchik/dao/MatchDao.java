package com.vladislavlevchik.dao;

import com.vladislavlevchik.entity.Match;
import com.vladislavlevchik.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MatchDao implements IMatchDao {

    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();

    @Override
    public List<Match> findAll() {

        try (Session session = sessionFactory.openSession()) {

            return session.createQuery("SELECT m FROM Match m", Match.class).list();

        }

    }

    @Override
    public Match save(Match entity) {

        try (Session session = sessionFactory.openSession()) {

            session.save(entity);

            return entity;
        }

    }

    @Override
    public List<Match> findAllMatchesByPlayerName(String name) {

        try (Session session = sessionFactory.openSession()) {

            return session.createQuery("SELECT m FROM Match m WHERE m.playerOne.name = :name OR m.playerTwo.name = :name", Match.class)
                    .setParameter("name", name)
                    .list();

        }

    }
}
