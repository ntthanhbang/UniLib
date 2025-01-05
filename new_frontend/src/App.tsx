import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./components/page/Login";
import Layout from "./components/page/Layout";
import Home from "./components/page/Home";
import MyBorrow from "./components/page/MyBorrow";
import BookDetail from "./components/page/BookDetail";
import MyReserve from "./components/page/MyReserve";
import AdminLayout from "./components/page/AdminLayout";
import AdminConfirm from "./components/page/AdminConfirm";
import AdminReturn from "./components/page/AdminReturn";

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        <Route path="/login" element={<Login />} />
        <Route path="/home" element={<Home />}></Route>
        <Route path="/borrow" element={<MyBorrow />}></Route>
        <Route path="/detail/:bookId" element={<BookDetail />}></Route>
        <Route path="/reserve" element={<MyReserve />}></Route>
      </Route>
      <Route path="/admin" element={<AdminLayout />}>
        <Route path="/admin/confirm" element={<AdminConfirm />}></Route>
        <Route path="/admin/return" element={<AdminReturn></AdminReturn>}>
          {" "}
        </Route>
      </Route>
    </Routes>
  );
};

export default App;
