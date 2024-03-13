import React from 'react'
import { Navbar } from 'react-bootstrap'
import NavbarWithSearch from '../Components/Header/NavbarWithSearch'

function CategoryProduct() {
  return (
    <div role="main" className="--container-background ">
      <header className="App-header ">
          <Navbar />
          <NavbarWithSearch />
      </header>
      
      
    </div>
  )
}

export default CategoryProduct