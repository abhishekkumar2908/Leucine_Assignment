import React from "react";
import { Routes, Route, Link } from "react-router-dom";
import ViewSubmittedAssignments from "./ViewSubmittedAssignments";
import ViewAllAssignments from "./ViewAllAssignments";
import TeacherDashboard from "./TeacherDashboard";
import AddAssignment from "./AddAssignment";
import "./TeacherDashboardPage.css";

const TeacherDashboardPage = () => {
  return (
    <div className="teacher-dashboard-page">
      <nav className="nav-bar">
        <ul className="nav-links">
          <li>
            <Link to="/teacher-dashboard" className="nav-link">Dashboard</Link>
          </li>
          <li>
            <Link to="/teacher-dashboard/add-assignment" className="nav-link">Add New Assignment</Link>
          </li>
          <li>
            <Link to="/teacher-dashboard/view-submitted-assignments" className="nav-link">View Submitted Assignments</Link>
          </li>
          <li>
            <Link to="/teacher-dashboard/view-all-assignments" className="nav-link">View All Assignments</Link>
          </li>
        </ul>
      </nav>
      <div className="content-container">
        <Routes>
          <Route path="add-assignment" element={<AddAssignment />} />
          <Route path="view-submitted-assignments" element={<ViewSubmittedAssignments />} />
          <Route path="view-all-assignments" element={<ViewAllAssignments />} />
          <Route path="/" element={<TeacherDashboard />} />
        </Routes>
      </div>
    </div>
  );
};

export default TeacherDashboardPage;