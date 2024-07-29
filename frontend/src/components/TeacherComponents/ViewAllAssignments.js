import React, { useEffect, useState } from "react";
import "./ViewAllAssignments.css";
import { Axios } from "../../axiosConfig";

const ViewAllAssignments = () => {
  const [assignments, setAssignments] = useState([]);
  const [selectedClass, setSelectedClass] = useState("XI");
  const [isUpdateFormVisible, setIsUpdateFormVisible] = useState(false);
  const [currentAssignment, setCurrentAssignment] = useState(null);
  const [formData, setFormData] = useState({
    title: "",
    description: "",
    dueDate: "",
    className: "",
    file: ""
  });

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

  const handleUpdate = (assignment) => {
    console.log(`Update assignment with id: ${assignment.id}`);
    setCurrentAssignment(assignment);
    setFormData({
      title: assignment.title,
      description: assignment.description,
      dueDate: new Date(assignment.dueDate).toISOString().slice(0, 16),
      className: assignment.className,
      file: assignment.file
    });
    setIsUpdateFormVisible(true);
  };

  const handleFormChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value
    }));
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await Axios.put(`/assignments/${currentAssignment.id}`, formData);
      console.log('Update Response:', response);
      setIsUpdateFormVisible(false);
      fetchAssignments(); // Refresh the assignments list
    } catch (error) {
      console.error("Error updating assignment:", error);
    }
  };

  const handleDelete = (id) => {
    try {
      Axios.delete(`/assignments/${id}`);
      setAssignments(assignments.filter((assignment) => assignment.id !== id));
    } catch (error) {
      console.error("Error deleting assignment:", error);
    }
  };

  const getCurrentDateTime = () => {
    const now = new Date();
    return now.toISOString().slice(0, 16);
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
                  <button onClick={() => handleUpdate(assignment)}>Update</button>
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

      {isUpdateFormVisible && (
        <div className="update-form">
          <h3>Update Assignment</h3>
          <form onSubmit={handleFormSubmit}>
            <label>
              Title:
              <input
                type="text"
                name="title"
                value={formData.title}
                onChange={handleFormChange}
              />
            </label>
            <label>
              Description:
              <textarea
                name="description"
                value={formData.description}
                onChange={handleFormChange}
              />
            </label>
            <label>
              Due Date:
              <input
                type="datetime-local"
                name="dueDate"
                value={formData.dueDate}
                onChange={handleFormChange}
                min={getCurrentDateTime()}
              />
            </label>
            <label>
              Class Name:
              <input
                type="text"
                name="className"
                value={formData.className}
                onChange={handleFormChange}
              />
            </label>
            <label>
              File:
              <input
                type="text"
                name="file"
                value={formData.file}
                onChange={handleFormChange}
              />
            </label>
            <button type="submit">Submit</button>
            <button type="button" onClick={() => setIsUpdateFormVisible(false)}>Cancel</button>
          </form>
        </div>
      )}
    </div>
  );
};

export default ViewAllAssignments;