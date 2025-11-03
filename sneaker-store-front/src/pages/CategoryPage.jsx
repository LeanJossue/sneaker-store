import { useEffect, useState } from "react";
import axios from "axios";
import { Table, Button, Form, Card } from "react-bootstrap";

function CategoryPage() {
  const [categories, setCategories] = useState([]);
  const [name, setName] = useState("");

  const loadData = () =>
    axios.get("http://localhost:8080/categories").then(res => setCategories(res.data));

  useEffect(loadData, []);

  const createCategory = () => {
    axios.post("http://localhost:8080/categories", { name }).then(() => {
      setName("");
      loadData();
    });
  };

  const deleteCategory = (id) =>
    axios.delete(`http://localhost:8080/categories/${id}`).then(loadData);

  return (
    <div className="container mt-4">
      <Card className="p-3">
        <h3>Crear Categoría</h3>
        <Form.Control
          placeholder="Nombre de categoría"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <Button className="mt-2" onClick={createCategory}>Guardar</Button>
      </Card>

      <h3 className="mt-4">Listado de Categorías</h3>
      <Table striped bordered hover>
        <thead>
          <tr><th>ID</th><th>Nombre</th><th>Acciones</th></tr>
        </thead>
        <tbody>
          {categories.map(c => (
            <tr key={c.id}>
              <td>{c.id}</td>
              <td>{c.name}</td>
              <td>
                <Button variant="danger" onClick={() => deleteCategory(c.id)}>
                  Eliminar
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
}

export default CategoryPage;
