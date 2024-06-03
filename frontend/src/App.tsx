import React from 'react';
import './App.css';
import { Route, Routes} from "react-router-dom";
import Main from "./pages/Main";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Home from "./pages/Home";
import Feedback from "./pages/Feedback";
import Reservation from "./pages/Reservation";
import AboutUs from "./pages/AboutUs";
import ContactUs from "./pages/ContactUs";
import BrowseRestaurants from "./pages/BrowseRestaurants";

function App() {
    return (
      <div className="App">
          <Routes>
              <Route path="/" element={<Main/>}></Route>
              <Route path="/login" element={<Login/>}></Route>
              <Route path="/register" element={<Register/>}></Route>
              <Route path="/home" element={<Home/>}></Route>
              <Route path="/feedback" element={<Feedback/>}></Route>
              <Route path="/reservations" element={<Reservation/>}></Route>
              <Route path="/about" element={<AboutUs/>}></Route>
              <Route path="/contact" element={<ContactUs/>}></Route>
              <Route path="/browse-restaurants" element={<BrowseRestaurants/>}></Route>
          </Routes>
      </div>
  );
}

export default App;
