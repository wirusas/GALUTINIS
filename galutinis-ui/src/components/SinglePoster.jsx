import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link, useParams } from "react-router-dom";
import PostComment from "./PostComment";
import { Header } from "./Header";
import { Footer } from "./Footer";
import '../styles/SinglePoster.css';

const BASE_URL = "http://localhost:8080";

const SinglePoster = () => {
  const { id } = useParams();
  const [poster, setPoster] = useState({});
  const [roles, setRoles] = useState([]);

  useEffect(() => {
    
    const userRoles = JSON.parse(localStorage.getItem('userRoles'));
    if (userRoles) {
      setRoles(userRoles);
    }

    
    axios
      .get(`${BASE_URL}/api/posters/${id}`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      })
      .then((response) => {
        setPoster(response.data);
      })
      .catch((error) => {
        console.error("Error fetching poster:", error);
      });
  }, [id]);

  const handleCommentAdded = (updatedPoster) => {
    setPoster(updatedPoster);
  };

  return (
    <>
      <Header />
      <main className="single-poster-card single">
        <article className="single-poster-container">
          <div>
            <h2>{poster.posterName}</h2>
            <p>{poster.description}</p>
          </div>

          <div>
            <img src={poster.imageUrl} alt="image of the poster" />
          </div>

          <div>
            <p>Category: {poster.category}</p>
            <p>City: {poster.city}</p>
            <p>Price: {poster.price}</p>

            <h2>Comments:</h2>
            <ul>
              {poster.comment && Object.keys(poster.comment).length > 0 ? (
                Object.entries(poster.comment).map(([user, comments], index) => (
                  <li key={index}>
                    <strong>{user}:</strong>
                    <ul>
                      {comments.map((comment, idx) => (
                        <li key={idx}>{comment}</li>
                      ))}
                    </ul>
                  </li>
                ))
              ) : (
                <li>No comments available</li>
              )}
            </ul>
          </div>
        </article>
        {!roles.includes('ADMIN') && <PostComment posterId={id} onCommentAdded={handleCommentAdded} />}
        
        <div className="back-button main-button">
          {/* Conditional rendering based on roles */}
          {roles.includes('ADMIN') && <Link to="/admindashboard" className="back-to-previous-page-link">Back to Admin Dashboard</Link>}
        </div>
      </main>
      <Footer />
    </>
  );
};

export default SinglePoster;
