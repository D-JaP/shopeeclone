import React from 'react'
import './UserDetailPopUp.scss'
import { Link } from 'react-router-dom'
import Logout from './Logout'

function UserDetailPopUp() {
  return (
    <div className='popup-nav-greeting'>
        <div className='triangle-up'></div>
        <div className='popup-box'>
            <ul>
                <li><a href="">My Account</a></li>
                <li><a href="">My Purchase</a></li>
                <li><a href='/' onClick={()=> Logout()}>Log out</a></li>
            </ul>
        </div>
    </div>
    
  )
}

export default UserDetailPopUp