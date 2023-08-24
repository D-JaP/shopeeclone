import React from 'react'
import './LoginNav.scss'
function LoginNav(props) {
  return (
    <div>
        <div className='--container-wrapper nav-container'>
            <img src="/img/logo_white.svg" className="logo-img" alt="logo" />
            <div className='heading1'>{props.option==="login"?"Log In": null}</div>
            <div className='heading1'>{props.option==="signup"?"Sign up": null}</div>
        </div>
    </div>
  )
}

export default LoginNav