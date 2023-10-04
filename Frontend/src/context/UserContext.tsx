import React , {createContext, useContext, useState, useEffect} from 'react'
import Cookies from 'js-cookie'
import data from '../data'

type userdata = {
  email: string,
  firstName : string,
  lastName : string
}

type AuthContext = {
  userdata : userdata;
  setUserData : React.Dispatch<React.SetStateAction<userdata>>
}

const refreshTokenUrl = '/api/v1/auth/refresh'
const userDetailUrl = '/api/v1/user'

export const AuthContext = createContext<AuthContext | null>(null)

export default  function AuthContextProvider({ children }) {
  
  const [userdata, setUserData] = useState<userdata> ({email:'', firstName : '', lastName : ''})
  const jwtToken:string = Cookies.get('jwtToken');
  const refreshToken:string = Cookies.get('refreshToken');
  
  useEffect(() =>  {
    getUserDetailFromToken(jwtToken, refreshToken)

    return () => {
    }
  }, [])
  
  const getUserDetailFromToken = async (jwtToken:string , refreshToken:string) => {
    if(isTokenExpired(jwtToken)){
      if(isTokenExpired(refreshToken)){
        setUserData({email:'', firstName : '', lastName : ''});
      }
      else{
        await refreshJwtToken(refreshToken)
        let newJwtToken :string
        await waitForCookie('jwtToken', 10, 1000).then((result) => {
          newJwtToken = result
        }).catch(()=> {
          newJwtToken =  null
        })
        
        await getUserDetail(newJwtToken)
      }
    }else{
      getUserDetail(jwtToken)
    }
  }

  const waitForCookie = async (cookieName:string, maxAttempts:number, intervalMs:number) :Promise<string> => {
    let attempts = 0;

    while (attempts < maxAttempts) {
      const cookieValue = Cookies.get(cookieName);

      if (cookieValue !== null) {
        return cookieValue;
      }

      await new Promise(resolve => setTimeout(resolve, intervalMs));
      attempts++;
    }
    
    throw new Error('cookie not setted')

  }

  const refreshJwtToken = async (refreshToken:string) => {
    await fetch(refreshTokenUrl, {
      method: 'POST',
      headers: {
        'Content-Type' : 'application/json',
        "Access-Control-Allow-Origin": "*",
        'Authorization' :  `Bearer ${refreshToken}`
      },
    })
    .then((response) => {
      if(response.ok){
        // return response.headers.getSetCookie() // not work some how, TBC
      }
    }).then((data)=> {
      // some data manipulation
    })
    .catch((err) => {
      console.log('Refresh jwt failed with messages:' + err.message);
    })
  }

  const getUserDetail =async (jwtToken:string) => {
    if (jwtToken == null) {
      throw new Error('token is empty cannot get user details')
    }


    await fetch(userDetailUrl, {
      method: 'GET',
      headers: {
        'Content-Type' : 'application/json',
        "Access-Control-Allow-Origin": "*",
        'Authorization' : `Bearer ${jwtToken}`
      },
    }).then((response) => {
      if (response.ok){        
        return response.json()
      }
    }).then((data) => {      
      setUserData({email:data.email, firstName: data.firstname, lastName: data.lastname})
    }).catch((err) => {
      console.log('Get User Detailed failed with message: ' + err.message)
    })
  }

  function isTokenExpired(token:string) : boolean {
    try {
      const payload = JSON.parse(atob(token.split('.')[1])); 
      const expirationTime = payload.exp * 1000; 
      const currentTime = Date.now();
      
      return currentTime > expirationTime;
    } catch (error) {
      
      return true; 
    }
  }

  return (
    <AuthContext.Provider
      value={{userdata, setUserData}}
    >
      {children}
    </AuthContext.Provider>
  )
}

export function useAuthContext () {
  const context = useContext(AuthContext)

  if (!context) {
    throw new Error("Context should be used within provider.")
  }

  return context
}