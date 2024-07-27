import React, { useState, useEffect } from "react";
import "./AdminDashboard.css";
import Header from "./Header.js";
import StudentForm from "./StudentForm";
import FacultyForm from "./FacultyForm";
import Modal from "./Modal";
import useFetch from "../hooks/useFetch";

const AdminDashboard = () => {

  const AdminButtons = () => (
    <div className="admin-buttons">
      <button onClick={() => (window.location.href = "/view-students")}>
        View All Students
      </button>
      <button onClick={() => (window.location.href = "/view-faculties")}>
        View All Faculties
      </button>
      <button onClick={() => (window.location.href = "/add-student")}>
        Add Student
      </button>
      <button onClick={() => (window.location.href = "/add-faculty")}>
        Add Faculty
      </button>
    </div>
  );

  return (
    <div className="admin-dashboard-container">
      <Header />
      <h1>Welcome to Admin Dashboard</h1>
      <AdminButtons />             
    </div>
  );
};

export default AdminDashboard;
