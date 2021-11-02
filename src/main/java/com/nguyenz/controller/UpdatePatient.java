package com.nguyenz.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nguyenz.dao.PatientDao;
import com.nguyenz.dao.PatientDaoHibernate;
import com.nguyenz.dao.StatusDao;
import com.nguyenz.dao.StatusDaoHibernate;
import com.nguyenz.model.Patient;
import com.nguyenz.model.Status;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(value = "/update-patient")
public class UpdatePatient extends HttpServlet {
    PatientDao patientDao = new PatientDaoHibernate();
    StatusDao statusDao = new StatusDaoHibernate();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        Patient patient = new Patient("Nguyendz",new Date(),true,"HaNoi");
        System.out.println(patient.getName()+"-"+patient.getAddress());
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(patient);
        System.out.println(json);
        PrintWriter writer = resp.getWriter();
        writer.print(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        boolean gender = Boolean.parseBoolean(req.getParameter("gender"));
        String sDob = req.getParameter("dob");
        Date dob = new Date();
        try {
            dob = format.parse(sDob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String address = req.getParameter("address");
        Patient patient = new Patient(name,dob,gender,address);
        patient.setId(id);
        patientDao.update(patient);

        int statusId = Integer.parseInt(req.getParameter("status_id"));
        String nameStatus = req.getParameter("status");
        String sStartDate = req.getParameter("start_date");
        Date startDate = new Date();
        try {
            startDate = format.parse(sDob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Status status = new Status(nameStatus,startDate);
        status.setId(statusId);
        statusDao.update(status);
        resp.sendRedirect("home");
    }
}
