import React, { useState, useEffect } from "react";
import axios from "axios";
import Modal from "react-bootstrap/Modal";
import Button from "react-bootstrap/Button";
import Form from "react-bootstrap/Form";

const BASE_URL = "http://localhost:8080";

export const EditPoster = ({ posterId }) => {
  const [form, setForm] = useState({
    posterName: "",
    description: "",
    city: "",
    imageUrl: "",
    price: "",
    category: "",
  });

  const [showModal, setShowModal] = useState(false);
  const [posterCategories, setPosterCategories] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchPosterDetails = async () => {
      try {
        if (posterId) {
          const response = await axios.get(`${BASE_URL}/api/posters/${posterId}`, {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("token")}`,
            },
          });
          const posterData = response.data;
          setForm({
            posterName: posterData.posterName,
            description: posterData.description,
            city: posterData.city,
            imageUrl: posterData.imageUrl,
            price: posterData.price,
            category: posterData.category,
          });
        }
      } catch (error) {
        setError(error.message);
      }
    };

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
    fetchPosterDetails();
  }, [posterId]);

  const handleFormChange = (event) => {
    const { name, value } = event.target;
    setForm((prevForm) => ({
      ...prevForm,
      [name]: value,
    }));
  };

  const handleFormSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.put(`${BASE_URL}/api/posters/${posterId}`, form, {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "Content-Type": "application/json",
        },
      });
      toggleForm();
      window.location.reload(false);
    } catch (error) {
      console.error("Error updating poster:", error);
    }
  };

  const toggleForm = () => {
    setShowModal(!showModal);
  };

  return (
    <>
      <button className="main-button" onClick={toggleForm}>
        Edit Poster
      </button>

      <Modal show={showModal} onHide={toggleForm}>
        <Modal.Header closeButton>
          <Modal.Title>Edit Poster</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form onSubmit={handleFormSubmit}>
            <Form.Group controlId="posterName">
              <Form.Label>Poster Name:</Form.Label>
              <Form.Control
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
                as="textarea"
                name="description"
                value={form.description}
                onChange={handleFormChange}
                rows={6}
                maxLength={500}
              />
            </Form.Group>
            <Form.Group controlId="city">
              <Form.Label>City:</Form.Label>
              <Form.Control
                type="text"
                name="city"
                value={form.city}
                onChange={handleFormChange}
              />
            </Form.Group>
            <Form.Group controlId="imageUrl">
              <Form.Label>Image URL:</Form.Label>
              <Form.Control
                type="text"
                name="imageUrl"
                value={form.imageUrl}
                onChange={handleFormChange}
              />
            </Form.Group>
            <Form.Group controlId="price">
              <Form.Label>Price:</Form.Label>
              <Form.Control
                type="text"
                name="price"
                value={form.price}
                onChange={handleFormChange}
              />
            </Form.Group>
            <Form.Group controlId="category">
              <Form.Label>Category:</Form.Label>
              <Form.Control
                as="select"
                name="category"
                value={form.category}
                onChange={handleFormChange}
              >
                <option value="">Select a category...</option>
                {posterCategories.map((category) => (
                  <option key={category.id} value={category.posterCategory}>
                    {category.posterCategory}
                  </option>
                ))}
              </Form.Control>
            </Form.Group>
            <div className="d-flex justify-content-end" style={{ marginTop: "1rem" }}>
              <Button variant="secondary" onClick={toggleForm} className="mr-2">
                Close
              </Button>
              <Button type="submit" className="submit-button" style={{ marginLeft: "10px" }}>
                Save Changes
              </Button>
            </div>
          </Form>
        </Modal.Body>
      </Modal>
    </>
  );
};
