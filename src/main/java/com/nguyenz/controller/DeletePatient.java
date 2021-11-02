package com.nguyenz.controller;

import com.nguyenz.dao.PatientDao;
import com.nguyenz.dao.PatientDaoHibernate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/delete-patient")
public class DeletePatient extends HttpServlet {
    PatientDao patientDao = new PatientDaoHibernate();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        patientDao.delete(id);
        resp.sendRedirect("home");
    }
}
