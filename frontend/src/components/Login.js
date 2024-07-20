import React, { useState } from "react";
import "./Login.css"; // Import the CSS file

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [role, setRole] = useState("STUDENT"); // Default role

  const handleLogin = async (event) => {
    event.preventDefault();

    const loginData = {
      username,
      password,
      role
    };

    try {
      const response = await fetch("http://localhost:8080/api/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(loginData),
      });

      if (response.ok) {
        const data = await response.json();
        // Store the JWT token in localStorage or handle as needed
        localStorage.setItem("jwt", data.token);
        // Redirect based on role
        if (role === "FACULTY") {
          window.location.href = "/faculty-dashboard";
        } else if (role === "ADMIN") {
          window.location.href = "/admin-dashboard";
        } else {
          window.location.href = "/student-dashboard";
        }
      } else {
        // Handle errors
        console.error("Login failed");
      }
    } catch (error) {
      console.error("An error occurred:", error);
    }
  };

  return (
    <div className="login-container-wrapper">
      <div className="login-container"> {/* Apply CSS class */}
        <h2>Login</h2>
        <form onSubmit={handleLogin}>
          <div>
            <label>Username:</label>
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>
          <div>
            <label>Password:</label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <div>
            <label>Role:</label>
            <select value={role} onChange={(e) => setRole(e.target.value)}>
              <option value="STUDENT">Student</option>
              <option value="FACULTY">Faculty</option>
              <option value="ADMIN">Admin</option>
            </select>
          </div>
          <button type="submit">Login</button>
        </form>
      </div>
    </div>
  );
};

export default Login;