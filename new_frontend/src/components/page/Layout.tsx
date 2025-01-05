import React from "react";
import { Outlet } from "react-router-dom";
import NavBar from "../NavBar";

const Layout = () => {
  return (
    <div className="h-screen flex flex-col relative ">
      <NavBar />
      <div className="w-full">
        <Outlet />
      </div>
    </div>
  );
};

export default Layout;
