import React, { useState } from "react";
import "./TeacherDashboardPage.css";
import axiosInstance from "../../axiosConfig";
const AddAssignment = () => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [dueDate, setDueDate] = useState("");
  const [attachment, setAttachment] = useState(null);
  const [className, setClassName] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  const handleFileChange = (event) => {
    setAttachment(event.target.files[0]);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    const formData = new FormData();
    formData.append("title", title);
    formData.append("description", description);
    formData.append("dueDate", dueDate);
    if (attachment) {
      formData.append("file", attachment);
    }
    formData.append("className", className);

    try {
      const response = await axiosInstance.post("/api/assignments", formData);
      setSuccessMessage("Assignment created successfully!");
      setErrorMessage("");
    } catch (error) {
      setSuccessMessage("");
      setErrorMessage("An error occurred. Please try again.");
    }
  };

  return (
    <div className="add-assignment-container">
      <h2>Add New Assignment</h2>
      {successMessage && <div className="success-message">{successMessage}</div>}
      {errorMessage && <div className="error-message">{errorMessage}</div>}
      <form onSubmit={handleSubmit}>
        <div>
          <label>Title</label>
          <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} required />
        </div>
        <div>
          <label>Description</label>
          <textarea value={description} onChange={(e) => setDescription(e.target.value)} required />
        </div>
        <div>
          <label>Due Date</label>
          <input type="date" value={dueDate} onChange={(e) => setDueDate(e.target.value)} required />
        </div>
        <div>
          <label>Attachments</label>
          <input type="file" onChange={handleFileChange} />
        </div>
        <div>
          <label>Class</label>
          <select value={className} onChange={(e) => setClassName(e.target.value)} required>
            <option value="">Select class</option>
            <option value="11th">11th</option>
            <option value="12th">12th</option>
          </select>
        </div>
        <button type="submit">Create Assignment</button>
      </form>
    </div>
  );
};

export default AddAssignment;