package com.hospital.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dao.DoctorDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Doctor;

@WebServlet("/DoctorServlet")
public class DoctorServlet extends HttpServlet {
    private DoctorDAO doctorDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        doctorDAO = new DoctorDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("add".equals(action)) {
            addDoctor(request, response);
        } else if ("update".equals(action)) {
            updateDoctor(request, response);
        } else if ("delete".equals(action)) {
            deleteDoctor(request, response);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("list".equals(action)) {
            listDoctors(request, response);
        } else if ("get".equals(action)) {
            getDoctor(request, response);
        } else if ("getBySpecialization".equals(action)) {
            getDoctorsBySpecialization(request, response);
        } else {
            // Default: show all doctors
            listDoctors(request, response);
        }
    }
    
    private void addDoctor(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            Doctor doctor = new Doctor();
            doctor.setFirstName(request.getParameter("firstName"));
            doctor.setLastName(request.getParameter("lastName"));
            doctor.setSpecialization(request.getParameter("specialization"));
            doctor.setPhoneNumber(request.getParameter("phoneNumber"));
            doctor.setEmail(request.getParameter("email"));
            doctor.setLicenseNumber(request.getParameter("licenseNumber"));
            
            if (request.getParameter("departmentId") != null && 
                !request.getParameter("departmentId").isEmpty()) {
                doctor.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
            }
            
            doctor.setAvailability(request.getParameter("availability"));
            
            if (request.getParameter("consultationFee") != null && 
                !request.getParameter("consultationFee").isEmpty()) {
                doctor.setConsultationFee(Double.parseDouble(request.getParameter("consultationFee")));
            }
            
            boolean success = doctorDAO.addDoctor(doctor);
            
            if (success) {
                request.setAttribute("message", "Doctor added successfully!");
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Failed to add doctor.");
                request.setAttribute("messageType", "error");
            }
            
        } catch (Exception e) {
            request.setAttribute("message", "Error: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listDoctors(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Doctor> doctors = doctorDAO.getAllDoctors();
        request.setAttribute("doctors", doctors);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void getDoctor(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            Doctor doctor = doctorDAO.getDoctorById(doctorId);
            
            if (doctor != null) {
                request.setAttribute("doctor", doctor);
                RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-details.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Doctor not found");
            }
            
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid doctor ID");
        }
    }
    
    private void updateDoctor(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            Doctor doctor = new Doctor();
            doctor.setDoctorId(Integer.parseInt(request.getParameter("doctorId")));
            doctor.setFirstName(request.getParameter("firstName"));
            doctor.setLastName(request.getParameter("lastName"));
            doctor.setSpecialization(request.getParameter("specialization"));
            doctor.setPhoneNumber(request.getParameter("phoneNumber"));
            doctor.setEmail(request.getParameter("email"));
            doctor.setLicenseNumber(request.getParameter("licenseNumber"));
            
            if (request.getParameter("departmentId") != null && 
                !request.getParameter("departmentId").isEmpty()) {
                doctor.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
            }
            
            doctor.setAvailability(request.getParameter("availability"));
            
            if (request.getParameter("consultationFee") != null && 
                !request.getParameter("consultationFee").isEmpty()) {
                doctor.setConsultationFee(Double.parseDouble(request.getParameter("consultationFee")));
            }
            
            boolean success = doctorDAO.updateDoctor(doctor);
            
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            
            if (success) {
                out.print("{\"status\":\"success\",\"message\":\"Doctor updated successfully\"}");
            } else {
                out.print("{\"status\":\"error\",\"message\":\"Failed to update doctor\"}");
            }
            
        } catch (Exception e) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }
    
    private void deleteDoctor(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            int doctorId = Integer.parseInt(request.getParameter("doctorId"));
            boolean success = doctorDAO.deleteDoctor(doctorId);
            
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            
            if (success) {
                out.print("{\"status\":\"success\",\"message\":\"Doctor deleted successfully\"}");
            } else {
                out.print("{\"status\":\"error\",\"message\":\"Failed to delete doctor\"}");
            }
            
        } catch (Exception e) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }
    
    private void getDoctorsBySpecialization(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String specialization = request.getParameter("specialization");
        List<Doctor> doctors = doctorDAO.getDoctorsBySpecialization(specialization);
        
        request.setAttribute("doctors", doctors);
        request.setAttribute("specialization", specialization);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-by-specialization.jsp");
        dispatcher.forward(request, response);
    }
}