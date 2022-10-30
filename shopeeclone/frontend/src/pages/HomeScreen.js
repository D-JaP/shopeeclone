import React from 'react';
import BannerMain from '../BannerMain';
import RecommendProduct from '../RecommendProduct';


function HomeScreen() {
  return (
    <div>
      {/* Products and services page */}
      <div role={'main'}>
        <BannerMain className="--container-wrapper" />
        <RecommendProduct />
      </div>
    </div>
  );
}

export default HomeScreen;
