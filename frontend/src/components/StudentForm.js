import React, { useState, useEffect } from 'react';
import './StudentForm.css'; // Import the CSS file

const StudentForm = ({ editingStudent, setEditingStudent, refreshStudents }) => {
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    name: '',
    phone: '',
    departmentId: '',
    year: '',
  });

  useEffect(() => {
    if (editingStudent) {
      setFormData(editingStudent);
    } else {
      setFormData({
        username: '',
        email: '',
        password: '',
        name: '',
        phone: '',
        departmentId: '',
        year: '',
      });
    }
  }, [editingStudent]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const url = editingStudent
      ? `http://localhost:8080/api/students/student/${editingStudent.id}`
      : 'http://localhost:8080/api/students/student';
    const method = editingStudent ? 'PUT' : 'POST';

    try {
      const response = await fetch(url, {
        method,
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('jwt')}`,
        },
        body: JSON.stringify(formData),
      });

      if (!response.ok) {
        throw new Error('Network response was not ok');
      }

      const result = await response.json();
      console.log('Student saved:', result);
      refreshStudents();
      setFormData({
        username: '',
        email: '',
        password: '',
        name: '',
        phone: '',
        departmentId: '',
        year: '',
      });
      setEditingStudent(null);
    } catch (error) {
      console.error('There was an error saving the student!', error);
    }
  };

  return (
    <div className="form-container">
      <h2>{editingStudent ? 'Edit Student' : 'Add Student'}</h2>
      <form onSubmit={handleSubmit} className="student-form">
        {/* User fields */}
        <input
          type="text"
          name="username"
          placeholder="Username"
          value={formData.username}
          onChange={handleChange}
          required
          className="form-input"
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
          required
          className="form-input"
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={formData.password}
          onChange={handleChange}
          required
          className="form-input"
        />
        <input
          type="text"
          name="name"
          placeholder="Name"
          value={formData.name}
          onChange={handleChange}
          required
          className="form-input"
        />
        <input
          type="text"
          name="phone"
          placeholder="Phone"
          value={formData.phone}
          onChange={handleChange}
          required
          className="form-input"
        />
        {/* Student fields */}
        <input
          type="text"
          name="departmentId"
          placeholder="Department ID"
          value={formData.departmentId}
          onChange={handleChange}
          required
          className="form-input"
        />
        <input
          type="text"
          name="year"
          placeholder="Year"
          value={formData.year}
          onChange={handleChange}
          required
          className="form-input"
        />
        <button type="submit" className="form-button">
          {editingStudent ? 'Update Student' : 'Add Student'}
        </button>
      </form>
    </div>
  );
};

export default StudentForm;