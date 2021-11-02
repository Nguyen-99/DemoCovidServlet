package com.nguyenz.dao;

import com.nguyenz.model.Patient;

import java.util.List;

public interface PatientDao {
    List<Patient> getAll();
    Patient getById(int id);
    void add(Patient patient);
    void update(Patient patient);
    boolean delete(int id);
    List<Patient> getTenNewPatients();
    List<Patient> search(String search);
}
