import React, { useState, useEffect } from "react";
import "./AdminDashboard.css";
import StudentForm from "./StudentForm";
import FacultyForm from "./FacultyForm";
import Modal from "./Modal";
import useFetch from "../hooks/useFetch";

const AdminDashboard = () => {
//   const [students, fetchStudents] = useFetch("http://localhost:8080/api/admin/students");
//   const [faculties, fetchFaculties] = useFetch("http://localhost:8080/api/admin/faculties");
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedItem, setSelectedItem] = useState(null);

//   useEffect(() => {
//     fetchStudents();
//     fetchFaculties();
//   }, [fetchStudents, fetchFaculties]);

  const handleEdit = (item) => {
    setSelectedItem(item);
    setIsModalOpen(true);
  };

  const handleDelete = async (id, type) => {
    const jwtToken = localStorage.getItem("jwt");
    await fetch(`http://localhost:8080/api/admin/${type.toLowerCase()}/${id}`, {
      method: "DELETE",
      headers: {
        Authorization: `Bearer ${jwtToken}`,
      },
    });
    // if (type === "student") {
    //   fetchStudents();
    // } else {
    //   fetchFaculties();
    // }
  };

  const AdminButtons = () => (
    <div className="admin-buttons">
      <button onClick={() => window.location.href = "/view-students"}>View All Students</button>
      <button onClick={() => window.location.href = "/view-faculties"}>View All Faculties</button>
      <button onClick={() => window.location.href = "/add-student"}>Add Student</button>
      <button onClick={() => window.location.href = "/add-faculty"}>Add Faculty</button>
    </div>
  );

  return (
    <div className="admin-dashboard-container">
      <h1>Welcome to Admin Dashboard</h1>
      <AdminButtons />
      {/* {isModalOpen && (
        <Modal
          item={selectedItem}
          onClose={() => setIsModalOpen(false)}
          refreshData={
            selectedItem.year !== undefined ? fetchStudents : fetchFaculties
          }
        />
      )} */}
      {/* <div className="section">
        <h2>Manage Students</h2>
        <StudentForm refreshStudents={fetchStudents} />
        <ul>
          {students.map((student) => (
            <li key={student.id}>
              {student.name} - {student.departmentId} - {student.year}
              <button onClick={() => handleEdit(student)}>Edit</button>
              <button onClick={() => handleDelete(student.id, "student")}>
                Delete
              </button>
            </li>
          ))}
        </ul>
      </div>
      <div className="section">
        <h2>Manage Faculty</h2>
        <FacultyForm refreshFaculties={fetchFaculties} />
        <ul>
          {faculties.map((faculty) => (
            <li key={faculty.id}>
              {faculty.name} - {faculty.departmentId} - {faculty.officeHours}
              <button onClick={() => handleEdit(faculty)}>Edit</button>
              <button onClick={() => handleDelete(faculty.id, "faculty")}>
                Delete
              </button>
            </li>
          ))}
        </ul>
      </div> */}
    </div>
  );
};

export default AdminDashboard;