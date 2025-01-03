import React, { useContext, useEffect } from 'react';
import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { BookContext } from './context/BookContext';
import Home from './pages/Home';
import SearchResults from './pages/SearchResults';
import BookDetails from './pages/BookDetails';
import LoginForm from './components/LoginForm';

const App: React.FC = () => {
  const { user } = useContext(BookContext);

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/login" element={<LoginForm />} />
        {/* Protect student routes */}
        <Route path="/" element={user ? <Home /> : <Navigate to="/login" />} />
        <Route path="/search" element={user ? <SearchResults /> : <Navigate to="/login" />} />
        <Route path="/book/:id" element={user ? <BookDetails /> : <Navigate to="/login" />} />

        {/* Protect admin routes */}
        <Route path="/admin/dashboard" element={user?.role === 'admin' ? <Admin /> : <Navigate to="/login" />} />
        {/* Add admin routes */}
      </Routes>
    </BrowserRouter>
  );
};

export default App;