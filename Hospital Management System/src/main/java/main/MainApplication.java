package main;

import dao.PatientDAO;
import dao.DoctorDAO;
import dao.AppointmentDAO;
import model.Patient;
import model.Doctor;
import model.Appointment;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class MainApplication {
    private static Scanner scanner = new Scanner(System.in);
    private static PatientDAO patientDAO = new PatientDAO();
    private static DoctorDAO doctorDAO = new DoctorDAO();
    private static AppointmentDAO appointmentDAO = new AppointmentDAO();
    
    public static void main(String[] args) {
        System.out.println("=== Hospital Management System ===");
        System.out.println("Database Backend Version\n");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            
            switch (choice) {
                case 1:
                    managePatients();
                    break;
                case 2:
                    manageDoctors();
                    break;
                case 3:
                    manageAppointments();
                    break;
                case 4:
                    displayStatistics();
                    break;
                case 5:
                    System.out.println("Exiting system...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Manage Patients");
        System.out.println("2. Manage Doctors");
        System.out.println("3. Manage Appointments");
        System.out.println("4. Display Statistics");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static void managePatients() {
        System.out.println("\n===== PATIENT MANAGEMENT =====");
        System.out.println("1. Add New Patient");
        System.out.println("2. View All Patients");
        System.out.println("3. Search Patient by ID");
        System.out.println("4. Update Patient");
        System.out.println("5. Delete Patient");
        System.out.print("Enter choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        switch (choice) {
            case 1:
                addNewPatient();
                break;
            case 2:
                viewAllPatients();
                break;
            case 3:
                searchPatient();
                break;
            case 4:
                updatePatient();
                break;
            case 5:
                deletePatient();
                break;
        }
    }
    
    private static void addNewPatient() {
        System.out.println("\n--- Add New Patient ---");
        
        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        
        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        
        System.out.print("Date of Birth (YYYY-MM-DD): ");
        Date dob = Date.valueOf(scanner.nextLine());
        
        System.out.print("Gender (Male/Female/Other): ");
        String gender = scanner.nextLine();
        
        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Address: ");
        String address = scanner.nextLine();
        
        System.out.print("Blood Group: ");
        String bloodGroup = scanner.nextLine();
        
        System.out.print("Emergency Contact: ");
        String emergencyContact = scanner.nextLine();
        
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setDateOfBirth(dob);
        patient.setGender(gender);
        patient.setPhoneNumber(phone);
        patient.setEmail(email);
        patient.setAddress(address);
        patient.setBloodGroup(bloodGroup);
        patient.setEmergencyContact(emergencyContact);
        
        if (patientDAO.addPatient(patient)) {
            System.out.println("Patient added successfully!");
        } else {
            System.out.println("Failed to add patient.");
        }
    }
    
    private static void viewAllPatients() {
        List<Patient> patients = patientDAO.getAllPatients();
        
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        
        System.out.println("\n--- All Patients ---");
        System.out.println("ID\tName\t\tPhone\t\tEmail");
        System.out.println("--------------------------------------------------");
        
        for (Patient patient : patients) {
            System.out.printf("%d\t%s %s\t%s\t%s\n",
                patient.getPatientId(),
                patient.getFirstName(),
                patient.getLastName(),
                patient.getPhoneNumber(),
                patient.getEmail());
        }
    }
    
    private static void searchPatient() {
        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Patient patient = patientDAO.getPatientById(id);
        
        if (patient != null) {
            System.out.println("\n--- Patient Details ---");
            System.out.println("ID: " + patient.getPatientId());
            System.out.println("Name: " + patient.getFirstName() + " " + patient.getLastName());
            System.out.println("DOB: " + patient.getDateOfBirth());
            System.out.println("Gender: " + patient.getGender());
            System.out.println("Phone: " + patient.getPhoneNumber());
            System.out.println("Email: " + patient.getEmail());
            System.out.println("Address: " + patient.getAddress());
            System.out.println("Blood Group: " + patient.getBloodGroup());
            System.out.println("Emergency Contact: " + patient.getEmergencyContact());
        } else {
            System.out.println("Patient not found!");
        }
    }
    
    private static void updatePatient() {
        System.out.print("Enter Patient ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        Patient patient = patientDAO.getPatientById(id);
        if (patient == null) {
            System.out.println("Patient not found!");
            return;
        }
        
        System.out.println("Current Details:");
        System.out.println(patient);
        
        System.out.print("New Phone Number (press enter to skip): ");
        String phone = scanner.nextLine();
        if (!phone.isEmpty()) patient.setPhoneNumber(phone);
        
        System.out.print("New Email (press enter to skip): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) patient.setEmail(email);
        
        System.out.print("New Address (press enter to skip): ");
        String address = scanner.nextLine();
        if (!address.isEmpty()) patient.setAddress(address);
        
        if (patientDAO.updatePatient(patient)) {
            System.out.println("Patient updated successfully!");
        } else {
            System.out.println("Failed to update patient.");
        }
    }
    
    private static void deletePatient() {
        System.out.print("Enter Patient ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Are you sure? (yes/no): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("yes")) {
            if (patientDAO.deletePatient(id)) {
                System.out.println("Patient deleted successfully!");
            } else {
                System.out.println("Failed to delete patient.");
            }
        }
    }
    
    private static void manageDoctors() {
        System.out.println("\n===== DOCTOR MANAGEMENT =====");
        System.out.println("1. View All Doctors");
        System.out.println("2. Search Doctor by Specialization");
        System.out.println("3. Add Appointment");
        System.out.println("4. Back to Main Menu");
        System.out.print("Enter choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        // Similar implementation for doctors
    }
    
    private static void manageAppointments() {
        System.out.println("\n===== APPOINTMENT MANAGEMENT =====");
        System.out.println("1. Schedule New Appointment");
        System.out.println("2. View Today's Appointments");
        System.out.println("3. Update Appointment Status");
        System.out.println("4. View Patient Appointments");
        System.out.print("Enter choice: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        // Similar implementation for appointments
    }
    
    private static void displayStatistics() {
        System.out.println("\n===== SYSTEM STATISTICS =====");
        
        List<Patient> patients = patientDAO.getAllPatients();
        System.out.println("Total Patients: " + patients.size());
        
        // You can add more statistics here
        System.out.println("Database connected successfully!");
        System.out.println("System is running with MySQL backend");
    }
}