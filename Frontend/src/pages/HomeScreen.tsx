import React, { useContext } from 'react';
import BannerMain from '../Components/BannerAds/BannerMain';
import RecommendProduct from '../RecommendProduct';
import Navbar from '../Components/Header/Navbar';
import NavbarWithSearch from '../Components/Header/NavbarWithSearch';

function HomeScreen() {
  return (
    <div>
      {/* nav-bar */}
      <header className="App-header ">
          <Navbar />
          <NavbarWithSearch />
      </header>
      {/* Products and services page */}
      <div role={'main'}>
        <BannerMain />
        <RecommendProduct />
      </div>
    </div>
  );
}

export default HomeScreen;
