import React, { useContext, useState } from 'react';
import { BookContext } from '../context/BookContext';

const SearchBar: React.FC = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const { searchBooks } = useContext(BookContext);

  const handleSearch = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSearchTerm(event.target.value);
    searchBooks(event.target.value);
  };

  return (
    <div className="container mx-auto p-4">
      <input
        type="text"
        placeholder="Search for books..."
        value={searchTerm}
        onChange={handleSearch}
        className="w-full p-2 border border-gray-300 rounded-md"
      />
    </div>
  );
};

export default SearchBar;