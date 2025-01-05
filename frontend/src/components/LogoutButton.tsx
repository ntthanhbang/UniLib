import React, { useContext } from 'react';
import { BookContext } from '../context/BookContext';

const LogoutButton: React.FC = () => {
  const { logout } = useContext(BookContext);

  const handleLogout = () => {
    logout();
  };

  return (
    <button 
      onClick={handleLogout} 
      className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
    >
      Logout
    </button>
  );
};

export default LogoutButton;