import React, { useState, useEffect } from "react";
import axios from "axios";

const BASE_URL = "http://localhost:8080";

const PosterCategoryList = ({ selectedCategory, onSelectCategory }) => {
  const [posterCategories, setPosterCategories] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchPosterCategories = async () => {
      try {
        const token = localStorage.getItem("token");
        const response = await axios.get(`${BASE_URL}/api/categories`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setPosterCategories(response.data);
      } catch (error) {
        setError(error.message);
      }
    };

    fetchPosterCategories();
  }, []);

  const handleCategorySelect = (event) => {
    const selectedCategoryId = event.target.value;
    const selectedCategory = posterCategories.find(category => category.id === selectedCategoryId);
    onSelectCategory(selectedCategory ? selectedCategory.categoryName : "");
  };

  const renderPosterCategoryOptions = () => {
    return posterCategories.map(category => (
      <option key={category.id} value={category.id}>{category.categoryName}</option>
    ));
  };

  return (
    <>
      {error && <p style={{ color: "red" }}>Error: {error}</p>}
      <select value={selectedCategory} onChange={handleCategorySelect}>
        <option value="">Select a category...</option>
        {renderPosterCategoryOptions()}
      </select>
    </>
  );
};

export default PosterCategoryList;
