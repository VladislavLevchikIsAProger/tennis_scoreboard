package com.vladislavlevchik.dao;

import com.vladislavlevchik.entity.Player;
import com.vladislavlevchik.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class PlayerDao extends BaseDao<Player,Long>{

    public PlayerDao() {
        super(Player.class);
    }

    public Optional<Player> findByName(String name) {

        try (Session session = sessionFactory.openSession()) {

            return session.createQuery("SELECT p FROM Player p WHERE p.name = :name", Player.class)
                    .setParameter("name", name).uniqueResultOptional();

        }

    }

}
