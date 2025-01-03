import React, { createContext, useState, ReactNode } from "react";
import { useNavigate } from "react-router-dom";

interface Book {
  title: string;
  author: string;
  imageUrl: string;
  summary: string;
  isAvailable: boolean;
}

interface User {
  id: string;
  username: string;
  role: "student" | "admin";
}

interface BookContextProps {
  books: Book[];
  searchResults: Book[];
  fetchBooks: () => Promise<void>;
  searchBooks: (searchTerm: string) => Promise<void>;
  borrowBook: (bookId: string) => Promise<void>;
  reserveBook: (bookId: string) => Promise<void>;
  user: User | null;
  login: (user: User) => void; 
  logout: () => void;
}

const BookContext = createContext<BookContextProps>({} as BookContextProps);

const BookProvider: React.FC<{ children: ReactNode }> = ({ children }) => {
  const [books, setBooks] = useState<Book[]>([]);
  const [searchResults, setSearchResults] = useState<Book[]>([]);
  const [user, setUser] = useState<User | null>(null);
  const navigate = useNavigate();

  const login = (user: User) => {
    setUser(user);
    if (user.role === 'admin') {
      navigate('/admin/dashboard');
    } else {
      navigate('/');
    }
  };

  const logout = () => {
    setUser(null);
    navigate('/login');
  }

  const fetchBooks = async () => {
    try {
      const response = await fetch("/api/books");
      if (!response.ok) throw new Error("Failed to fetch books");
      const data = await response.json();
      setBooks(data);
    } catch (error) {
      console.error("Error fetching books:", error);
      alert("Failed to fetch books. Please try again later.");
    }
  };

  const searchBooks = async (searchTerm: string) => {
    try {
      const response = await fetch(`/api/books?search=${searchTerm}`);
      if (!response.ok) throw new Error("Failed to search books");
      const data = await response.json();
      setSearchResults(data);
    } catch (error) {
      console.error("Error searching books:", error);
      alert("Failed to search books. Please try again later.");
    }
  };

  const borrowBook = async (bookId: string) => {
    try {
      const response = await fetch(`/api/books/borrow/${bookId}`, {
        method: "POST",
      });
      if (!response.ok) throw new Error("Failed to borrow book");
      alert("Book borrowed successfully.");
    } catch (error) {
      console.error("Error borrowing book:", error);
      alert("Failed to borrow book. Please try again later.");
    }
  };

  const reserveBook = async (bookId: string) => {
    try {
      const response = await fetch(`/api/books/reserve/${bookId}`, {
        method: "POST",
      });
      if (!response.ok) throw new Error("Failed to reserve book");
      alert("Book reserved successfully.");
    } catch (error) {
      console.error("Error reserving book:", error);
      alert("Failed to reserve book. Please try again later.");
    }
  };

  return (
    <BookContext.Provider
      value={{
        books,
        searchResults,
        fetchBooks,
        searchBooks,
        borrowBook,
        reserveBook,
        user,
        login,
        logout,
      }}
    >
      {children}
    </BookContext.Provider>
  );
};

export { BookContext, BookProvider };
