import React, { useContext, useState } from 'react';
import { BookContext } from '../context/BookContext';

interface Book {
  id: string;
  title: string;
  author: string;
  imageUrl: string;
  isAvailable: boolean;
}

const BookCard: React.FC<Book> = ({ id, title, author, imageUrl, isAvailable }) => {
  const { borrowBook, reserveBook } = useContext(BookContext);
  const [isProcessing, setIsProcessing] = useState(false);

  const handleBorrow = async () => {
    setIsProcessing(true);
    try {
      await borrowBook(id);
    } catch (error) {
      console.error('Failed to borrow book:', error);
      alert('An error occurred while borrowing the book.');
    } finally {
      setIsProcessing(false);
    }
  };

  const handleReserve = async () => {
    setIsProcessing(true);
    try {
      await reserveBook(id);
    } catch (error) {
      console.error('Failed to reserve book:', error);
      alert('An error occurred while reserving the book.');
    } finally {
      setIsProcessing(false);
    }
  };

  return (
    <div className="bg-white rounded-lg shadow-md p-4">
      <img
        src={imageUrl || '/fallback-image.jpg'}
        alt={title}
        className="w-full h-48 object-cover mb-4 rounded"
      />
      <h3 className="text-lg font-bold mb-2">{title}</h3>
      <p className="text-gray-600 mb-4">{author}</p>
      {isAvailable ? (
        <button
          onClick={handleBorrow}
          disabled={isProcessing}
          aria-label={`Borrow ${title}`}
          className={`font-bold py-2 px-4 rounded ${
            isProcessing
              ? 'bg-blue-300 text-white cursor-not-allowed'
              : 'bg-blue-500 hover:bg-blue-700 text-white'
          }`}
        >
          {isProcessing ? 'Processing...' : 'Borrow'}
        </button>
      ) : (
        <button
          onClick={handleReserve}
          disabled={isProcessing}
          aria-label={`Reserve ${title}`}
          className={`font-bold py-2 px-4 rounded ${
            isProcessing
              ? 'bg-gray-400 text-white cursor-not-allowed'
              : 'bg-gray-500 hover:bg-gray-700 text-white'
          }`}
        >
          {isProcessing ? 'Processing...' : 'Reserve'}
        </button>
      )}
    </div>
  );
};

export default BookCard;
