package com.vladislavlevchik.dao;

import com.vladislavlevchik.entity.Player;
import com.vladislavlevchik.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class PlayerDao implements IPlayerDao {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Optional<Player> findByName(String name) {

        try (Session session = sessionFactory.openSession()) {

            return session.createQuery("SELECT p FROM Player p WHERE p.name = :name", Player.class)
                    .setParameter("name", name).uniqueResultOptional();

        }

    }

    @Override
    public Player save(Player entity) {
        try (Session session = sessionFactory.openSession()) {

            session.save(entity);

            return entity;
        }
    }

    @Override
    public List<Player> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT p FROM Player p", Player.class).list();
        }
    }


}
