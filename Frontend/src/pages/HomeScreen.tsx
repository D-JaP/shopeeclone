import React, { useContext } from 'react';
import BannerMain from '../Components/BannerAds/BannerMain';
import RecommendProduct from '../RecommendProduct';
import Navbar from '../Components/Header/Navbar';
import NavbarWithSearch from '../Components/Header/NavbarWithSearch';
import "./HomeScreen.scss"
import {Category} from '../Components/Category/Category';

function HomeScreen() {
  return (
    <div className='Home'>
      {/* nav-bar */}
      <header className="App-header ">
          <Navbar />
          <NavbarWithSearch />
      </header>
      {/* Products and services page */}
      <div role={'main'}>
        <BannerMain />
        <Category/>
        <RecommendProduct />
      </div>
    </div>
  );
}

export default HomeScreen;
