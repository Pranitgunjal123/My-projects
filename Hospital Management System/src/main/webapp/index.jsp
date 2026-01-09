<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hospital Management System</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background: #f5f5f5; }
        .container { max-width: 1200px; margin: 0 auto; }
        .header { background: #007bff; color: white; padding: 20px; border-radius: 5px; margin-bottom: 30px; }
        .card { background: white; padding: 20px; border-radius: 5px; margin: 10px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
        .card-container { display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 20px; }
        .btn { display: inline-block; padding: 10px 20px; background: #007bff; color: white; text-decoration: none; border-radius: 4px; margin: 5px; }
        .btn:hover { background: #0056b3; }
        .btn-success { background: #28a745; }
        .btn-warning { background: #ffc107; }
        .btn-info { background: #17a2b8; }
        .stat-card { text-align: center; padding: 30px; }
        .stat-number { font-size: 2.5em; font-weight: bold; color: #007bff; }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>🏥 Hospital Management System</h1>
            <p>Java Web Application with MySQL Database</p>
        </div>
        
        <div class="card">
            <h2>Welcome to Hospital Management System</h2>
            <p>This is a complete hospital management system built with Java Servlets, JSP, and MySQL database.</p>
        </div>
        
        <div class="card-container">
            <div class="card stat-card">
                <h3>Patient Management</h3>
                <p>Register, view, and manage patient records</p>
                <a href="patient-form.jsp" class="btn btn-success">Register Patient</a>
                <a href="PatientServlet?action=list" class="btn">View Patients</a>
            </div>
            
            <div class="card stat-card">
                <h3>Doctor Management</h3>
                <p>Add and manage doctor profiles</p>
                <a href="doctor-form.jsp" class="btn btn-warning">Add Doctor</a>
                <a href="DoctorServlet?action=list" class="btn">View Doctors</a>
            </div>
            
            <div class="card stat-card">
                <h3>Appointments</h3>
                <p>Schedule and manage appointments</p>
                <a href="appointment-form.jsp" class="btn btn-info">Schedule Appointment</a>
                <a href="AppointmentServlet?action=list" class="btn">View Appointments</a>
            </div>
        </div>
        
        <div class="card">
            <h3>System Features:</h3>
            <ul>
                <li>📋 Patient Registration and Management</li>
                <li>👨‍⚕️ Doctor Profile Management</li>
                <li>📅 Appointment Scheduling</li>
                <li>💊 Medical Records</li>
                <li>💳 Billing System</li>
                <li>📊 Reports and Analytics</li>
                <li>🔐 Secure Database Storage</li>
            </ul>
        </div>
        
        <div class="card">
            <h3>Technology Stack:</h3>
            <p><strong>Backend:</strong> Java Servlets, JDBC</p>
            <p><strong>Frontend:</strong> JSP, HTML5, CSS3</p>
            <p><strong>Database:</strong> MySQL 8.0</p>
            <p><strong>Server:</strong> Apache Tomcat 9.0</p>
        </div>
    </div>
</body>
</html>