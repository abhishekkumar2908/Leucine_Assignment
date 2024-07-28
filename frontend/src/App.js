import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./components/Login";
import TeacherDashboardPage from "./components/TeacherComponents/TeacherDashboardPage"; // Adjust the path if needed

function App() {
  return (
    <Router>
      <div className="App">
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/login" element={<Login />} />
          <Route path="/teacher-dashboard/*" element={<TeacherDashboardPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;