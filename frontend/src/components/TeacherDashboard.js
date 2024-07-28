import React, { useState } from 'react';
import './App.css';


function App() {
  const [title, setTitle] = useState('');
  const [description, setDescription] = useState('');
  const [file, setFile] = useState(null);

  const handleSubmit = async (event) => {
    event.preventDefault();

    const formData = new FormData();
    formData.append('title', title);
    formData.append('description', description);


    formData.append('file', file);

    try {
      const response = await fetch('http://localhost:8080/api/assignments', {
        method: 'POST',
        headers: {
          'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        body: formData
      });

      if (response.ok) {
        alert('Assignment submitted successfully!');
      } else {
        alert('Failed to submit assignment.');
      }
    } catch (error) {
      console.error('Error:', error);
      alert('An error occurred while submitting the assignment.');
    }
  };

  return (
    <div className="App">
      <h2>Assignment Submission</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="title">Title</label>
        <input
          type="text"
          id="title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
        />

        <label htmlFor="description">Description</label>
        <textarea
          id="description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          rows="4"
          required
        />

        <label htmlFor="file">Upload File</label>
        <input
          type="file"
          id="file"
          onChange={(e) => setFile(e.target.files[0])}
          required
        />

        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default TeacherDashboard;