import React from "react";
import { createBrowserRouter, Outlet } from "react-router-dom";
import Login from "./components/Login";
import TeacherDashboardPage from "./components/TeacherComponents/TeacherDashboardPage";
import getUserRole from "./utils";
import Header from "./components/Header";

export const getRoutes = () => {
  const isLoggedIn = localStorage.getItem("token");
  const isTeacher = getUserRole(isLoggedIn);

  const publicRoutes = [
    {
      path: "/",
      element: <Login />,
    },
    {
      path: "/login",
      element: <Login />,
    },
  ];

  const teacherRoutes = [
    {
      path: "/*",
      element: <TeacherDashboardPage />,
    },
  ];

  const studentRoutes = [];
  const privateRoutes = isTeacher ? teacherRoutes : studentRoutes;
  return isLoggedIn ? privateRoutes : publicRoutes;
};

const loadRoutes = () => {
  const routes = getRoutes();
  return [
    {
      element: (
        <>
          <Header />
          <Outlet />
        </>
      ),
      errorElement: <p>Error</p>,
      children: routes,
    },
  ];
};

const router = createBrowserRouter(loadRoutes());

export { router };
