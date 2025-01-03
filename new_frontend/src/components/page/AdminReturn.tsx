import React, { useState, useEffect } from "react";
import axios from "axios";
import {
  Table,
  TableHeader,
  TableRow,
  TableCell,
  TableBody,
} from "../ui/table";
import { Button } from "../ui/button";

const ReturnConfirm = async (borrowId: number) => {
  try {
    await axios.post(
      `http://localhost:8080/api/v1/admin/borrow/return/${borrowId}`,
      null,
      {
        headers: {
          accept: "*/*",
          Authorization: `Bearer ${localStorage.getItem("token")}`,
        },
      }
    );
    window.location.reload();
  } catch (error) {
    console.error(error);
  }
};

const AdminReturn = () => {
  const [returnList, setReturnList] = useState();

  useEffect(() => {
    const fetchReturn = async () => {
      try {
        const res = await axios.get(
          "http://localhost:8080/api/v1/admin/borrow",
          {
            headers: {
              Authorization: `Bearer ${localStorage.getItem("token")}`,
            },
          }
        );
        setReturnList(res.data);
      } catch (error) {
        console.log(error);
      }
    };

    fetchReturn();
  }, []);

  return (
    <div className="p-12">
      <Table>
        <TableHeader>
          <TableRow>
            <TableCell>BorrowId</TableCell>
            <TableCell>User</TableCell>
            <TableCell>Book</TableCell>
            <TableCell>Status</TableCell>
            <TableCell>Confirm Return</TableCell>
          </TableRow>
        </TableHeader>
        <TableBody>
          {Array.isArray(returnList) &&
            returnList.map((returnItem, index) => (
              <TableRow key={index}>
                <TableCell>{returnItem.borrowId}</TableCell>
                <TableCell>
                  {returnItem.user.firstName + " " + returnItem.user.lastName}
                </TableCell>
                <TableCell>{returnItem.book.bookName}</TableCell>
                <TableCell>{returnItem.borrowStatus}</TableCell>
                <TableCell>
                  <Button onClick={() => ReturnConfirm(returnItem.borrowId)}>
                    Confirm Return
                  </Button>
                </TableCell>
              </TableRow>
            ))}
        </TableBody>
      </Table>
    </div>
  );
};

export default AdminReturn;
