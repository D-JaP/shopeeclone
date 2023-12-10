import React from 'react'
import './SellerHeader.scss'
function SellerHeader() {
    const logo : string = process.env.PUBLIC_URL + "/img/logo.svg"
  return (
    <div className='SellerHeader container mx-auto flex mt-3 mb-3'>
        <img src={logo} alt="logo" className='h-8 '/>
        <p className='align-bottom mt-auto ms-3'>Seller Page (TBD)</p>
    </div>
  )
}

export default SellerHeader