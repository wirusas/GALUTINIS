import React, { useEffect, useState } from 'react';
import { Link, useLocation } from 'react-router-dom';
import '../styles/Header.css';
import { LogedUser } from './LogedUser.jsx';

export const Header = () => {
  const [roles, setRoles] = useState([]);
  const location = useLocation();

  useEffect(() => {
    // Fetch roles here
    const userRoles = JSON.parse(localStorage.getItem('userRoles'));
    if (userRoles) {
      setRoles(userRoles);
    }
  }, []);

  return (
    <>
      <div className="header-section">
        <div className='header-nawbar'>
          <p>Welcome to &#x01A4;osters Comunity <LogedUser/></p>
          {!roles.includes('ADMIN') && <Link to="/login" className="main-button">LOGIN AS ADMIN</Link>}
          {location.pathname !== '/' && !roles.includes('ADMIN') && <Link to="/" className="main-button">Back to MAIN</Link>}
        </div>
        <h1>&#x01A4;osters &#x0186;omunity</h1>
      </div>
    </>
  );
};
