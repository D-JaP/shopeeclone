import React, { useEffect, useState } from 'react'
import "./SocialLoginButton.scss"

function SocialLoginButton(props) {
    const type : string =props.type
    // const [externalPopup, setExternalPopup] = useState<Window>(null)
    // const handleClick = (e) => {
    //     const width : number = 500;
    //     const height : number = 400;
    //     const left = window.screenX + (window.outerWidth - width) / 2;
    //     const top = window.screenY + (window.outerHeight - height) / 2.5;
    //     const title = `WINDOW TITLE`;
    //     const url = "/api/v1/login/oauth2/authorization/" + type
    //     const popup = window.open(url, title, `width=${width},height=${height},left=${left},top=${top}`);
    //     setExternalPopup(popup);
    // }

    // useEffect(() => {
    //   if (!externalPopup){
    //     return
    //   }
    //   const timer = setInterval(()=> {
    //     if (!externalPopup) {
    //         return
    //     }
    //     const currentUrl = externalPopup.location.search;
    //     if(!currentUrl){
    //         return
    //     }
    //     const searchParams = new URL(currentUrl).searchParams;
    //     const code = searchParams.get('code')
    //     console.log(code);
        
    //   })
    
    //   return () => {
        
    //   }
    // }, [externalPopup])
    
    
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