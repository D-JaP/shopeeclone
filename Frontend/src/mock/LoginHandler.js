import {rest} from 'msw'

const loginUrl = '/api/v1/auth/login'
const registerUrl = '/api/v1/registration'

export const loginHandler = [
    rest.post(loginUrl, (req,res,ctx) => {
        const isAuthenticated = sessionStorage.getItem('username')
        if (isAuthenticated) {
            return res(ctx.status(200), ctx.json({message:'Login Successfully'}))
        }
        else {
            return res(ctx.status(403),ctx.json({message : 'Not authenticated'}))
        }
    }),

    rest.post(registerUrl, (req, res, ctx ) => {
        const isRegistered = sessionStorage.getItem('username')
        if (isRegistered){
            return res(ctx.status(200), ctx.json({messages  :'register success'}))
        }
        else {
            return res(ctx.status(404), ctx.json({message : 'Network error'}))
        }
    })
]