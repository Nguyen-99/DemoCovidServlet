package com.nguyenz.dao;

import com.nguyenz.model.Status;
import com.nguyenz.utils.HibernateUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class StatusDaoHibernate implements StatusDao{
    Logger logger = Logger.getLogger(StatusDaoHibernate.class);
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    @Override
    public List<Status> getByPatientId(int patientId) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Status> statusList = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM Status s WHERE s.patient.id = :patient_id";
            Query query = session.createQuery(hql);
            query.setParameter("patient_id",patientId);
            statusList = query.getResultList();
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            logger.error("error:"+e);
        } finally {
            session.close();
        }
        logger.info("Get status by patient id");
        return statusList;
    }

    @Override
    public Status getById(int id) {
        Status status = null;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Status> statusList = null;
        try {
            tx = session.beginTransaction();
            status = session.get(Status.class,id);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            logger.error("error"+e);
        } finally {
            session.close();
        }
        logger.info("get status by id");
        return status;
    }

    @Override
    public void add(Status status) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(status);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            logger.error("error:"+e);
        } finally {
            session.close();
        }
        logger.info("add status");
    }

    @Override
    public void update(Status status) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(status);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            logger.error("error:"+e);
        } finally {
            session.close();
        }
        logger.info("update status");
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Status status = session.get(Status.class,id);
            session.delete(status);
            tx.commit();
        } catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
            logger.error("error"+e);
        } finally {
            session.close();
        }
        logger.info("delete");
    }
}
