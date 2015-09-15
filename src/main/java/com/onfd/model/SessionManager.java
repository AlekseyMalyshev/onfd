/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.onfd.model;

import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Aleksey Malyshev
 */
public class SessionManager {

    private static final Logger _log;

    private static SessionManager sessionManager;

    @Autowired
    protected SessionFactory sessionFactory;

    static {
        _log = Logger.getLogger(SessionManager.class.getName());
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @PostConstruct
    public void init() {
        sessionManager = this;
    }
    
    @SuppressWarnings("unchecked")
    @Transactional
    public <T> List<T> query(String q, Object... params) {
        Session session = getSession();
        Query query = session.createQuery(q);
        int i = 0;
        for (Object param : params) {
            query.setParameter("p" + i++, param.toString());
        }
        return (List<T>) query.list();
    }

    @SuppressWarnings("unchecked")
    @Transactional
    public <T> T load(Class<T> clazz, Integer id) {
        Session session = getSession();
        return (T) session.load(clazz, id);
    }

    @Transactional
    public <T> T getOne(String q, Object... params) {
        T result = null;
        List<T> results = query(q, params);
        if (results != null && results.size() == 1) {
            result = results.get(0);
        }
        return (T) result;
    }

    @Transactional
    public void saveOrUpdate(Object o) {
        Session session = getSession();
        session.saveOrUpdate(o);
    }

    @Transactional
    public void save(Object o) {
        Session session = getSession();
        session.save(o);
    }

    @Transactional
    public void update(Object o) {
        Session session = getSession();
        session.update(o);
    }

    @Transactional
    public void delete(Object o) {
        Session session = getSession();
        session.delete(o);
    }

}
