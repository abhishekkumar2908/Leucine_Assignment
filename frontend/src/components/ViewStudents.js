import React, { useEffect } from 'react';

// Assuming useFetch is a custom hook you've defined elsewhere in your project
import useFetch from '../hooks/useFetch';
const ViewStudents = () => {
  // Destructure the returned values from useFetch
  const [{ data: students, isLoading, error }, fetchStudents] = useFetch("http://localhost:8080/api/students/");

  useEffect(() => {
    fetchStudents();
  }, [fetchStudents]);

  if (isLoading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  return (
    <div>
      <h2>Students List</h2>
      <ul>
        {students.map(student => (
          <li key={student.id}>{student.name} - {student.email}</li>
        ))}
      </ul>
    </div>
  );
};

export default ViewStudents;