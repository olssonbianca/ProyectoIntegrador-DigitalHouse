import { useContext } from "react";
import { Outlet, Navigate } from "react-router-dom";
import { AuthContext } from "./Components/utils/AuthContext";

const ProtectedRoutes = () => {
  const { rol } = useContext(AuthContext);

  if(rol.code !== "ADMIN" ) {
    return <Navigate to="/" />
  }

  return <Outlet />
};

export default ProtectedRoutes;
