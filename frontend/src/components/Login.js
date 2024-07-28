import React, { useState } from 'react';
import { useForm } from 'react-hook-form';
import './Login.css';
import { Axios } from '../axiosConfig';

const Login = () => {
  const { register, handleSubmit, formState: { errors } } = useForm();
  const [errorMessage, setErrorMessage] = useState('');

  const onSubmit = async (data) => {
    try {
      const response = await Axios.post ( '/auth/login', {
        username: data.username,
        password: data.password,
        role: data.role
      });

      console.log("response", response);
      if (response) {
        // Assuming the response contains a token and user role
        localStorage.setItem('token', response.token);
        localStorage.setItem('role', response.role);

        if (response.role === 'TEACHER') {
          window.location.href = '/';
        } else if (response.role === 'STUDENT') {
          window.location.href = '/student-dashboard';
        } else {
          window.location.href = '/';
        }
      } else {
        setErrorMessage('Invalid username or password.');
      }
    } catch (error) {
      setErrorMessage(error?.message);
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      {errorMessage && <div className="error-message">{errorMessage}</div>}
      <form onSubmit={handleSubmit(onSubmit)}>
        <div>
          <label>Username</label>
          <input type="text" {...register('username', { required: true })} />
          {errors.username && <span>This field is required</span>}
        </div>
        <div>
          <label>Password</label>
          <input type="password" {...register('password', { required: true })} />
          {errors.password && <span>This field is required</span>}
        </div>
        <div>
          <label>Role</label>
          <select {...register('role', { required: true })}>
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