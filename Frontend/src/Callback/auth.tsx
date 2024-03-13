const Cookies = require('js-cookie');

const refreshTokenUrl = '/api/v1/auth/refresh'
const userDetailUrl = '/api/v1/auth/user'

export const isUserLoggin = (user) => {
    if (Cookies.get('jwtToken')) {
        return true
    }
    else {
        return false;
    }
}

export const refreshJwtToken = async () => {
    await fetch(refreshTokenUrl, {
      method: 'POST',
      headers: {
        'Content-Type' : 'application/json',
        "Access-Control-Allow-Origin": "*",
      },
      credentials : 'include'
    })
    .then((response) => {
      if(response.ok){
        // return response.headers.getSetCookie() // not work some how, TBC
      }
    }).then(async (data)=> {
      console.log(data);
      let newJwtToken :string        
      await waitForCookie('jwtToken', 10, 1000).then((result) => {
        newJwtToken = result
        if (!newJwtToken) throw new Error("jwtToken not setted");
        else {
          return newJwtToken
        }
      })
    })
    .catch((err) => {
      console.log('Refresh jwt failed with messages:' + err.message);
    })
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