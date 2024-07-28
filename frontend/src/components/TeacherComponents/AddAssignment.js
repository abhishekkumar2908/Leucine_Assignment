import React, { useState } from "react";
import "./AddAssignment.css";
import { Axios } from "../../axiosConfig";

const AddAssignment = () => {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [dueDate, setDueDate] = useState("");
  const [attachment, setAttachment] = useState(null);
  const [className, setClassName] = useState("");
  const [successMessage, setSuccessMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState("");
  const token = localStorage.getItem("token"); // Get the token from localStorage

  const handleFileChange = (event) => {
    setAttachment(event.target.files[0]);
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    const formData = new FormData();
    formData.append("title", title);
    formData.append("description", description);
    formData.append("dueDate", dueDate + "T23:59:59"); // Append time to dueDate
    if (attachment) {
      formData.append("file", attachment);
    }
    formData.append("className", className);

    try {
      const response = await Axios.post("/assignments", formData, {
        headers: {
          Authorization: `Bearer ${token}`, // Include the token in the headers
        },
      });
      setSuccessMessage("Assignment created successfully!");
      setErrorMessage("");
    } catch (error) {
      if (error.response && error.response.data) {
        // Extract validation error message
        const validationError = error.response.data.errors.find(err => err.field === "dueDate");
        if (validationError) {
          setErrorMessage(validationError.defaultMessage);
        } else {
          setErrorMessage("An error occurred. Please try again.");
        }
      } else {
        setErrorMessage("An error occurred. Please try again.");
      }
      setSuccessMessage("");
    }
  };

  const today = new Date().toISOString().split("T")[0]; // Get today's date in YYYY-MM-DD format

  return (
    <div className="add-assignment-container">
      <h2>Add New Assignment</h2>
      {successMessage && <div className="success-message">{successMessage}</div>}
      {errorMessage && <div className="error-message">{errorMessage}</div>}
      <form className="add-assignment-form" onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Title</label>
          <input type="text" value={title} onChange={(e) => setTitle(e.target.value)} required />
        </div>
        <div className="form-group">
          <label>Description</label>
          <textarea value={description} onChange={(e) => setDescription(e.target.value)} required />
        </div>
        <div className="form-group">
          <label>Due Date</label>
          <input type="date" value={dueDate} onChange={(e) => setDueDate(e.target.value)} min={today} required />
        </div>
        <div className="form-group">
          <label>Attachments</label>
          <input type="file" onChange={handleFileChange} />
        </div>
        <div className="form-group">
          <label>Class</label>
          <select value={className} onChange={(e) => setClassName(e.target.value)} required>
            <option value="">Select class</option>
            <option value="XI">11th</option>
            <option value="XII">12th</option>
          </select>
        </div>
        <button className = "submit-button" type="submit">Create Assignment</button>
      </form>
    </div>
  );
};

export default AddAssignment;