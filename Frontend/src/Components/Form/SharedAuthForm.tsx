import React, { useEffect, useState } from 'react'
import './SharedAuthForm.scss'
import { Link } from 'react-router-dom';
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
        const allValid = Object.values(formData).every((value)=> value != '') && !isPasswordMisMatch
        
        allValid ? setFormDisable(false) : setFormDisable(true)

    }, [formData,isPasswordMisMatch])

    // show form 
    const handleFormChange = (event, inputName) => {
        setFormData({...formData, [inputName]: event.target.value})
    }

    const handleSubmit = async (e) => {
        e.preventDefault();
        try{
            const response = await fetch(props.metadata.url, {
                method: 'POST',
                headers: {
                    'Content-Type' : 'application/json',
                },
                body: JSON.stringify(FormData)
            })   
            if (response.ok){
                // redirect save cookie
            }
            else{
                
            }
        }
        catch (ex) {
            // handle err
        }
    }
    
    return (
        <div className={props.className + ' ' +'bg-login'}>
            <form className="login-form" onSubmit={handleSubmit}>
                <div className="login-text">{props.metadata.type ==='signup'?'Sign up' : 'Login'}</div>
                {props.inputs.map((e,index) => 
                    <div className="input" key={index}>
                        <input className={isPasswordMisMatch?(e.name=='retypePassword'?'re-pw-fault':null):null} type={e.type} name={e.name} placeholder={e.placeholder} maxLength={e.maxLength} onChange={(event) => handleFormChange(event, e.name)} required></input>
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
                
                {props.metadata.type === 'login'?
                    (<div className="sign-up">
                    <p>New to Shopee Clone?</p><Link to="/signup">Sign Up</Link>
                    </div>): null
                }
                
                {props.metadata.type === 'signup'?
                    (<div className="sign-up">
                    <p>Already have account?</p><Link to="/login">Login</Link>
                    </div>): null
                }
                
            </form>
        </div>
    )
}

export default SharedAuthForm