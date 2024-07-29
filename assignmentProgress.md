Hackathon Assignment: Classroom Assignment Management System
Objective:
Build a web application for managing classroom assignments. The application should have separate interfaces for teachers and students with the following functionalities:


Requirements
1. Login Page

  Prepared Login Page
  
   <img width="444" alt="Screenshot 2024-07-28 at 5 27 38 PM" src="https://github.com/user-attachments/assets/0b65ab05-362d-48f0-b595-a4fa13a03cfa">
  
   <img width="387" alt="Screenshot 2024-07-28 at 5 28 56 PM" src="https://github.com/user-attachments/assets/fd41b4d6-a586-4fa7-9573-d6bf5f723c33">

Implemented JWT Token, validation for credentials and ROLE.

Based on the role User will be redirected to resepective page.


2. Teacher Interface
  A. Add a New Assignment
   Prepared Add New Assignment page
     Form with the following fields:
      Title (Text)
      Description (Text Area)
      Due Date (Date Picker) 
      Attachments (File upload) 
      Class (Dropdown for 11th or 12th)
 
    <img width="892" alt="Screenshot 2024-07-28 at 3 32 08 PM" src="https://github.com/user-attachments/assets/d52da402-5642-4ba9-a941-1860aa207515">

    Tested in local and frontend
    PreAuthorised create Assignment API ( only teacher can create an assignment )
    Implemented validation for form input, especially Due Date: disabled past dates from frontend and validated from backend as well.
    <img width="421" alt="Screenshot 2024-07-28 at 6 44 43 PM" src="https://github.com/user-attachments/assets/b60b5b3f-9eb8-4451-9082-b904e42d6fec">
    
    On submit assignment will get saved in db with user id of the teacher.
    user id will be fetched from getUserByUserName (username will be fetched from jwt token) 
    <img width="765" alt="Screenshot 2024-07-28 at 10 51 55 PM" src="https://github.com/user-attachments/assets/01af7c29-ddf7-40cf-bf4a-223b6ce34496">

    In file filename will get saved. However Introduced get file by filename api which will take file name and returns a file path.
   which will be accessbile on the same network.
   <img width="1009" alt="Screenshot 2024-07-28 at 10 31 35 PM" src="https://github.com/user-attachments/assets/bf9846e9-3a84-4019-b347-dbed197cbe5d">


 B. View Submitted Assignments 
   Prepared a get api (accesseible by teacher only) for fetching all assignments from assignment table which are created by the teacher and then from submission table fetch those assignments associated with 
   the assignment Ids.

   Prepared a page for view submitted assignments. Connection from backend is remaining.

 C. View All Assignments
   Prepared a get api to fetch list of all the assignments by className for the teacher who created those.
   Introduced a ClassName enum which has XI, and XII.
   Introduced a class table in which each student will be associated with either XI or XII.
     
   <img width="861" alt="Screenshot 2024-07-28 at 3 31 47 PM" src="https://github.com/user-attachments/assets/0e9c2c2b-c2c0-44c8-b321-312f0afda0a6">

   Along with that created a update api for the same 
   <img width="844" alt="Screenshot 2024-07-28 at 3 33 16 PM" src="https://github.com/user-attachments/assets/8cd7ded3-b9ba-4acf-9ae1-c1d0abd6b177">
   Prepared a page for view assignments. Based on the class selected, teacher can view all his created assignments. 
   Integrated update and delete action also.
  <img width="701" alt="Screenshot 2024-07-29 at 11 35 37 PM" src="https://github.com/user-attachments/assets/e009cb3c-fe91-43cf-ad45-2ae93c1f4bfc">

  Integrated view action in order to view in details.
  <img width="705" alt="Screenshot 2024-07-30 at 12 58 10 AM" src="https://github.com/user-attachments/assets/fc477d1a-bdec-4170-b1ea-8454a2d1d6d0">
  

 D. Dashboard
At the time of login if user role selected is TEACHER then it will be redirected to the dashboard board which will look like this.
In the below image the pie chart is created by some dummy data. 
The idea is to display two charts one for XI and one for XII.
It will display total assignment submitted and pending
<img width="767" alt="Screenshot 2024-07-28 at 7 25 48 PM" src="https://github.com/user-attachments/assets/955ea411-ce00-4a45-aef8-2be5685310bf">

The agenda is to display that chart along with Upcoming Deadlines. 
<img width="349" alt="Screenshot 2024-07-28 at 11 40 25 PM" src="https://github.com/user-attachments/assets/7fc2435f-7a4e-421e-ac7d-27e51ffd5e15">
<img width="632" alt="Screenshot 2024-07-29 at 11 38 48 PM" src="https://github.com/user-attachments/assets/f6739c55-b5d2-4fda-b9ac-1e315cf09781">


3. Student Interface
   Working on it.
