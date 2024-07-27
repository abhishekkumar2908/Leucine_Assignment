// src/Header.js
import React from "react";
import { useNavigate } from "react-router-dom";
import "./Header.css";

const Header = () => {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("jwt");
    navigate("/login");
  };

  return (
    <header className="header">
      <h1>Admin Dashboard</h1>
      <button onClick={handleLogout}>Logout</button>
    </header>
  );
};

export default Header;
