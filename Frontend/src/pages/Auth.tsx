import React from 'react'
import LoginNav from '../Components/Header/LoginNav'
import LoginForm from '../Components/Form/LoginForm'
import './Auth.scss'
import Footer from '../Components/Footer/Footer'
import SignUpForm from '../Components/Form/SignupForm'

function Auth(props) {
  
  const style_bg = {
    backgroundImage : "url('/img/login/bg-img.png')",
    backgroundRepeat: "no-repeat",
    backgroundPosition: "center"
  }

  return (
    <div>
        <LoginNav option={props.option}></LoginNav>
        <div className="bg-login-pr" style={style_bg}>
          <div></div>
          {props.option === "login" ? (<LoginForm className="form_style"></LoginForm>) : null}
          {props.option === "signup" ? (<SignUpForm className="form_style"></SignUpForm>) : null}
        </div>
        <Footer></Footer>
    </div>
  )
}

export default Auth