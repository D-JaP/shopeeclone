import React from 'react';
import './Navbar.css';
import UilFacebook from '@iconscout/react-unicons/icons/uil-facebook';
import UilInstagram from '@iconscout/react-unicons/icons/uil-instagram';
export default function Navbar() {
  return (
    <div className="--navbar-wrapper ">
      <div className="--navbar-content --container-wrapper">
        {/* left nav */}

        <div className="--left-navbar">
          <div>
            <a href="/" className="--navbar-text">
              Seller center
            </a>
          </div>
          <div className="--navbar-seperator"></div>
          <div>
            <a href="/" className="--navbar-text">
              Sell on Shopee
            </a>
          </div>
          <div className="--navbar-seperator"></div>
          <div>
            <a href="/" className="--navbar-text">
              Download
            </a>
          </div>
          <div className="--navbar-seperator"></div>
          <div className="--navbar-text">
            <a href="/" className="--navbar-text">
              Follow us on
            </a>
          </div>
          <div className="--navbar-text">
            <a href="/">
              <UilFacebook className="--navbar-icon" />
            </a>
          </div>
          <div className="--navbar-text">
            <a href="/">
              <UilInstagram className="--navbar-icon" />
            </a>
          </div>
        </div>

        {/* right nav */}

        <ul className="--right-navbar">
          <li>
            <a href="/" className="--navbar-text">
              Notification
            </a>
          </li>
          <li>
            <a href="/" className="--navbar-text">
              Help
            </a>
          </li>
          <li>
            <a href="/" className="--navbar-text">
              English
            </a>
          </li>
          <li>
            <a href="/" className="--navbar-text --sign-log">
              Sign up
            </a>
          </li>
          <div className="--navbar-seperator"></div>
          <li>
            <a href="/" className="--navbar-text --sign-log">
              Log in
            </a>
          </li>
        </ul>
      </div>
    </div>
  );
}
