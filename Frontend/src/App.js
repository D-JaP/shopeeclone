import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Navbar from './Header/Navbar';
import NavbarWithSearch from './Header/NavbarWithSearch';
import HomeScreen from './pages/HomeScreen';
import ProductPage from './pages/ProductPage';
function App() {
  return (
    <BrowserRouter>
      <div>
        {/* nav-bar */}
        <header className="App-header">
          <Navbar />
          <NavbarWithSearch />
        </header>

        <Routes>
          <Route path="/" element={<HomeScreen />} />
          <Route path="/product/:slug" element={<ProductPage />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
