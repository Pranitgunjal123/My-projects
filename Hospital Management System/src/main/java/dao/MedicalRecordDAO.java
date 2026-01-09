package dao;

import model.MedicalRecord;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordDAO {
    
    public boolean addMedicalRecord(MedicalRecord record) {
        String sql = "INSERT INTO medical_records (patient_id, doctor_id, visit_date, " +
                    "diagnosis, treatment, prescription, notes, height, weight, " +
                    "blood_pressure, temperature) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, record.getPatientId());
            pstmt.setInt(2, record.getDoctorId());
            pstmt.setDate(3, record.getVisitDate());
            pstmt.setString(4, record.getDiagnosis());
            pstmt.setString(5, record.getTreatment());
            pstmt.setString(6, record.getPrescription());
            pstmt.setString(7, record.getNotes());
            pstmt.setDouble(8, record.getHeight());
            pstmt.setDouble(9, record.getWeight());
            pstmt.setString(10, record.getBloodPressure());
            pstmt.setDouble(11, record.getTemperature());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<MedicalRecord> getMedicalRecordsByPatientId(int patientId) {
        List<MedicalRecord> records = new ArrayList<>();
        String sql = "SELECT * FROM medical_records WHERE patient_id = ? " +
                    "ORDER BY visit_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, patientId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                MedicalRecord record = new MedicalRecord();
                record.setRecordId(rs.getInt("record_id"));
                record.setPatientId(rs.getInt("patient_id"));
                record.setDoctorId(rs.getInt("doctor_id"));
                record.setVisitDate(rs.getDate("visit_date"));
                record.setDiagnosis(rs.getString("diagnosis"));
                record.setTreatment(rs.getString("treatment"));
                record.setPrescription(rs.getString("prescription"));
                record.setNotes(rs.getString("notes"));
                record.setHeight(rs.getDouble("height"));
                record.setWeight(rs.getDouble("weight"));
                record.setBloodPressure(rs.getString("blood_pressure"));
                record.setTemperature(rs.getDouble("temperature"));
                
                records.add(record);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return records;
    }
}