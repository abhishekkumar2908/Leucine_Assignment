import React, { useState, useEffect } from "react";
import "./AdminDashboard.css"; // Import CSS for styling if needed

const AdminDashboard = () => {
  const [students, setStudents] = useState([]);
  const [faculties, setFaculties] = useState([]);
  const [newStudent, setNewStudent] = useState({
    name: "",
    departmentId: "",
    year: "",
  });
  const [newFaculty, setNewFaculty] = useState({
    name: "",
    departmentId: "",
    officeHours: "",
  });
  const [editingStudent, setEditingStudent] = useState(null);
  const [editingFaculty, setEditingFaculty] = useState(null);

  // Retrieve JWT token from localStorage
  const jwtToken = localStorage.getItem("jwt");

  useEffect(() => {
    // Fetch initial data for students and faculties
    fetchStudents();
    fetchFaculties();
  }, []);

  const fetchStudents = async () => {
    const response = await fetch("http://localhost:8080/api/admin/students", {
      headers: {
        "Authorization": `Bearer ${jwtToken}`,
      },
    });
    const data = await response.json();
    setStudents(data);
  };

  const fetchFaculties = async () => {
    const response = await fetch("http://localhost:8080/api/admin/faculties", {
      headers: {
        "Authorization": `Bearer ${jwtToken}`,
      },
    });
    const data = await response.json();
    setFaculties(data);
  };

  const handleAddStudent = async () => {
    await fetch("http://localhost:8080/api/admin/student", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${jwtToken}`,
      },
      body: JSON.stringify(newStudent),
    });
    setNewStudent({ name: "", departmentId: "", year: "" });
    fetchStudents(); // Refresh the list
  };

  const handleUpdateStudent = async () => {
    await fetch(`http://localhost:8080/api/admin/student/${editingStudent.id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${jwtToken}`,
      },
      body: JSON.stringify(editingStudent),
    });
    setEditingStudent(null);
    fetchStudents(); // Refresh the list
  };

  const handleDeleteStudent = async (id) => {
    await fetch(`http://localhost:8080/api/admin/student/${id}`, {
      method: "DELETE",
      headers: {
        "Authorization": `Bearer ${jwtToken}`,
      },
    });
    fetchStudents(); // Refresh the list
  };

  const handleAddFaculty = async () => {
    await fetch("http://localhost:8080/api/admin/faculty", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${jwtToken}`,
      },
      body: JSON.stringify(newFaculty),
    });
    setNewFaculty({ name: "", departmentId: "", officeHours: "" });
    fetchFaculties(); // Refresh the list
  };

  const handleUpdateFaculty = async () => {
    await fetch(`http://localhost:8080/api/admin/faculty/${editingFaculty.id}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${jwtToken}`,
      },
      body: JSON.stringify(editingFaculty),
    });
    setEditingFaculty(null);
    fetchFaculties(); // Refresh the list
  };

  const handleDeleteFaculty = async (id) => {
    await fetch(`http://localhost:8080/api/admin/faculty/${id}`, {
      method: "DELETE",
      headers: {
        "Authorization": `Bearer ${jwtToken}`,
      },
    });
    fetchFaculties(); // Refresh the list
  };

  return (
    <div className="admin-dashboard-container">
      <h1>Welcome to Admin Dashboard</h1>
      <div className="section">
        <h2>Manage Students</h2>
        <form
          onSubmit={(e) => {
            e.preventDefault();
            if (editingStudent) {
              handleUpdateStudent();
            } else {
              handleAddStudent();
            }
          }}
        >
          <input
            type="text"
            placeholder="Name"
            value={editingStudent ? editingStudent.name : newStudent.name}
            onChange={(e) =>
              editingStudent
                ? setEditingStudent({ ...editingStudent, name: e.target.value })
                : setNewStudent({ ...newStudent, name: e.target.value })
            }
            required
          />
          <input
            type="text"
            placeholder="Department ID"
            value={
              editingStudent
                ? editingStudent.departmentId
                : newStudent.departmentId
            }
            onChange={(e) =>
              editingStudent
                ? setEditingStudent({
                    ...editingStudent,
                    departmentId: e.target.value,
                  })
                : setNewStudent({ ...newStudent, departmentId: e.target.value })
            }
            required
          />
          <input
            type="text"
            placeholder="Year"
            value={editingStudent ? editingStudent.year : newStudent.year}
            onChange={(e) =>
              editingStudent
                ? setEditingStudent({ ...editingStudent, year: e.target.value })
                : setNewStudent({ ...newStudent, year: e.target.value })
            }
            required
          />
          <button type="submit">
            {editingStudent ? "Update Student" : "Add Student"}
          </button>
        </form>
        <ul>
          {students.map((student) => (
            <li key={student.id}>
              {student.name} - {student.departmentId} - {student.year}
              <button onClick={() => setEditingStudent(student)}>Edit</button>
              <button onClick={() => handleDeleteStudent(student.id)}>
                Delete
              </button>
            </li>
          ))}
        </ul>
      </div>

      <div className="section">
        <h2>Manage Faculty</h2>
        <form
          onSubmit={(e) => {
            e.preventDefault();
            if (editingFaculty) {
              handleUpdateFaculty();
            } else {
              handleAddFaculty();
            }
          }}
        >
          <input
            type="text"
            placeholder="Name"
            value={editingFaculty ? editingFaculty.name : newFaculty.name}
            onChange={(e) =>
              editingFaculty
                ? setEditingFaculty({ ...editingFaculty, name: e.target.value })
                : setNewFaculty({ ...newFaculty, name: e.target.value })
            }
            required
          />
          <input
            type="text"
            placeholder="Department ID"
            value={
              editingFaculty
                ? editingFaculty.departmentId
                : newFaculty.departmentId
            }
            onChange={(e) =>
              editingFaculty
                ? setEditingFaculty({
                    ...editingFaculty,
                    departmentId: e.target.value,
                  })
                : setNewFaculty({ ...newFaculty, departmentId: e.target.value })
            }
            required
          />
          <input
            type="text"
            placeholder="Office Hours"
            value={
              editingFaculty
                ? editingFaculty.officeHours
                : newFaculty.officeHours
            }
            onChange={(e) =>
              editingFaculty
                ? setEditingFaculty({
                    ...editingFaculty,
                    officeHours: e.target.value,
                  })
                : setNewFaculty({ ...newFaculty, officeHours: e.target.value })
            }
            required
          />
          <button type="submit">
            {editingFaculty ? "Update Faculty" : "Add Faculty"}
          </button>
        </form>
        <ul>
          {faculties.map((faculty) => (
            <li key={faculty.id}>
              {faculty.name} - {faculty.departmentId} - {faculty.officeHours}
              <button onClick={() => setEditingFaculty(faculty)}>Edit</button>
              <button onClick={() => handleDeleteFaculty(faculty.id)}>
                Delete
              </button>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default AdminDashboard;