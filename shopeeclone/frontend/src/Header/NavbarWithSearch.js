import React from 'react';
import './NavbarWithSearch.css';
import { UilShoppingCart } from '@iconscout/react-unicons';
import { UilSearch } from '@iconscout/react-unicons';
import { Link } from 'react-router-dom';
function NavbarWithSearch() {
  return (
    <div className="--navbar-with-search-container">
      {/* logo */}
      <div>
        <Link to={'/'}>
          <img
            className="--shopee-logo"
            src="http://localhost:3000/img/shopee-logo.svg"
            alt="logo"
            height={66}
            width={160}
          />
        </Link>
      </div>

      {/* Search bar */}
      <div className="--search-bar">
        <form>
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
      <div>
        <UilShoppingCart className="--shopping-cart-icon" />
      </div>
    </div>
  );
}

export default NavbarWithSearch;
