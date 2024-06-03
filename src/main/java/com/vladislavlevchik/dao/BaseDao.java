package com.vladislavlevchik.dao;

import com.vladislavlevchik.entity.Player;
import com.vladislavlevchik.utils.HibernateUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseDao<E, K> implements CrudDao<E, K> {

    private final Class<E> clazz;
    protected final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<E> findAll() {
        try (Session session = sessionFactory.openSession()) {
            var criteria = session.getCriteriaBuilder().createQuery(clazz);
            criteria.from(clazz);

            return session.createQuery(criteria).list();
        }
    }

    @Override
    public Optional<E> findById(K id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(clazz, id));
        }
    }

    @Override
    public E save(E entity) {
        try (Session session = sessionFactory.openSession()) {

            session.save(entity);

            return entity;
        }
    }

    @Override
    public void update(E entity) {
        try (Session session = sessionFactory.openSession()) {
            session.merge(entity);
        }
    }

    @Override
    public void delete(K id) {
        try (Session session = sessionFactory.openSession()) {
            session.remove(id);
            session.flush();
        }
    }

}
