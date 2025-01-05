import React, { useEffect, useState } from "react";
import axios from "axios";
import {
  Card,
  CardHeader,
  CardContent,
  CardFooter,
  CardTitle,
} from "../ui/card";
import { Button } from "../ui/button";
import { Input } from "../ui/input";
import { Link } from "react-router-dom";

const Borrow = async (bookId: number) => {
  try {
    const data = {
      bookId: bookId,
    };
    const response = await axios.post(
      `http://localhost:8080/api/v1/user/borrow/create-borrow`,
      data,
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      }
    );
    console.log("Book reserved successfully:", response.data);
    window.location.reload();
  } catch (error) {
    alert(error);
  }
};

const Reserve = async (bookId) => {
  try {
    await axios.post(
      "http://localhost:8080/api/v1/user/reserve/create-reserve",
      String(bookId),
      {
        headers: {
          Authorization: `Bearer ${localStorage.getItem("token")}`,
          "Content-Type": "application/json",
        },
      }
    );
    window.location.reload();
  } catch (error) {
    alert(error);
  }
};

const Home = () => {
  const [books, setBooks] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const response = await axios.get("http://localhost:8080/api/v1/book");
        setBooks(response.data);
      } catch (error) {
        console.error("Error fetching books:", error);
      }
    };

    fetchBooks();
  }, []);

  const filteredBooks = books.filter((book) =>
    book.bookName.toLowerCase().includes(searchQuery.toLowerCase())
  );

  return (
    <div className="flex flex-col items-center h-screen">
      <Input
        type="text"
        placeholder="Search books..."
        value={searchQuery}
        onChange={(e) => setSearchQuery(e.target.value)}
        className="p-2 w-1/2 m-4 border rounded"
      />
      {filteredBooks.length > 0 ? (
        <div className="p-8 my-auto grid grid-cols-3 gap-2">
          {filteredBooks.map((book) => (
            <Card key={book.bookId}>
              <CardHeader>
                <center>
                  <img
                    width={"50%"}
                    height={"500%"}
                    src={book.bookCoverUrl}
                    alt=""
                  />
                </center>
              </CardHeader>
              <CardContent>
                <CardTitle>{book.bookName}</CardTitle>
              </CardContent>
              <CardFooter>
                <div className="w-full">
                  {book.bookStatus === "AVAILABLE" ? (
                    <div className="flex flex-col gap-2">
                      {" "}
                      <Button
                        className="w-full"
                        onClick={() => {
                          Borrow(book.bookId);
                        }}
                      >
                        Borrow
                      </Button>
                      <Link to={`/detail/${book.bookId}`}>
                        {" "}
                        <Button className="w-full" variant="outline">
                          View Detail{" "}
                        </Button>
                      </Link>
                    </div>
                  ) : (
                    <div className="flex flex-col gap-2">
                      <Button
                        onClick={() => {
                          Reserve(book.bookId);
                        }}
                        className="w-full"
                        variant={"secondary"}
                      >
                        Reserve
                      </Button>
                      <Link to={`/detail/${book.bookId}`}>
                        {" "}
                        <Button className="w-full" variant="outline">
                          View Detail{" "}
                        </Button>
                      </Link>
                    </div>
                  )}
                </div>
              </CardFooter>
            </Card>
          ))}
        </div>
      ) : (
        <div className="text-2xl items-center font-bold">No books found</div>
      )}
    </div>
  );
};

export default Home;
