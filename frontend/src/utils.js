import { jwtDecode } from "jwt-decode";

export class UerRole {
  static TEACHER = "ROLE_TEACHER";
}

const getUserRole = (token = localStorage.getItem("token")) => {
  try {
    const user = jwtDecode(token);
    if (user.role === UerRole.TEACHER) return true;
  } catch (error) {
    console.error("Invalid token:", error);
    return null;
  }
};

export default getUserRole;
