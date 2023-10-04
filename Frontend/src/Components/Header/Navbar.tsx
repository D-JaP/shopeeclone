import React, { useEffect } from "react";
import "./Navbar.css";
import UilFacebook from "@iconscout/react-unicons/icons/uil-facebook";
import UilInstagram from "@iconscout/react-unicons/icons/uil-instagram";
import { useAuthContext } from "../../context/UserContext";
import Cookies from 'js-cookie'

export default function Navbar() {
  const  {userdata, setUserData}= useAuthContext();
  const  {email, firstName, lastName}  = userdata;

  const token = Cookies.get('jwtToken')
  
  

  return (
    <div className="--navbar-wrapper ">
      <div className="--navbar-content  --container-wrapper">
        {/* left nav */}
        <div className="--left-navbar">
          <div>
            <a
              href="/Users/dzung/project/shopeeclone/Frontend/src/pages"
              className="--navbar-text"
            >
              Seller center
            </a>
          </div>
          <div className="--navbar-seperator"></div>
          <div>
            <a
              href="/Users/dzung/project/shopeeclone/Frontend/src/pages"
              className="--navbar-text"
            >
              Sell on Shopee
            </a>
          </div>
          <div className="--navbar-seperator"></div>
          <div>
            <a
              href="/Users/dzung/project/shopeeclone/Frontend/src/pages"
              className="--navbar-text"
            >
              Download
            </a>
          </div>
          <div className="--navbar-seperator"></div>
          <div className="--navbar-text">
            <a
              href="/Users/dzung/project/shopeeclone/Frontend/src/pages"
              className="--navbar-text"
            >
              Follow us on
            </a>
          </div>
          <div className="--navbar-text">
            <a href="/Users/dzung/project/shopeeclone/Frontend/src/pages">
              <UilFacebook className="--navbar-icon" />
            </a>
          </div>
          <div className="--navbar-text">
            <a href="/Users/dzung/project/shopeeclone/Frontend/src/pages">
              <UilInstagram className="--navbar-icon" />
            </a>
          </div>
        </div>

        {/* right nav */}

        <ul className="--right-navbar">
          <li>
            <a
              href="/Users/dzung/project/shopeeclone/Frontend/src/pages"
              className="--navbar-text"
            >
              Notification
            </a>
          </li>
          <li>
            <a
              href="/Users/dzung/project/shopeeclone/Frontend/src/pages"
              className="--navbar-text"
            >
              Help
            </a>
          </li>
          <li>
            <a
              href="/Users/dzung/project/shopeeclone/Frontend/src/pages"
              className="--navbar-text"
            >
              English
            </a>
          </li>

          {email ? (
            <a href="/" className="--navbar-text --sign-log">
              Hi, {firstName} {lastName}
            </a>
          ) : (
            <>
              <li>
                <a href="/signup" className="--navbar-text --sign-log">
                  Sign up
                </a>
              </li>
              <div className="--navbar-seperator"></div>
              <li>
                <a href="/login" className="--navbar-text --sign-log">
                  Log in
                </a>
              </li>
            </>
          )}
        </ul>
      </div>
    </div>
  );
}
