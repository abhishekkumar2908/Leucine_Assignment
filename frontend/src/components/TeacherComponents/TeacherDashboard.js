import React, { useEffect, useState } from "react";
import "./TeacherDashboardPage.css";
import PieChartRecharts from "../Chart";
import { Axios } from "../../axiosConfig";

const TeacherDashboard = () => {
  const [summaryXI, setSummaryXI] = useState({
    totalAssignments: 5,
    totalSubmissions: 2,
    upcomingDeadlines: [],
  });

  const [summaryXII, setSummaryXII] = useState({
    totalAssignments: 7,
    totalSubmissions: 2,
    upcomingDeadlines: [],
  });

  const token = localStorage.getItem("token");

  useEffect(() => {
    fetchSummary("XI", setSummaryXI);
    fetchSummary("XII", setSummaryXII);
  }, []);

  const fetchSummary = async (className, setSummary) => {
    try {
      const response = await Axios.get(
        `/assignments/summary?className=${className}`
        
      );
      // setSummary(response.data);
    } catch (error) {
      console.error(`Error fetching summary data for Class ${className}:`, error);
    }
  };

  const renderSummary = (summary, className) => (
    <div className="summary">
      <div>Total Assignments for Class {className}: {summary?.totalAssignments}</div>
      <div>Total Submissions for Class {className}: {summary?.totalSubmissions}</div>
      <div>Upcoming Deadlines for Class {className}:</div>
      {summary?.upcomingDeadlines?.length > 0 ? (
        <ul>
          {summary?.upcomingDeadlines?.map((deadline) => (
            <li key={deadline?.id}>
              {deadline?.title} - {deadline?.dueDate}
            </li>
          ))}
        </ul>
      ) : (
        <div>No upcoming deadlines.</div>
      )}
    </div>
  );

  return (
    <div>
      <h2>Dashboard</h2>
      {renderSummary(summaryXI, "XI")}
      {renderSummary(summaryXII, "XII")}
      <div className="content-container">
        <PieChartRecharts
          data={[
            { name: "Submitted by Students", value: summaryXI?.totalSubmissions },
            { name: "Remaining", value: summaryXI?.totalAssignments - summaryXI?.totalSubmissions },
          ]}
        />
        <PieChartRecharts
          data={[
            { name: "Submitted by Students", value: summaryXII?.totalSubmissions },
            { name: "Remaining", value: summaryXII?.totalAssignments - summaryXII?.totalSubmissions },
          ]}
        />
      </div>
    </div>
  );
};

export default TeacherDashboard;