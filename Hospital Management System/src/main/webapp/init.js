/*// Initialize localStorage if empty
function initializeStorage() {
    if (!localStorage.getItem('patients')) {
        // Sample initial data
        const samplePatients = [...]; // Your existing sample data
        localStorage.setItem('patients', JSON.stringify(samplePatients));
    }
    
    if (!localStorage.getItem('doctors')) {
        const sampleDoctors = [...]; // Your existing sample data
        localStorage.setItem('doctors', JSON.stringify(sampleDoctors));
    }
    
    if (!localStorage.getItem('appointments')) {
        const sampleAppointments = [...]; // Your existing sample data
        localStorage.setItem('appointments', JSON.stringify(sampleAppointments));
    }
    
    if (!localStorage.getItem('wards')) {
        const sampleWards = [...]; // Your wards data
        localStorage.setItem('wards', JSON.stringify(sampleWards));
    }
}

// Call this on each page load
document.addEventListener('DOMContentLoaded', function() {
    initializeStorage();
    // Rest of your initialization code
});*/