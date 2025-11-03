import { Container, Nav, Navbar as RBNavbar } from "react-bootstrap";
import { Link } from "react-router-dom";

function AppNavbar() {
  return (
    <RBNavbar bg="dark" variant="dark" expand="lg">
      <Container>
        <RBNavbar.Brand as={Link} to="/">Sneaker Store</RBNavbar.Brand>
        <RBNavbar.Toggle aria-controls="navbar" />
        <RBNavbar.Collapse id="navbar">
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/">Sneakers</Nav.Link>
            <Nav.Link as={Link} to="/brands">Marcas</Nav.Link>
            <Nav.Link as={Link} to="/categories">Categorías</Nav.Link>
          </Nav>
        </RBNavbar.Collapse>
      </Container>
    </RBNavbar>
  );
}

export default AppNavbar;
