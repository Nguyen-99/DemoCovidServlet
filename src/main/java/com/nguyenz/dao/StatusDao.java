package com.nguyenz.dao;

import com.nguyenz.model.Status;

import java.util.List;

public interface StatusDao {
    List<Status> getByPatientId(int patientId);
    Status getById(int id);
    void add(Status status);
    void update(Status status);
    void delete(int id);
}
