import React, { useState, useEffect } from "react";
import axios from "axios";
import Button from "react-bootstrap/Button";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";

const BASE_URL = "http://localhost:8080";

export const CreatePoster = () => {
  const [form, setForm] = useState({
    posterName: "",
    description: "",
    city: "",
    imageUrl: "",
    price: "",
    category: "",
  });

  const [posterCategories, setPosterCategories] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [showSuccessMessage, setShowSuccessMessage] = useState(false);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchPosterCategories = async () => {
      try {
        const response = await axios.get(`${BASE_URL}/api/categories`);
        setPosterCategories(response.data);
      } catch (error) {
        setError(error.message);
      }
    };

    fetchPosterCategories();
  }, []);

  const handleFormChange = (event) => {
    const { name, value } = event.target;
    setForm((prevForm) => ({
      ...prevForm,
      [name]: value,
    }));
  };

  const handleCategorySelection = (categoryName) => {
    setForm((prevForm) => ({
      ...prevForm,
      category: categoryName,
    }));
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(`${BASE_URL}/api/posters`, form, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "Content-Type": "application/json",
        },
      });
      toggleForm();
      setShowSuccessMessage(true);
      setForm({
        posterName: "",
        description: "",
        city: "",
        imageUrl: "",
        price: "",
        category: "",
      });
      setTimeout(() => {
        setShowSuccessMessage(false);
        window.location.reload(); // Reload the page after success
      }, 900);
    } catch (error) {
      console.error("Error creating poster:", error);
    }
  };

  const toggleForm = () => {
    setShowModal(!showModal);
  };

  const buttonText = showSuccessMessage ? "Poster created successfully" : "Create Poster";

  return (
    <>
      <button className="main-button" style={{ width: "130px" }} type="button" onClick={toggleForm}>
        {buttonText}
      </button>

      <Modal show={showModal} onHide={toggleForm}>
        <Modal.Body>
          <Form onSubmit={handleFormSubmit}>
            <Form.Group controlId="posterName">
              <Form.Label>Poster Name:</Form.Label>
              <Form.Control
                placeholder="Poster Name"
                required
                type="text"
                name="posterName"
                value={form.posterName}
                onChange={handleFormChange}
                maxLength={30}
              />
            </Form.Group>
            <Form.Group controlId="description">
              <Form.Label>Description:</Form.Label>
              <Form.Control
                placeholder="Description"
                as="textarea"
                required
                name="description"
                value={form.description}
                onChange={handleFormChange}
                rows={6}
                style={{ resize: "none" }}
                maxLength={500}
              />
            </Form.Group>
            <Form.Group controlId="city">
              <Form.Label>City:</Form.Label>
              <Form.Control
                placeholder="City"
                required
                type="text"
                name="city"
                value={form.city}
                onChange={handleFormChange}
              />
            </Form.Group>
            <Form.Group controlId="imageUrl">
              <Form.Label>Image URL:</Form.Label>
              <Form.Control
                placeholder="Image URL"
                required
                type="text"
                name="imageUrl"
                value={form.imageUrl}
                onChange={handleFormChange}
              />
            </Form.Group>
            <Form.Group controlId="price">
              <Form.Label>Price:</Form.Label>
              <Form.Control
                placeholder="Price"
                required
                type="text"
                name="price"
                value={form.price}
                onChange={handleFormChange}
              />
            </Form.Group>
            <Form.Group controlId="category">
              <Form.Label>Category:</Form.Label>
              <select
                className="main-button"
                style={{ padding: ".3rem", margin: ".8rem" }}
                value={form.category}
                onChange={(e) => handleCategorySelection(e.target.value)}
              >
                <option value="">Select a category...</option>
                {posterCategories.map((category) => (
                  <option key={category.id} value={category.posterCategory}>
                    {category.posterCategory}
                  </option>
                ))}
              </select>
            </Form.Group>
            <div style={{ height: "20px" }}></div>
            <div className="d-flex justify-content-end">
              <Button variant="secondary" onClick={toggleForm} className="mr-2">
                Close
              </Button>
              <Button className="submit-button" type="submit" style={{ marginLeft: "5px" }}>
                Save
              </Button>
            </div>
          </Form>
        </Modal.Body>
      </Modal>
    </>
  );
};
