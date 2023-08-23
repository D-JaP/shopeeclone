import React from 'react';
import './BannerMain.css';

function BannerMain() {
  return (
    <div className="--container-wrapper">
      <div className="--banner-main">
        {/* Slide banner */}
        <div className="--slide-banner">
          <img
            className="--slide-image --image-banner"
            src="./img/banner_3.png"
            alt="running_banner"
          />
        </div>
        {/* Right img banner */}
        <div className="--right-banner">
          <div className="--right-banner-content-1">
            <a href="/Users/dzung/project/shopeeclone/Frontend/src/pages">
              <img
                className="--small-image-banner --image-banner"
                src="./img/small_banner_1.jfif"
                alt="small-banner"
              />
            </a>
          </div>

          <div className="--right-banner-content-2">
            <a href="/Users/dzung/project/shopeeclone/Frontend/src/pages">
              <img
                className="--small-image-banner --image-banner"
                src="./img/small_banner_2.jfif"
                alt="small-banner"
              />
            </a>
          </div>
        </div>
      </div>
    </div>
  );
}

export default BannerMain;
