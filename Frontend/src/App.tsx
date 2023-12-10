import React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';

import HomeScreen from './pages/HomeScreen';
import ProductPage from './pages/ProductPage';
import Auth from './pages/Auth';
import AuthContextProvider from './context/UserContext';
import SellerPage from './pages/SellerPage';

function App()  {
  return (
    <BrowserRouter>
      <AuthContextProvider>
        <Routes>
          <Route path="/" element={<HomeScreen />} />
          <Route path="/product/:slug" element={<ProductPage />} />
          <Route path="/login" element={<Auth option="login"/>}></Route>
          <Route path="/signup" element={<Auth option="signup"/>}></Route>
          <Route path='/seller' element={<SellerPage />}></Route>
        </Routes>
      </AuthContextProvider>
    </BrowserRouter>
  );
}

export default App;
