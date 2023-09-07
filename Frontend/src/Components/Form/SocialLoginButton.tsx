import React from 'react'
import "./SocialLoginButton.scss"

function SocialLoginButton(props) {
    const type : string =props.type
  return (
    <>
        {<a className={"button button--social-login button--" + type} href={"/api/v1/login/oauth2/authorization/" + type}><img src={'/img/login/'+ type + '.svg'} alt={type + 'icon'} className='icon'></img>Login With {capitalizeFirstLetter(type)}</a>}
    </>
  )
}

function capitalizeFirstLetter(string : string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}

export default SocialLoginButton