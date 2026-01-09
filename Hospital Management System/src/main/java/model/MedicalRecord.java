package model;

import java.sql.Date;

public class MedicalRecord {
    private int recordId;
    private int patientId;
    private int doctorId;
    private Date visitDate;
    private String diagnosis;
    private String treatment;
    private String prescription;
    private String notes;
    private double height;
    private double weight;
    private String bloodPressure;
    private double temperature;
    
    // Constructors
    public MedicalRecord() {}
    
    public MedicalRecord(int recordId, int patientId, int doctorId, Date visitDate, 
                        String diagnosis, String treatment, String prescription) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.visitDate = visitDate;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.prescription = prescription;
    }
    
    // Getters and Setters
    public int getRecordId() { return recordId; }
    public void setRecordId(int recordId) { this.recordId = recordId; }
    
    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }
    
    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }
    
    public Date getVisitDate() { return visitDate; }
    public void setVisitDate(Date visitDate) { this.visitDate = visitDate; }
    
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    
    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
    
    public String getPrescription() { return prescription; }
    public void setPrescription(String prescription) { this.prescription = prescription; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
    
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }
    
    public String getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(String bloodPressure) { this.bloodPressure = bloodPressure; }
    
    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }
    
    @Override
    public String toString() {
        return "MedicalRecord [ID=" + recordId + ", PatientID=" + patientId + 
               ", VisitDate=" + visitDate + ", Diagnosis=" + diagnosis + "]";
    }
}