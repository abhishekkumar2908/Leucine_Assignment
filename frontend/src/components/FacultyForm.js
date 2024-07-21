import React, { useState, useEffect } from 'react';

const FacultyForm = ({ editingFaculty, setEditingFaculty, refreshFaculties }) => {
  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    
    name: '',
    phone: '',
    departmentId: '',
    officeHours: '',
  });

  useEffect(() => {
    if (editingFaculty) {
      setFormData({
        ...editingFaculty.user, // Assuming editingFaculty has a nested user object
        departmentId: editingFaculty.departmentId,
        officeHours: editingFaculty.officeHours,
      });
    }
  }, [editingFaculty]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const url = editingFaculty ? `api/admin/faculty/${editingFaculty.id}` : 'api/admin/faculty';
    const method = editingFaculty ? 'PUT' : 'POST';

    await fetch(url, {
      method,
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${localStorage.getItem('jwt')}`,
      },
      body: JSON.stringify(formData),
    });

    refreshFaculties();
    setEditingFaculty(null);
    setFormData({
      username: '',
      email: '',
      password: '',
     
      name: '',
      phone: '',
      departmentId: '',
      officeHours: '',
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
      {/* Faculty fields */}
      <input type="text" name="departmentId" placeholder="Department ID" value={formData.departmentId} onChange={handleChange} required />
      <input type="text" name="officeHours" placeholder="Office Hours" value={formData.officeHours} onChange={handleChange} required />
      <button type="submit">{editingFaculty ? 'Update Faculty' : 'Add Faculty'}</button>
    </form>
  );
};

export default FacultyForm;