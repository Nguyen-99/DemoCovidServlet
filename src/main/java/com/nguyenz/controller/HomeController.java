package com.nguyenz.controller;

import com.nguyenz.dao.PatientDao;
import com.nguyenz.dao.PatientDaoHibernate;
import com.nguyenz.dao.StatusDao;
import com.nguyenz.dao.StatusDaoHibernate;
import com.nguyenz.file_io.MyConstants;
import com.nguyenz.model.Patient;
import com.nguyenz.model.Status;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@WebServlet(value = "/home")
public class HomeController extends HttpServlet {
    PatientDao  patientDao = new PatientDaoHibernate();
    StatusDao statusDao = new StatusDaoHibernate();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        List<Patient> patientList = patientDao.getAll();
        request.setAttribute("patientList",patientList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view/home.jsp");
        dispatcher.forward(request,response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");

        String name = request.getParameter("name");
        boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String sDob = request.getParameter("dob");
        Date dob = new Date();
        try {
            dob = format.parse(sDob);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String address = request.getParameter("address");
        //avatar
        Patient patient = new Patient(name,dob,gender,address);
        patientDao.add(patient);
        String statusName = request.getParameter("status");
        String sStartDate = request.getParameter("start_date");
        Date startDate = new Date();
        try {
            startDate = format.parse(sStartDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Status status = new Status(statusName,startDate);
        status.setPatient(patient);
        statusDao.add(status);
        response.sendRedirect(request.getContextPath()+"/home");
    }
}
