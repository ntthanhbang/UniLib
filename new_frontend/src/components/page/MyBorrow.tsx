import React, { useEffect, useState } from "react";
import axios from "axios";
import {
  Table,
  TableHeader,
  TableRow,
  TableCell,
  TableBody,
} from "../ui/table";

const MyBorrow = () => {
  const [myBorrow, setMyBorrow] = useState([]);

  useEffect(() => {
    const fetchBooks = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8080/api/v1/user/borrow/me",
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("token")}`,
            },
          }
        );
        setMyBorrow(response.data);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };

    fetchBooks();
  }, []);

  return (
    <div className="flex flex-col gap-4  justify-center items-center h-[50vh] p-12">
      <h1 className="text-2xl font-bold">My Borrow</h1>
      <Table>
        <TableHeader>
          <TableRow>
            <TableCell>Title</TableCell>
            <TableCell>Borrower</TableCell>
            <TableCell>start</TableCell>
            <TableCell>end</TableCell>
          </TableRow>
        </TableHeader>
        <TableBody>
          {Array.isArray(myBorrow) &&
            myBorrow.map((book) => (
              <TableRow key={book.bookId}>
                <TableCell>{book.bookName}</TableCell>
                <TableCell>{book.fullName}</TableCell>
                <TableCell>{book.begin}</TableCell>
                <TableCell>{book.end}</TableCell>
              </TableRow>
            ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default MyBorrow;
