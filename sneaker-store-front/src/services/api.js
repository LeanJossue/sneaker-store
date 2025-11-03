import axios from "axios";

const API_URL = "http://localhost:8080/sneakers";

export const getSneakers = () => axios.get(API_URL);

export const createSneaker = (data) => {
  const payload = {
    ...data,
    brand: { id: Number(data.brand) },
    category: { id: Number(data.category) },
    price: Number(data.price),
    size: Number(data.size),
    stock: Number(data.stock),
  };

  return axios.post(API_URL, payload);
};

export const updateSneaker = (id, data) => axios.put(`${API_URL}/${id}`, data);

export const deleteSneaker = (id) => axios.delete(`${API_URL}/${id}`);
