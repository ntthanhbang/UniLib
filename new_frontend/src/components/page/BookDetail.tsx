import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axios from "axios";

const BookDetail = () => {
  const { bookId } = useParams();
  const [book, setBook] = useState(null);

  useEffect(() => {
    const fetchBookDetails = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8080/api/v1/book/id/${bookId}`
        );
        setBook(response.data);
      } catch (error) {
        console.error("Error fetching book details:", error);
      }
    };

    fetchBookDetails();
  }, [bookId]);

  if (!book) {
    return <div>Loading...</div>;
  }

  return (
    <div className="p-12 flex gap-4 w-full h-screen">
      <center className="w-1/2 flex justify-center  ">
        <img className=" " src={book.bookCoverUrl} alt="" />
      </center>
      <div className="flex flex-col justify-center items-start gap-2">
        <h1 className="text-2xl font-bold">Name: {book.bookName}</h1>
        <h1 className="text-2xl font-bold">Author: </h1>
        <h1 className="text-2xl">{book.authors}</h1>
        <h1 className="text-2xl font-bold">Book Review: </h1>
        <h1 className="text-2xl">
          {book.description.substring(0, 100)} ... Read More
        </h1>
      </div>
    </div>
  );
};

export default BookDetail;
