import React from 'react'
import './WhtTextOrgBGBtn.scss'

function WhtTextOrgBGBtn({className = '', children= ''}) {
  return (
    <button className={'--button ' + className} >{children}</button>
  )
}

export default WhtTextOrgBGBtn