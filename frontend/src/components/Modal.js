// src/components/Modal.js
import React, { useState, useEffect } from "react";

const Modal = ({ item, onClose, refreshData }) => {
  const isStudent = item.year !== undefined;

  const [formData, setFormData] = useState({
    username: '',
    email: '',
    password: '',
    name: item.name || '',
    phone: '',
    departmentId: item.departmentId || '',
    year: isStudent ? item.year : '',
    officeHours: !isStudent ? item.officeHours : '',
  });

  useEffect(() => {
    setFormData({
      username: '',
      email: '',
      password: '',
      name: item.name || '',
      phone: '',
      departmentId: item.departmentId || '',
      year: isStudent ? item.year : '',
      officeHours: !isStudent ? item.officeHours : '',
    });
  }, [item, isStudent]);

  const handleSave = async () => {
    const jwtToken = localStorage.getItem("jwt");
    const type = isStudent ? "student" : "faculty";
    await fetch(`http://localhost:8080/api/admin/${type}/${item.id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${jwtToken}`,
      },
      body: JSON.stringify(formData),
    });
    refreshData();
    onClose();
  };

  return (
    <div className="modal">
      <div className="modal-content">
        <h2>Edit {isStudent ? "Student" : "Faculty"}</h2>
        <form>
          <input
            type="text"
            placeholder="Name"
            value={formData.name}
            onChange={(e) => setFormData({ ...formData, name: e.target.value })}
            required
          />
          <input
            type="text"
            placeholder="Department ID"
            value={formData.departmentId}
            onChange={(e) =>
              setFormData({ ...formData, departmentId: e.target.value })
            }
            required
          />
          {isStudent ? (
            <input
              type="text"
              placeholder="Year"
              value={formData.year}
              onChange={(e) =>
                setFormData({ ...formData, year: e.target.value })
              }
              required
            />
          ) : (
            <input
              type="text"
              placeholder="Office Hours"
              value={formData.officeHours}
              onChange={(e) =>
                setFormData({ ...formData, officeHours: e.target.value })
              }
              required
            />
          )}
        </form>
        <button onClick={handleSave}>Save</button>
        <button onClick={onClose}>Cancel</button>
      </div>
    </div>
  );
};

export default Modal;