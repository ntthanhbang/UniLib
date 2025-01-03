import { React, useState, useEffect } from "react";
import axios from "axios";

import {
  Table,
  TableHeader,
  TableRow,
  TableCell,
  TableBody,
} from "../ui/table";

const MyReserve = () => {
  const [myReserve, setMyReserve] = useState();

  useEffect(() => {
    const fetchReserve = async () => {
      const res = await axios.get(
        "http://localhost:8080/api/v1/user/reserve/me",
        {
          headers: {
            Authorization: `Bearer ${localStorage.getItem("token")}`,
          },
        }
      );
      setMyReserve(res.data);
    };
    fetchReserve();
  }, []);

  return (
    <div>
      <div className="flex flex-col gap-4  justify-center items-center h-[50vh] p-12">
        <h1 className="text-2xl font-bold">My Reservation</h1>
        <Table>
          <TableHeader>
            <TableRow>
              <TableCell>Title</TableCell>
              <TableCell>Start</TableCell>
              <TableCell>End</TableCell>
            </TableRow>
          </TableHeader>
          <TableBody>
            {Array.isArray(myReserve) &&
              myReserve.map((res, index) => (
                <TableRow key={index}>
                  <TableCell>{res.BookName}</TableCell>
                  <TableCell>{res.start}</TableCell>
                  <TableCell>{res.end}</TableCell>
                </TableRow>
              ))}
          </TableBody>
        </Table>
      </div>
    </div>
  );
};

export default MyReserve;
