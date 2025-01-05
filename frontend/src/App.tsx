import React, { useContext, useEffect } from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import { BookContext } from "./context/BookContext";
import Home from "./pages/Home";
import SearchResults from "./pages/SearchResults";
import BookDetails from "./pages/BookDetails";
import LoginForm from "./components/LoginForm";

const App: React.FC = () => {
  const { user } = useContext(BookContext);

  return (
    <Routes>
      <Route path="/login" element={<LoginForm />} />
      {/* Protect student routes */}
      <Route path="/" element={<Home />} />
      <Route
        path="/search"
        element={user ? <SearchResults /> : <Navigate to="/login" />}
      />
      <Route
        path="/book/:id"
        element={user ? <BookDetails /> : <Navigate to="/login" />}
      />

      {/* Protect admin routes */}
      <Route
        path="/admin/dashboard"
        element={user?.role === "admin" ? <Admin /> : <Navigate to="/login" />}
      />
      {/* Add admin routes */}
    </Routes>
  );
};

export default App;
