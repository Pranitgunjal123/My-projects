// js/dataStore.js

export const store = {
  doctors: JSON.parse(localStorage.getItem('doctors')) || [],
  patients: JSON.parse(localStorage.getItem('patients')) || [],
  appointments: JSON.parse(localStorage.getItem('appointments')) || []
};

/* =========================
   SAVE FUNCTIONS
========================= */
export function saveDoctors() {
  localStorage.setItem('doctors', JSON.stringify(store.doctors));
}

export function savePatients() {
  localStorage.setItem('patients', JSON.stringify(store.patients));
}

export function saveAppointments() {
  localStorage.setItem('appointments', JSON.stringify(store.appointments));
}
