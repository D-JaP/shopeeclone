import React from 'react'
import SharedAuthForm from './SharedAuthForm'

function SignUpForm(props) {
    let meta_data : {
        type:string;
        url:string;
        formInput: {
            email: string;
            password: string;
            retypePassword: string;
            firstname: string;
            lastname: string;
        };
        redirectTo: string
    } = {
        type: 'signup',
        url: '/api/v1/registration',
        formInput: {
            email: '',
            password: '',
            retypePassword: '',
            firstname: '',
            lastname: '',
        },
        redirectTo: '/login'
    }

    const signupInputs = [
        {type:'email', name:'email', placeholder : 'Email', maxLength:128},
        {type:'password', name:'password', placeholder : 'Password', maxLength:30},
        {type:'password', name:'retypePassword', placeholder : 'Retype password ', maxLength:30},
        {type:'text', name:'firstname', placeholder : 'First Name', maxLength:30},
        {type:'text', name:'lastname', placeholder : 'Last Name', maxLength:30},
    ]
    

  return (
    <SharedAuthForm className={props.className} inputs={signupInputs} metadata={meta_data}></SharedAuthForm>
  )
}

export default SignUpForm