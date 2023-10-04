import React, { useEffect, useReducer, useState } from 'react'
import './SharedAuthForm.scss'
import { Link, redirect  } from 'react-router-dom';
import SocialLoginButton from './SocialLoginButton';
function SharedAuthForm(props) {
    const bg = {
        "background-img" : "/img/login/bg-img.png"
    } 
    type formDataType = {
        [key: string]: string;
    }
    const [formData, setFormData] = useState<formDataType|null>(props.metadata.formInput)
    const [formDisabled, setFormDisable] = useState(false)
    const [isPasswordMisMatch,setIsPasswordMisMatch] = useState(false)
    const [errorMsgBox, setErrMsgBox] = useState('')
    const [isLoginErr, setIsLoginErr] = useState(false)

    
    // Reducer for message box
    const MsgBoxReducer = (state, action ) => {
        switch (action.type){
            case 'login':
                return props.metadata.type + ' succeed'
            case 'signup':
                return props.metadata.type + ' succeed. An activation Email has been send.'
            default: 
                return ''
        }
    }

    const [okMsgBox, setOkMsgBox] = useReducer(MsgBoxReducer, '')

    const [isOk, setIsOk] = useState(false)

    useEffect(() => {
        if (formData.hasOwnProperty('password') && formData.hasOwnProperty('retypePassword')){            
            if (formData.password !== formData.retypePassword){               
                setIsPasswordMisMatch(true)
            }
            else {
                setIsPasswordMisMatch(false)
            }
        }
    }, [formData])
    
    useEffect(() => {
        const allValid = Object.values(formData).every((value)=> value !== '') && !isPasswordMisMatch
        
        allValid ? setFormDisable(false) : setFormDisable(true)
        
    }, [formData,isPasswordMisMatch])

    // show form 
    const handleFormChange = (event, inputName) => {
        setFormData({...formData, [inputName]: event.target.value})
    }

    // handle submit 
    const handleSubmit = async (e) => {
        e.preventDefault();
        
        await fetch(props.metadata.url, {
            method: 'POST',
            headers: {
                'Content-Type' : 'application/json',
                "Access-Control-Allow-Origin": "*",
            },
            body: JSON.stringify(formData)
        }) 
        .then((response) => {
            if(response.ok){
                setIsLoginErr(false)
                const newUrl = props.metadata.redirectTo
                setOkMsgBox({type : props.metadata.type})
                setIsOk(true)
                setTimeout(()=> {
                    window.location.href = newUrl
                }, 3000)
            }
            else{
                return response.json()
            }
        })
        .then((data) => {
            if (data !=null){
                setErrMsgBox(data.message)
                setIsLoginErr(true)
            }
        })
    
        .catch((err) => {
            console.log("Network err");
        })
    }
    
    return (
        <div className={props.className + ' ' +'bg-login'}>
            <form className="login-form" onSubmit={handleSubmit}>
                <div className="login-text">{props.metadata.type ==='signup'?'Sign up' : 'Login'}</div>
                {isLoginErr?<div className='msg-box'>{errorMsgBox}</div>: null}
                {isOk?<div className='msg-box green'>{okMsgBox}</div>: null}

                {props.inputs.map((e,index) => 
                    <div className="input" key={index}>
                        <input className={isPasswordMisMatch?(e.name==='retypePassword'?'re-pw-fault':null):null} type={e.type} name={e.name} placeholder={e.placeholder} maxLength={e.maxLength} onChange={(event) => handleFormChange(event, e.name)} required></input>
                    </div>
                )} 
                <div>
                    <button type="submit" className="login-btn" disabled={formDisabled} >{props.metadata.type ==='signup'?'SIGN UP' : 'LOGIN'}</button>
                </div>
                <div className="or-section">
                    <div className="spacer-bar"></div>
                    <div className="text">OR</div>
                    <div className="spacer-bar"></div>
                </div>
                
                
                <div>
                    {/* <SocialLoginButton type={'github'}></SocialLoginButton> */}
                    <SocialLoginButton type={'google'}></SocialLoginButton>
                </div>

                {
                    props.metadata.type === 'login'?(<div className="sign-up">
                    <p>New to Shopee Clone?</p><a href="/signup">Sign Up</a>
                    </div>): null
                }
                
                {props.metadata.type === 'signup'?
                    (<div className="sign-up">
                    <p>Already have account?</p><a href="/login">Login</a>
                    </div>): null
                }
                
            </form>
        </div>
    )
}

export default SharedAuthForm