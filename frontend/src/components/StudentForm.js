import React, { useState, useEffect } from 'react';

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



  

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const url = 'http://localhost:8080/api/students/student';
    const method = 'POST';

    await fetch(url, {
      method,
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('jwt')}`,
      },
      body: JSON.stringify(formData),
    });

   
    setFormData({
      username: '',
      email: '',
      password: '',
      
      name: '',
      phone: '',
      departmentId: '',
      year: '',
    });
  };

  return (
    <form onSubmit={handleSubmit}>
      {/* User fields */}
      <input type="text" name="username" placeholder="Username" value={formData.username} onChange={handleChange} required />
      <input type="email" name="email" placeholder="Email" value={formData.email} onChange={handleChange} required />
      <input type="password" name="password" placeholder="Password" value={formData.password} onChange={handleChange} required />
      <input type="text" name="name" placeholder="Name" value={formData.name} onChange={handleChange} required />
      <input type="text" name="phone" placeholder="Phone" value={formData.phone} onChange={handleChange} required />
      {/* Student fields */}
      <input type="text" name="departmentId" placeholder="Department ID" value={formData.departmentId} onChange={handleChange} required />
      <input type="text" name="year" placeholder="Year" value={formData.year} onChange={handleChange} required />
      <button type="submit">{editingStudent ? 'Update Student' : 'Add Student'}</button>
    </form>
  );
};

export default StudentForm;