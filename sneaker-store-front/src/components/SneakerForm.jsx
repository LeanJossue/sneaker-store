import { useState, useEffect } from "react";
import { createSneaker, updateSneaker } from "../services/api";
import axios from "axios";
import { Form, Button, Card } from "react-bootstrap";


function SneakerForm({ sneakerToEdit, onSaved }) {
  const [brands, setBrands] = useState([]);
  const [categories, setCategories] = useState([]);

  const [form, setForm] = useState(
    sneakerToEdit || {
      name: "",
      brand: "",
      category: "",
      color: "",
      size: "",
      stock: "",
      price: "",
    }
  );

  useEffect(() => {
    axios.get("http://localhost:8080/brands").then((res) => setBrands(res.data));
    axios.get("http://localhost:8080/categories").then((res) => setCategories(res.data));
  }, []);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();

    const payload = {
      ...form,
      size: Number(form.size),
      stock: Number(form.stock),
      price: Number(form.price),
      brand: { id: Number(form.brand) },
      category: form.category ? { id: Number(form.category) } : null,
    };

    if (form.id) updateSneaker(form.id, payload).then(onSaved);
    else createSneaker(payload).then(onSaved);
  };

  return (
    <Card className="p-4 shadow-sm mb-4">
      <h2 className="mb-3">{form.id ? "Editar Sneaker" : "Agregar Nuevo Sneaker"}</h2>

      <Form onSubmit={handleSubmit}>
        <Form.Group className="mb-3">
          <Form.Label>Nombre</Form.Label>
          <Form.Control name="name" value={form.name} onChange={handleChange} required />
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>Marca</Form.Label>
          <Form.Select name="brand" value={form.brand} onChange={handleChange} required>
            <option value="">Seleccione una Marca</option>
            {brands.map((b) => (
              <option key={b.id} value={b.id}>{b.name}</option>
            ))}
          </Form.Select>
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>Categoría</Form.Label>
          <Form.Select name="category" value={form.category} onChange={handleChange}>
            <option value="">Sin categoría</option>
            {categories.map((c) => (
              <option key={c.id} value={c.id}>{c.name}</option>
            ))}
          </Form.Select>
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>Color</Form.Label>
          <Form.Control name="color" value={form.color} onChange={handleChange} />
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>Talla</Form.Label>
          <Form.Control type="number" name="size" value={form.size} onChange={handleChange} />
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>Stock</Form.Label>
          <Form.Control type="number" name="stock" value={form.stock} onChange={handleChange} />
        </Form.Group>

        <Form.Group className="mb-3">
          <Form.Label>Precio</Form.Label>
          <Form.Control type="number" name="price" value={form.price} onChange={handleChange} />
        </Form.Group>

        <Button variant="primary" type="submit" className="w-100">
          Guardar
        </Button>
      </Form>
    </Card>
  );
}

export default SneakerForm;
