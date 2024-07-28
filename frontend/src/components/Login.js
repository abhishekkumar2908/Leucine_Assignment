import React, { useState } from "react";
import { useForm } from "react-hook-form";
import axiosInstance from "../axiosConfig";
import "./Login.css";

const Login = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();
  const [errorMessage, setErrorMessage] = useState("");

  const onSubmit = async (data) => {
    try {
      const response = await axiosInstance.post("/api/auth/login", data); // Ensure this matches your backend endpoint
      localStorage.setItem("token", response.data.token);
      // Redirect to the dashboard or appropriate page based on role
      const userRole = data.role.toUpperCase();
      if (userRole === "TEACHER") {
        window.location.href = "/teacher-dashboard"; // Adjust the path as needed
      } else if (userRole === "STUDENT") {
        window.location.href = "/student-dashboard"; // Adjust the path as needed
      }
    } catch (error) {
      if (error.response && error.response.status === 401) {
        setErrorMessage("Invalid credentials. Please try again.");
      } else if (error.response && error.response.status === 403) {
        setErrorMessage("Invalid role selected.");
      } else {
        setErrorMessage("An error occurred. Please try again later.");
      }
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      {errorMessage && <div className="error-message">{errorMessage}</div>}
      <form onSubmit={handleSubmit(onSubmit)}>
        <div>
          <label>Username</label>
          <input type="text" {...register("username", { required: true })} />
          {errors.username && <span>This field is required</span>}
        </div>
        <div>
          <label>Password</label>
          <input
            type="password"
            {...register("password", { required: true })}
          />
          {errors.password && <span>This field is required</span>}
        </div>
        <div>
          <label>Role</label>
          <select {...register("role", { required: true })}>
            <option value="">Select role</option>
            <option value="TEACHER">Teacher</option>
            <option value="STUDENT">Student</option>
          </select>
          {errors.role && <span>This field is required</span>}
        </div>
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default Login;