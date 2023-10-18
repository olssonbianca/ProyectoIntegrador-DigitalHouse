import { createContext, useReducer } from "react";

export const AuthContext = createContext();

const initialState = {
  user: JSON.parse(localStorage.getItem("user")) || {} ,
  rol: JSON.parse(localStorage.getItem("rol")) || {} ,
  accessToken: localStorage.getItem("token") || "" ,
};

const authReducer = (state, action) => {
  switch (action.type) {
    case "LOGIN":
      localStorage.setItem( "token" , action.payload.accessToken )
      localStorage.setItem("user", JSON.stringify(action.payload.user))
      localStorage.setItem("rol", JSON.stringify(action.payload.rol))
      return {
        ...state,
        user: action.payload.user,
        rol: action.payload.rol,
        accessToken: action.payload.accessToken,
      };
    case "LOGOUT":
      localStorage.removeItem("token")
      localStorage.removeItem("user")
      localStorage.removeItem("rol")
      return {
        ...state,
        user: {},
        rol: {},
        accessToken: "",
      };

    default:
      return state;
  }
};

const AuthContextProvider = ({ children }) => {
  const [state, dispatch] = useReducer(authReducer, initialState);

  const data = {
    dispatch,
    user: state.user,
    rol: state.rol,
    token: state.accessToken
  }

  return <AuthContext.Provider value={data}>{children}</AuthContext.Provider>;
};

export default AuthContextProvider;
