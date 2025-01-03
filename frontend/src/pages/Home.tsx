import React, { useContext, useEffect, useState } from 'react';
import Navbar from '../components/Navbar';
import SearchBar from '../components/SearchBar';
import { BookContext } from '../context/BookContext';

const Home: React.FC = () => {
  const { fetchBooks } = useContext(BookContext);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const loadBooks = async () => {
      try {
        await fetchBooks();
      } catch (err) {
        console.error('Failed to fetch books', err);
      } finally {
        setLoading(false);
      }
    };

    loadBooks();
  }, [fetchBooks]);

  if (loading) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <Navbar />
      <SearchBar />
      {/*Other content */}
    </div>
  );
};

export default Home;
