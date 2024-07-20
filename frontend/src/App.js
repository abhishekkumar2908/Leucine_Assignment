// src/App.js
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./components/Login";
import StudentDashboard from "./components/StudentDashboard";
import FacultyDashboard from "./components/FacultyDashboard";
import AdminDashboard from "./components/AdminDashboard.js";
import StudentForm from "./components/StudentForm.js";
import FacultyForm from "./components/FacultyForm.js";

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/student-dashboard" element={<StudentDashboard />} />
          <Route path="/faculty-dashboard" element={<FacultyDashboard />} />
          <Route path="/admin-dashboard" element={<AdminDashboard />} />
          <Route path="/add-student" element={<StudentForm />} />
          <Route path="/add-faculty" element={<FacultyForm />} />
          

        </Routes>
      </div>
    </Router>
  );
}

export default App;