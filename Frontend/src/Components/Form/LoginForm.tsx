import React from 'react'
import SharedAuthForm from './SharedAuthForm'

function LoginForm(props) {
    let meta_data : {
        type:string;
        url:string;
        formInput: {
            email: string;
            password: string;
        };
        redirectTo: string
    } = {
        type: 'login',
        url: '/api/v1/auth/login',
        formInput: {
            email:'',
            password:''
        },
        redirectTo: '/'
    }

    const loginInputs = [
        {type:'email', name:'email', placeholder : 'Username', maxLength:128},
        {type:'password', name:'password', placeholder : 'Password', maxLength:30}
    ]
    
  return (
    <SharedAuthForm className={props.className} inputs={loginInputs} metadata={meta_data}></SharedAuthForm>
  )
}

export default LoginForm