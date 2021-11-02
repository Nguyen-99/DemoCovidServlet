package com.nguyenz.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nguyenz.model.Patient;
import com.nguyenz.regex.MyRegex;
import com.nguyenz.utils.HibernateUtils;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PatientDaoHibernate implements PatientDao {
    private Logger logger = Logger.getLogger(PatientDaoHibernate.class);
    SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    StatusDao statusDao = new StatusDaoHibernate();
    @Override
    public List<Patient> getAll() {
        List<Patient> patientList = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            patientList = session.createQuery("FROM Patient").list();
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
            logger.error("error"+e);
        }finally {
            session.close();
        }
//        for (Patient patient:patientList) {
//            patient.setStatusList(statusDao.getByPatientId(patient.getId()));
//        }
        logger.info("get all patients");
        return patientList;
    }

    @Override
    public Patient getById(int id) {
        Patient patient = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            patient = session.get(Patient.class,id);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
            logger.error("error"+e);
        }finally {
            session.close();
        }
//        patient.setStatusList(statusDao.getByPatientId(id));
        logger.info("get patient by id");
        return patient;
    }

    @Override
    public void add(Patient patient) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(patient);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
            logger.error("error"+e);
        }finally {
            session.close();
        }
    }

    @Override
    public void update(Patient patient) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(patient);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
            logger.error("error"+e);
        }finally {
            session.close();
        }
        logger.info("update patient");
    }

    @Override
    public boolean delete(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Patient patient = session.get(Patient.class,id);
            session.delete(patient);
            transaction.commit();
            logger.error("delete patient");
            return true;
        }catch (HibernateException e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
            logger.error("error"+e);
        }finally {
            session.close();
        }
        return false;
    }

    @Override
    public List<Patient> getTenNewPatients() {
        List<Patient> patientList = null;
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "FROM Patient P ORDER BY P.id DESC";
            patientList = session.createQuery(hql).setMaxResults(10).list();
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
            logger.error("error"+e);
        }finally {
            session.close();
        }
//        for (Patient patient:patientList) {
//            patient.setStatusList(statusDao.getByPatientId(patient.getId()));
//        }
        logger.info("get 10 new patient");
        return patientList;
    }

    @Override
    public List<Patient> search(String search) {
        List<Patient> patientList = new ArrayList<>();
        for (Patient patient:getAll()) {
            if(MyRegex.search(search.toLowerCase(),patient.getName().toLowerCase())
                    || MyRegex.search(search,patient.getAddress())
                    || MyRegex.search(search,patient.getLastStatus().getName())) {
                patientList.add(patient);
            }
        }
        logger.info("search:"+search);
        return patientList;
    }
    public static void main(String[] args) {
        PatientDao patientDao = new PatientDaoHibernate();
        Patient patient = patientDao.getById(42);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";
        try {
            json = objectMapper.writeValueAsString(patient);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(json);

    }
}
