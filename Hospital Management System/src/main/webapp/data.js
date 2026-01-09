// Initialize sample data for the application
const initializeSampleData = () => {
    // Only initialize if localStorage is empty
    if (!localStorage.getItem('patients')) {
        const samplePatients = [
            {
                id: "PAT001234",
                firstName: "Rohan",
                lastName: "Sharma",
                age: 45,
                gender: "Male",
                bloodGroup: "A+",
                mobile: "+91 9876543210",
                email: "rohan.sharma@email.com",
                disease: "Hypertension, Diabetes Type 2",
                assignedDoctor: "Dr. Rajesh Kumar",
                doctorId: "DOC001",
                department: "Cardiology",
                status: "Admitted",
                admitDate: "2024-03-10",
                dischargeDate: null,
                roomNo: "ICU-102",
                emergencyContact: "Mrs. Sharma (+91 9876543211)",
                medicalHistory: "Hypertension for 5 years",
                allergies: "None",
                avatar: "https://randomuser.me/api/portraits/men/22.jpg",
                createdAt: "2024-03-10T10:30:00Z"
            },
            // Add more sample patients...
        ];
        localStorage.setItem('patients', JSON.stringify(samplePatients));
    }
    
    if (!localStorage.getItem('doctors')) {
        const sampleDoctors = [
            {
                id: "DOC001",
                name: "Dr. Rajesh Kumar",
                avatar: "https://randomuser.me/api/portraits/men/32.jpg",
                department: "Cardiology",
                specialization: "Cardiologist",
                experience: 12,
                email: "rajesh.k@citycare.com",
                phone: "+91 9876543210",
                status: "Active",
                joinDate: "2012-05-15"
            },
            // Add more sample doctors...
        ];
        localStorage.setItem('doctors', JSON.stringify(sampleDoctors));
    }
    
    if (!localStorage.getItem('appointments')) {
        const sampleAppointments = [
            {
                id: "APT001",
                patientId: "PAT001234",
                doctorId: "DOC001",
                date: "2024-03-20",
                time: "10:00 AM",
                datetime: "2024-03-20T10:00:00",
                type: "followup",
                status: "confirmed",
                reason: "Hypertension follow-up",
                notes: "Blood pressure monitoring",
                emergency: false,
                createdAt: "2024-03-18T09:30:00Z"
            },
            // Add more sample appointments...
        ];
        localStorage.setItem('appointments', JSON.stringify(sampleAppointments));
    }
    
    if (!localStorage.getItem('wards')) {
        const sampleWards = [
            {
                id: "W001",
                name: "ICU",
                totalBeds: 20,
                occupiedBeds: 15,
                availableBeds: 5,
                floor: "3rd Floor",
                inCharge: "Dr. Mehta",
                department: "Critical Care"
            },
            // Add more sample wards...
        ];
        localStorage.setItem('wards', JSON.stringify(sampleWards));
    }
};/**
 * 
 */