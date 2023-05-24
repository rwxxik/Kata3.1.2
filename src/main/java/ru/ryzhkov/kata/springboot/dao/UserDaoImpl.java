package ru.ryzhkov.kata.springboot.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ryzhkov.kata.springboot.model.User;

import java.util.List;


@Repository
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
        entityManager.flush();
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery("SELECT c from User c", User.class);
        return query.getResultList();
    }

    @Override
    public User getUser(int id) {
        TypedQuery<User> query = entityManager.createQuery("SELECT c from User c where c.id = :userId", User.class);
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
        entityManager.merge(userToBeUpdated);
        entityManager.flush();

    }

    @Override
    @Transactional
    public void removeUser(int id) {
        User userToBeRemoved = getUser(id);
        entityManager.remove(userToBeRemoved);
        entityManager.flush();
    }
}
