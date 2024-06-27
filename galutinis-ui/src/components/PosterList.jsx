import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";
import { DeletePoster } from "./DeletePoster.jsx";
import { EditPoster } from "./EditPoster.jsx";
import '../styles/PosterList.css';
import PostComment from "./PostComment.jsx";
import PosterCategoryService from "./PosterCategoryService.jsx";
import { CreatePoster } from "./CreatePoster.jsx";
import Logout from "./Logout.jsx";
import { Header } from "./Header.jsx";
import { Footer } from "./Footer.jsx";

const BASE_URL = "http://localhost:8080";

export const PostersList = () => {
  const [posterList, setPosterList] = useState([]);
  const [filteredPosters, setFilteredPosters] = useState([]);
  const [categories, setCategories] = useState([]);
  const [selectedCategory, setSelectedCategory] = useState('');
  const [roles, setRoles] = useState([]);
  const [searchTerm, setSearchTerm] = useState('');

  useEffect(() => {
    fetchPosters();

    // Check roles and setRoles
    const userRoles = JSON.parse(localStorage.getItem('userRoles'));
    if (userRoles) {
      setRoles(userRoles);
    }
  }, []);

  const fetchPosters = () => {
    axios
      .get(`${BASE_URL}/api/posters/allPosters`, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      })
      .then((response) => {
        setPosterList(response.data);
        setFilteredPosters(response.data);
        const uniqueCategories = [...new Set(response.data.map(poster => poster.category))];
        setCategories(uniqueCategories);
      })
      .catch((error) => {
        console.error("Error fetching posters:", error);
      });
  };

  const handleCategoryChange = (e) => {
    const category = e.target.value;
    setSelectedCategory(category);
    if (category) {
      setFilteredPosters(posterList.filter(poster => poster.category === category));
    } else {
      setFilteredPosters(posterList);
    }
  };

  const handleSearch = (e) => {
    setSearchTerm(e.target.value);
    if (e.target.value === '') {
      setFilteredPosters(posterList);
    } else {
      setFilteredPosters(posterList.filter(poster => poster.posterName.toLowerCase().includes(e.target.value.toLowerCase())));
    }
  };

  const handlePosterDeleted = (deletedPosterId) => {
    setPosterList((prevPosters) => prevPosters.filter(poster => poster.id !== deletedPosterId));
    setFilteredPosters((prevPosters) => prevPosters.filter(poster => poster.id !== deletedPosterId));
  };
  

  return (
    <>
      <Header/>
      <section className="posterlist-section">
        <div className="side-bar" >
          <div className="search-by-name-field">
            <input 
              type="text"
              placeholder="Enter poster name"
              value={searchTerm}
              onChange={handleSearch}
              maxLength="20ch"
              className="main-button placeholder"
              style={{width:"130px", marginBottom:".8rem"}}
            />
          </div>

          <div className="search-by-select">
            <select value={selectedCategory} onChange={handleCategoryChange} className="main-button" style={{width:"130px", textAlign:"center"}}>
              <option value="">All Categories</option>
              {categories.map((category) => (
                <option key={category} value={category}>
                  {category}
                </option>
              ))}
            </select>
            {roles.includes('ADMIN') && (
              <div className="admin-category-createposter-div">
                <PosterCategoryService/>
                <CreatePoster/>
              </div>
            )}
           
            {roles.includes('ADMIN') && <Logout />}
          </div>
        </div>
        <div className="posterlist-div" >
          {filteredPosters.map((poster) => (
            <div key={poster.id} className="single-poster-card">
              <Link to={`/singleposter/${poster.id}`}>
                <div>
                  <h3>{poster.posterName}</h3>
                </div>
              </Link>
              <p>{poster.description}</p>
              <div>
                <img src={poster.imageUrl} alt="image of the poster" />
              </div>
              <div>
                <p>Category: {poster.category}</p>
                <p>City: {poster.city}</p>
                <p>Price: {poster.price}</p>
                <div className="poster-delete-edit">
                  {roles.includes('ADMIN') && <DeletePoster posterId={poster.id} onPosterDeleted={handlePosterDeleted} />}
                  {roles.includes('ADMIN') && <EditPoster posterId={poster.id} />}
                  {!roles.includes('ADMIN') && <PostComment posterId={poster.id}/>}
                </div>
              </div>
            </div>
          ))}
        </div>
      </section>
      <Footer/>
    </>
  );
};

export default PostersList;
