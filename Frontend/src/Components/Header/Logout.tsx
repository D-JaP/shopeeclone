import React, { useEffect } from "react";
import Cookies from "js-cookie";
import { response } from "express";
import { redirect } from "react-router-dom";
async function Logout() {  
  Cookies.remove('jwtToken');
  Cookies.remove('refreshToken');
  Cookies.remove('JSESSIONID');

  await fetch("/api/v1/logout", {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
      "Access-Control-Allow-Origin": "*",
    },
  }).then((response) => {
    if(response.ok){
      // return redirect('/')
    }
  }).catch((err)=> {
    console.log(err);
  });


}

export default Logout;
