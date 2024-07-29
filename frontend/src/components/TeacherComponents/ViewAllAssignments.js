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
      console.log(`Fetching assignments for class: ${selectedClass}`);
      const response = await Axios.get(`/assignments/${selectedClass}`);
      console.log('Response:', response);
      const data = response; // Directly use response.data
      console.log('Data:', data);
      if (Array.isArray(data)) {
        setAssignments(data);
      } else {
        console.error("Data is not an array:", data);
        setAssignments([]);
      }
    } catch (error) {
      console.error("Error fetching assignments:", error);
      setAssignments([]); // Set assignments to an empty array on error
    }
  };

  const handleUpdate = (id) => {
    console.log(`Update assignment with id: ${id}`);
    // Add your update logic here
  };

  const handleDelete = (id) => {
    console.log(`Delete assignment with id: ${id}`);
    // Add your delete logic here
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
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          {assignments.length > 0 ? (
            assignments.map((assignment) => (
              <tr key={assignment.id}>
                <td>{assignment.title}</td>
                <td>{assignment.className}</td>
                <td>{new Date(assignment.dueDate).toLocaleDateString()}</td>
                <td>
                  <button onClick={() => handleUpdate(assignment.id)}>Update</button>
                  <button onClick={() => handleDelete(assignment.id)}>Delete</button>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan="4">No assignments found.</td>
            </tr>
          )}
        </tbody>
      </table>
    </div>
  );
};

export default ViewAllAssignments;