import { store, savePatients } from './js/dataStore.js';

/* ============================
   1️⃣ INITIAL SAMPLE DATA
============================ */
const initialPatientsData = [
  {
    id: "PAT001234",
    firstName: "Rohan",
    lastName: "Sharma",
    age: 45,
    gender: "Male",
    bloodGroup: "A+",
    mobile: "+91 9876543210",
    disease: "Hypertension",
    assignedDoctor: "Dr. Rajesh Kumar",
    department: "Cardiology",
    status: "Admitted",
    admitDate: "2024-03-10",
    avatar: "https://randomuser.me/api/portraits/men/22.jpg"
  }
];

/* ============================
   2️⃣ SEED DATA ONLY ONCE
============================ */
if (store.patients.length === 0) {
  store.patients = initialPatientsData;
  savePatients();
}

/* ============================
   3️⃣ ALWAYS USE DYNAMIC DATA
============================ */
let patientsData = store.patients;

/* ============================
   4️⃣ TABLE RENDER
============================ */
const tableBody = document.getElementById("patientsTableBody");

function populatePatientsTable() {
  tableBody.innerHTML = "";

  if (patientsData.length === 0) {
    tableBody.innerHTML =
      `<tr><td colspan="8" style="text-align:center">No patients found</td></tr>`;
    return;
  }

  patientsData.forEach(patient => {
    const row = document.createElement("tr");
    row.innerHTML = `
      <td></td>
      <td>
        <strong>${patient.firstName} ${patient.lastName}</strong><br>
        <small>${patient.id}</small>
      </td>
      <td>${patient.age} / ${patient.gender}</td>
      <td>${patient.assignedDoctor}</td>
      <td>${patient.department}</td>
      <td>${patient.status}</td>
      <td>${patient.admitDate}</td>
      <td>—</td>
    `;
    tableBody.appendChild(row);
  });
}

/* ============================
   5️⃣ RUN AFTER PAGE LOAD
============================ */
document.addEventListener("DOMContentLoaded", populatePatientsTable);
