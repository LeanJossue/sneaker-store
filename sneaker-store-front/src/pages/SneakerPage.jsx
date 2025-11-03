import SneakerList from "../components/SneakerList";
import SneakerForm from "../components/SneakerForm";
import { useState } from "react";

function SneakerPage() {
  const [editing, setEditing] = useState(null);

  return (
    <div className="container py-4">
      <h1 className="mb-4">Sneaker Store</h1>

      <SneakerForm sneakerToEdit={editing} onSaved={() => setEditing(null)} />

      <SneakerList onEdit={setEditing} />
    </div>
  );
}

export default SneakerPage;
