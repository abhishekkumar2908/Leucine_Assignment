import React, { useEffect, useState } from "react";
import "./ViewAllAssignments.css";
import { Axios } from "../../axiosConfig";

const ViewAllAssignments = () => {
  const [assignments, setAssignments] = useState([]);
  const [selectedClass, setSelectedClass] = useState("XI");

  useEffect(() => {
    fetchAssignments();
  }, [selectedClass]);

  const fetchAssignments = async () => {
    try {
      const response = await Axios.get(`/assignments/${selectedClass}`);
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
      <div className="classSelection">
        <button onClick={() => setSelectedClass("XI")}>Class XI</button>
        <button onClick={() => setSelectedClass("XII")}>Class XII</button>
      </div>
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
        {assignments && assignments.length > 0 ? (
            assignments.map((assignment) => (
              <tr key={assignment.id}>
                <td>{assignment.title}</td>
                <td>{assignment.className}</td>
                <td>{new Date(assignment.dueDate).toLocaleDateString()}</td>
                <td>
                  <button onClick={() => handleDelete(assignment.id)}>Delete</button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4">No assignments found</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ViewAllAssignments;