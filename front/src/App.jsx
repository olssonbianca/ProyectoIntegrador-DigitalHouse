import { BrowserRouter, Routes, Route } from "react-router-dom";
import '../src/Styles/font.css'
import Details from "./Routes/Detail";
import CatDetails from "./Routes/CatDetail";
import Home from "./Routes/Home";
import Dashboard from "./Routes/Dashboard";
import ThemeContext from "./Components/utils/Context";
import React, { useEffect, useState } from 'react'
import Header from "./Components/Header";
import Footer from "./Components/Footer";
import AuthContextProvider from "./Components/utils/AuthContext";
import Login from "./Routes/Login";
import SignUp from "./Routes/SignUp";
import Account from "./Routes/Account";
import ProtectedRoutes from "./ProtectedRoutes";
import Booking from "./Routes/Booking";
import Confirmation from "./Routes/Confirmation";



export const themes = {
    light: {
        font: "black",
        background: "white",

    },
    dark: {
        font: "white",
        background: "black",
    },

};


const localValue = JSON.parse(localStorage.getItem('theme'));

function App() {

    const [theme, setTheme] = useState(localValue ?? themes.light );

    const handleChangeTheme = () => {
        theme === themes.dark ? setTheme(themes.light) : setTheme(themes.dark);

    };

    const [experiences, setExperiences] = useState([]);
    const [categories, setCategories] = useState([]);



    const getCategory = async () => {
      fetch(`http://ec2-3-17-204-179.us-east-2.compute.amazonaws.com:8080/api/category/list`)
              .then(res=>res.json())
              .then(json=>setCategories(json))
  
    };
    const getExperience = async () => {
        fetch(`http://ec2-3-17-204-179.us-east-2.compute.amazonaws.com:8080/api/experience/random`)
                .then(res=>res.json())
                .then(json=>setExperiences(json))
    
      };
  
    
    useEffect(() => {
      getCategory()
      getExperience()
      
    }, []);

    useEffect(() => {
        localStorage.setItem('theme',JSON.stringify(theme));

    },[theme]);
    
    return (

        <AuthContextProvider>
        <ThemeContext.Provider value={{theme,handleChangeTheme,categories,experiences}} >
            <BrowserRouter>
            <Header/>
                <Routes>
                
                    <Route path="/" element={<Home />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/signup" element={<SignUp />} />
                    <Route path="/catdetails/:id" element={<CatDetails />} />
                    <Route path="/details/:id" element={<Details />} />
                    <Route path="/account" element={<Account />} />
                    <Route path="/booking/:id" element={<Booking />} />
                    <Route path="/confirmation" element={<Confirmation />} />
                    <Route element={<ProtectedRoutes/>} >
                    <Route path="/dashboard" element={<Dashboard />} />
                    </Route>
                </Routes>
                <Footer/>
            </BrowserRouter>
        </ThemeContext.Provider>
        </AuthContextProvider>

    );

}
export default App;