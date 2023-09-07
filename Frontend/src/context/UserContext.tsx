import React , {createContext, useContext, useState} from 'react'

type userdata = {
  email: string,
  firstName : string,
  lastName : string
}

type AuthContext = {
  userdata : userdata;
  setUserData : React.Dispatch<React.SetStateAction<userdata>>
}

export const AuthContext = createContext<AuthContext | null>(null)

export default function AuthContextProvider({ children }) {
  const [userdata, setUserData] = useState<userdata> ({email:'', firstName : '', lastName : ''})

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