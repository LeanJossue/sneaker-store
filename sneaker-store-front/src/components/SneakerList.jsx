import { useEffect, useState } from "react";
import { getSneakers, deleteSneaker } from "../services/api";
import { Table, Button } from "react-bootstrap";

function SneakerList({ onEdit }) {
  const [sneakers, setSneakers] = useState([]);

  const loadData = () => getSneakers().then(res => setSneakers(res.data));

  useEffect(() => {
    loadData();
  }, []);

  const handleDelete = (id) => {
    if (window.confirm("¿Seguro que deseas eliminar este sneaker?")) {
      deleteSneaker(id).then(loadData);
    }
  };

  return (
    <div className="mt-4">
      <h2 className="mb-3">Inventario de Sneakers</h2>

      <Table striped bordered hover responsive>
        <thead className="table-dark">
          <tr>
            <th>Nombre</th>
            <th>Marca</th>
            <th>Categoría</th>
            <th>Color</th>
            <th>Talla</th>
            <th>Stock</th>
            <th>Precio</th>
            <th></th>
          </tr>
        </thead>

        <tbody>
          {sneakers.map(s => (
            <tr key={s.id}>
              <td>{s.name}</td>
              <td>{s.brand?.name}</td>
              <td>{s.category?.name || "-"}</td>
              <td>{s.color}</td>
              <td>{s.size}</td>
              <td>{s.stock}</td>
              <td>${s.price}</td>
              <td className="text-center">
                <Button size="sm" variant="warning" onClick={() => onEdit(s)}>
                  Editar
                </Button>{" "}
                <Button size="sm" variant="danger" onClick={() => handleDelete(s.id)}>
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

export default SneakerList;
