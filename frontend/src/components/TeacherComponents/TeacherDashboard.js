import React, { useEffect, useState } from "react";
import "./TeacherDashboardPage.css";
import axiosInstance from "../../axiosConfig";
import PieChartRecharts from "../Chart";

const TeacherDashboard = () => {
  const [summary, setSummary] = useState({
    totalAssignments: 0,
    totalSubmissions: 0,
    upcomingDeadlines: [],
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
    <div>
      <div className="">
        <h2>Dashboard</h2>
        <div className="summary">
          <div>Total Assignments: {summary.totalAssignments}</div>
          <div>Total Submissions: {summary.totalSubmissions}</div>
          <div>Upcoming Deadlines:</div>
          <ul>
            {summary.upcomingDeadlines.map((deadline) => (
              <li key={deadline.id}>
                {deadline.title} - {deadline.dueDate}
              </li>
            ))}
          </ul>
        </div>
      </div>
      <div className="content-container">
        <PieChartRecharts
          data={[
            { name: "Submitted by Students", value: 60 },
            { name: "Remaining", value: 40 },
          ]}
        />
        <PieChartRecharts
          data={[
            { name: "Submitted by Students", value: 20 },
            { name: "Remaining", value: 80 },
          ]}
        />
      </div>
    </div>
  );
};

export default TeacherDashboard;
