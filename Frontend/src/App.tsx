import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Navbar from './Components/Header/Navbar';
import NavbarWithSearch from './Components/Header/NavbarWithSearch';
import HomeScreen from './pages/HomeScreen';
import ProductPage from './pages/ProductPage';
import Auth from './pages/Auth';

function App() {
  return (
    <BrowserRouter>
      <div>
        <Routes>
          <Route path="/" element={<HomeScreen />} />
          <Route path="/product/:slug" element={<ProductPage />} />
          <Route path="/login" element={<Auth option="login"/>}></Route>
          <Route path="/signup" element={<Auth option="signup"/>}></Route>
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
