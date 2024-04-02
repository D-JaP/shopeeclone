import React from "react";
import "./NavbarWithSearch.css";
import { UilShoppingCart } from "@iconscout/react-unicons";
import { UilSearch } from "@iconscout/react-unicons";
import { Link } from "react-router-dom";
function NavbarWithSearch() {
  const form_style = {
    width: "100%",
  };
  const logo_url = process.env.PUBLIC_URL + "/img/shopee-logo.svg";
  return (
    <div className="--navbar-with-search-container">
      <div className=" --inner-container">
        {/* logo */}
        <div>
          <Link to={"/"}>
            <img
              className="--shopee-logo"
              src={logo_url}
              alt="logo"
              height={66}
              width={160}
            />
          </Link>
        </div>

        {/* Search bar */}
        <div className="--search-bar">
          <form style={form_style}>
            <input
              placeholder="Register now & get $10 off voucher!"
              className="--search-bar-input"
              maxLength={128}
            />
          </form>
          <div className="--search-button">
            <UilSearch className="--search-button-icon" />
          </div>
        </div>

        {/* Cart */}
        <img src={process.env.PUBLIC_URL + '/img/shopping-cart.svg'} alt="cart" className="--shopping-cart-icon mx-5"/>
      </div>
    </div>
  );
}

export default NavbarWithSearch;
