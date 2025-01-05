import React, { useContext, useState } from 'react';
import BookCard from '../components/BookCard';
import { BookContext } from '../context/BookContext';

const SearchResults: React.FC = () => {
  const { searchResults } = useContext(BookContext);
  const [showModal, setShowModal] = useState(false);
  const [title, setTitle] = useState('');
  const [author, setAuthor] = useState('');

  const handleSubmitSuggestion = () => {
    //Send the book suggestion to the admin side
    console.log(`Suggested Book - Title: ${title}, Author: ${author}`);
    setShowModal(false);
  };

  return (
    <div className="container mx-auto p-4">
      {searchResults.length === 0 ? (
        <>
          <p>No books found</p>
          <button
            onClick={() => setShowModal(true)}
            className="mt-4 bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded"
          >
            Suggest a book to buy
          </button>

          {/*suggesting a book popup */}
          {showModal && (
            <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center">
              <div className="bg-white p-6 rounded-lg shadow-lg w-1/3">
                <h2 className="text-xl font-bold mb-4">Suggest a Book</h2>
                <div className="mb-4">
                  <label className="block text-gray-700">Title</label>
                  <input
                    type="text"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    className="w-full p-2 border border-gray-300 rounded-md"
                    placeholder="Enter book title"
                  />
                </div>
                <div className="mb-4">
                  <label className="block text-gray-700">Author</label>
                  <input
                    type="text"
                    value={author}
                    onChange={(e) => setAuthor(e.target.value)}
                    className="w-full p-2 border border-gray-300 rounded-md"
                    placeholder="Enter author name"
                  />
                </div>
                <div className="flex justify-end">
                  <button
                    onClick={handleSubmitSuggestion}
                    className="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded mr-2"
                  >
                    Suggest
                  </button>
                  <button
                    onClick={() => setShowModal(false)}
                    className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
                  >
                    Cancel
                  </button>
                </div>
              </div>
            </div>
          )}
        </>
      ) : (
        searchResults.map((book) => (
          <BookCard key={book.id} {...book} />
        ))
      )}
    </div>
  );
};

export default SearchResults;
