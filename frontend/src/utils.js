// utils.js
export default function getUserRole(token) {
    if (!token) {
      return null;
    }
    
    // Decode the token to extract user role (assuming it's a JWT)
    const payload = JSON.parse(atob(token.split('.')[1]));
    return payload.role; // Assuming the role is stored in the payload
  }