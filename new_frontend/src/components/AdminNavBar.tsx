import { Link, useNavigate } from "react-router-dom";
import { Button } from "./ui/button";

const AdminNavBar = () => {
  const navigate = useNavigate();
  return (
    <div className="p-4  w-full bg-background border-b-2 border-border flex items-center justify-evenly gap-2">
      <div className="flex flex-row items-center gap-5 font-bold text-2xl   w-1/2">
        <Link to="/admin/">UniLib </Link>
        <div>
          <Link to="/admin/confirm">
            <Button variant={"link"}>Confirm</Button>
          </Link>
          <Link to="/admin/return">
            <Button variant={"link"}>Return</Button>
          </Link>
        </div>
      </div>

      <div>
        {localStorage.getItem("token") != null ? (
          <div className=" ">
            <Button
              onClick={() => {
                localStorage.clear();
                navigate("/login");
              }}
            >
              Logout
            </Button>
          </div>
        ) : (
          <div>
            <Button onClick={() => navigate("/login")}>Login</Button>
          </div>
        )}
      </div>
    </div>
  );
};

export default AdminNavBar;
