import React from "react";
import "./Header.css"; // Import CSS for styling
import { isUserLoggedIn } from "../utils";

const Header = () => {

  const handleLogout = () => {
    localStorage.removeItem("token");
    window.location.href='/login';
  };

  return (
    <header className="header">
      <h1>My Application</h1>
      {
        isUserLoggedIn() && 
        <button className="logout-button" onClick={handleLogout}>
        Logout
        </button>
      }
    </header>
  );
};

export default Header;
