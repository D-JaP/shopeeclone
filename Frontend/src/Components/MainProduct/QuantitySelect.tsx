import "./QuantitySelect.scss";

import React, { useEffect, useState } from 'react'

function QuantitySelect(
    {maxQuantity, setQuantity}: {
    maxQuantity: number,
    setQuantity: (quantity: number) => void
}
) {
    const [orderQuantity, setorderQuantity] = useState(1)

    const handerInputQuantityChange = (event) => {
        event.preventDefault()
        const inputValue = event.target.value
        const regex = /^[0-9\b]+$/;
        
        if(!inputValue) {
            setorderQuantity(1)
            return
        }
        if (inputValue === '' || regex.test(inputValue)) {
            if (parseInt(inputValue) > maxQuantity) {
                setorderQuantity(maxQuantity);
            }
            else {
                setorderQuantity(parseInt(inputValue));
            }
        }
    }
    const minusQuantityBy1 = () => {
        if(orderQuantity > 1){
            setorderQuantity(orderQuantity-1)
        }
    }
    const addQuantityBy1 = () => {
        if(orderQuantity < maxQuantity){
            setorderQuantity(orderQuantity+1)
        }
    }
    useEffect(() => {
      setQuantity(orderQuantity)
    
      return () => {
      }
    }, [orderQuantity])
    

  return (
    <div className="quantity-select-container d-flex justify-content-center align-items-center">
        <button className="modifyQuantityBtn" onClick={minusQuantityBy1}>-</button>
        <input type="text" className="order-quantity text-center" value={orderQuantity} onChange={handerInputQuantityChange}></input>
        <button className="modifyQuantityBtn" onClick={addQuantityBy1}>+</button>
    </div>
  )
}

export default QuantitySelect