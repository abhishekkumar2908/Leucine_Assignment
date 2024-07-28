import React from "react";
import { useNavigate } from "react-router-dom";
import "./Header.css"; // Import CSS for styling

const Header = () => {

  const handleLogout = () => {
    localStorage.removeItem("token");
    window.location.href='/login';
  };

  return (
    <header className="header">
      <h1>My Application</h1>
      <button className="logout-button" onClick={handleLogout}>
        Logout
      </button>
    </header>
  );
};

export default Header;
