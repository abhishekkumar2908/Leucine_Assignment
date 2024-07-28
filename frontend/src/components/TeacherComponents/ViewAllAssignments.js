import React, { useEffect, useState } from "react";
import "./TeacherDashboardPage.css";
import { Axios } from "../../axiosConfig";

const ViewAllAssignments = () => {
  const [assignments, setAssignments] = useState([]);

  useEffect(() => {
    fetchAssignments();
  }, []);

  const fetchAssignments = async () => {
    try {
      const response = await Axios.get("/assignments");
      setAssignments(response.data);
    } catch (error) {
      console.error("Error fetching assignments:", error);
    }
  };

  const handleDelete = async (id) => {
    try {
      await Axios.delete(`/assignments/${id}`);
      setAssignments(assignments.filter((assignment) => assignment.id !== id));
    } catch (error) {
      console.error("Error deleting assignment:", error);
    }
  };

  return (
    <div className="view-all-assignments-container">
      <h2>View All Assignments</h2>
      <table>
        <thead>
          <tr>
            <th>Assignment Title</th>
            <th>Class</th>
            <th>Due Date</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {assignments.map((assignment) => (
            <tr key={assignment.id}>
              <td>{assignment.title}</td>
              <td>{assignment.className}</td>
              <td>{assignment.dueDate}</td>
              <td>
                <button onClick={() => handleDelete(assignment.id)}>Delete</button>
                {/* Implement Edit functionality */}
                <button>Edit</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default ViewAllAssignments;