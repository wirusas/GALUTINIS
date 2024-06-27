import React from "react";
import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import { isUserLoggedIn } from "./services/AuthService";
import LoginComponent from "./components/LoginComponent";
import { UserDashboard } from "./components/UserDashboard";
import { AdminDashboard } from "./components/AdminDashboard";
import SinglePoster from "./components/SinglePoster";



function App() {
  function AuthenticatedRoute({ children }) {
    const isAuth = isUserLoggedIn();

    if (isAuth) {
      return children;
    }
    // return <Navigate to="/" />;
  }
  return (
    <>
    <BrowserRouter>

<Routes>
         {/* /http://localhost:3000/login */}
<Route path="/login" element={<LoginComponent />}>

</Route>

<Route
            path="/"
            element={
              <UserDashboard/>
              }
          >

</Route>

<Route
            path="/admindashboard"
            element={
              <AuthenticatedRoute>
                <AdminDashboard/>
              </AuthenticatedRoute>
            }
          >
        
</Route>

<Route
            path="/singleposter/:id"
            element={
            <SinglePoster/>
           }
          >

</Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
