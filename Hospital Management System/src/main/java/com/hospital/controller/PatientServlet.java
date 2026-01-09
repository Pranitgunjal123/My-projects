package com.hospital.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import dao.PatientDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Patient;

@WebServlet("/PatientServlet")
public class PatientServlet extends HttpServlet {
    private PatientDAO patientDAO;
    
    @Override
    public void init() throws ServletException {
        super.init();
        patientDAO = new PatientDAO();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("register".equals(action)) {
            registerPatient(request, response);
        } else if ("update".equals(action)) {
            updatePatient(request, response);
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("list".equals(action)) {
            listPatients(request, response);
        } else if ("get".equals(action)) {
            getPatient(request, response);
        } else if ("search".equals(action)) {
            searchPatients(request, response);
        } else {
            // Default: show registration form
            showRegistrationForm(request, response);
        }
    }
    
    private void registerPatient(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            Patient patient = new Patient();
            patient.setFirstName(request.getParameter("firstName"));
            patient.setLastName(request.getParameter("lastName"));
            
            String dobStr = request.getParameter("dateOfBirth");
            if (dobStr != null && !dobStr.isEmpty()) {
                patient.setDateOfBirth(Date.valueOf(dobStr));
            }
            
            patient.setGender(request.getParameter("gender"));
            patient.setPhoneNumber(request.getParameter("phoneNumber"));
            patient.setEmail(request.getParameter("email"));
            patient.setAddress(request.getParameter("address"));
            patient.setBloodGroup(request.getParameter("bloodGroup"));
            patient.setEmergencyContact(request.getParameter("emergencyContact"));
            
            boolean success = patientDAO.addPatient(patient);
            
            if (success) {
                request.setAttribute("message", "Patient registered successfully! Patient ID: " + 
                    patientDAO.getLastInsertedId());
                request.setAttribute("messageType", "success");
            } else {
                request.setAttribute("message", "Failed to register patient.");
                request.setAttribute("messageType", "error");
            }
            
        } catch (Exception e) {
            request.setAttribute("message", "Error: " + e.getMessage());
            request.setAttribute("messageType", "error");
            e.printStackTrace();
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listPatients(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<Patient> patients = patientDAO.getAllPatients();
        request.setAttribute("patients", patients);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void showRegistrationForm(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void getPatient(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            int patientId = Integer.parseInt(request.getParameter("patientId"));
            Patient patient = patientDAO.getPatientById(patientId);
            
            if (patient != null) {
                request.setAttribute("patient", patient);
                RequestDispatcher dispatcher = request.getRequestDispatcher("patient-details.jsp");
                dispatcher.forward(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Patient not found");
            }
            
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid patient ID");
        }
    }
    
    private void updatePatient(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            Patient patient = new Patient();
            patient.setPatientId(Integer.parseInt(request.getParameter("patientId")));
            patient.setFirstName(request.getParameter("firstName"));
            patient.setLastName(request.getParameter("lastName"));
            
            String dobStr = request.getParameter("dateOfBirth");
            if (dobStr != null && !dobStr.isEmpty()) {
                patient.setDateOfBirth(Date.valueOf(dobStr));
            }
            
            patient.setGender(request.getParameter("gender"));
            patient.setPhoneNumber(request.getParameter("phoneNumber"));
            patient.setEmail(request.getParameter("email"));
            patient.setAddress(request.getParameter("address"));
            patient.setBloodGroup(request.getParameter("bloodGroup"));
            patient.setEmergencyContact(request.getParameter("emergencyContact"));
            
            boolean success = patientDAO.updatePatient(patient);
            
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            
            if (success) {
                out.print("{\"status\":\"success\",\"message\":\"Patient updated successfully\"}");
            } else {
                out.print("{\"status\":\"error\",\"message\":\"Failed to update patient\"}");
            }
            
        } catch (Exception e) {
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print("{\"status\":\"error\",\"message\":\"" + e.getMessage() + "\"}");
            e.printStackTrace();
        }
    }
    
    private void searchPatients(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String searchTerm = request.getParameter("searchTerm");
        List<Patient> patients = patientDAO.searchPatients(searchTerm);
        
        request.setAttribute("patients", patients);
        request.setAttribute("searchTerm", searchTerm);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-search-results.jsp");
        dispatcher.forward(request, response);
    }
}