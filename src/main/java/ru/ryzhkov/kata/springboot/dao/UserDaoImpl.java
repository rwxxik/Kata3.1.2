package ru.ryzhkov.kata.springboot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ryzhkov.kata.springboot.models.User;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager em;

    @PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        em.persist(user);
        em.flush();
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        Query query = em.createQuery("SELECT c from User c", User.class);
        return query.getResultList();
    }

    @Override
    public User getUser(int id) {
        TypedQuery<User> query = em.createQuery("SELECT c from User c where c.id = :userId", User.class);
        query.setParameter("userId", id);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public void updateUser(int id, User updatedUser) {
        User userToBeUpdated = getUser(id);
        userToBeUpdated.setFirstName(updatedUser.getFirstName());
        userToBeUpdated.setLastName(updatedUser.getLastName());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        em.merge(userToBeUpdated);
        em.flush();

    }

    @Override
    @Transactional
    public void removeUser(int id) {
        User userToBeRemoved = getUser(id);
        em.remove(userToBeRemoved);
        em.flush();
    }
}
