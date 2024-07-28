import React, { useEffect, useState } from "react";
import "./TeacherDashboardPage.css";
import axiosInstance from "../../axiosConfig";

const TeacherDashboard = () => {
  const [summary, setSummary] = useState({
    totalAssignments: 0,
    totalSubmissions: 0,
    upcomingDeadlines: []
  });

  useEffect(() => {
    fetchSummary();
  }, []);

  const fetchSummary = async () => {
    try {
      const response = await axiosInstance.get("/api/dashboard/summary");
      setSummary(response.data);
    } catch (error) {
      console.error("Error fetching summary data:", error);
    }
  };

  return (
    <div className="teacher-dashboard-container">
      <h2>Dashboard</h2>
      <div className="summary">
        <div>Total Assignments: {summary.totalAssignments}</div>
        <div>Total Submissions: {summary.totalSubmissions}</div>
        <div>Upcoming Deadlines:</div>
        <ul>
          {summary.upcomingDeadlines.map((deadline) => (
            <li key={deadline.id}>{deadline.title} - {deadline.dueDate}</li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default TeacherDashboard;