import React, { useEffect, useState } from "react";
import axiosInstance from "../../axiosConfig";
import "./TeacherDashboardPage.css";

const ViewSubmittedAssignments = () => {
  const [submissions, setSubmissions] = useState([]);
  const [filters, setFilters] = useState({
    title: "",
    studentName: "",
    className: ""
  });

  useEffect(() => {
    fetchSubmissions();
  }, []);

  const fetchSubmissions = async () => {
    try {
      const response = await axiosInstance.get("/api/submissions");
      setSubmissions(response.data);
    } catch (error) {
      console.error("Error fetching submissions:", error);
    }
  };

  const handleFilterChange = (event) => {
    const { name, value } = event.target;
    setFilters((prevFilters) => ({
      ...prevFilters,
      [name]: value
    }));
  };

  const filteredSubmissions = submissions.filter((submission) =>
    submission.assignmentTitle.includes(filters.title) &&
    submission.studentName.includes(filters.studentName) &&
    (filters.className === "" || submission.className === filters.className)
  );

  return (
    <div className="view-submitted-assignments-container">
      <h2>View Submitted Assignments</h2>
      <div className="filters">
        <input type="text" placeholder="Assignment Title" name="title" value={filters.title} onChange={handleFilterChange} />
        <input type="text" placeholder="Student Name" name="studentName" value={filters.studentName} onChange={handleFilterChange} />
        <select name="className" value={filters.className} onChange={handleFilterChange}>
          <option value="">Select class</option>
          <option value="11th">11th</option>
          <option value="12th">12th</option>
        </select>
      </div>
      <table>
        <thead>
          <tr>
            <th>Assignment Title</th>
            <th>Student Name</th>
            <th>Submission Date</th>
            <th>Class</th>
          </tr>
        </thead>
        <tbody>
          {filteredSubmissions.map((submission) => (
            <tr key={submission.id}>
              <td>{submission.assignmentTitle}</td>
              <td>{submission.studentName}</td>
              <td>{submission.submissionDate}</td>
              <td>{submission.className}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ViewSubmittedAssignments;