import React from 'react'
import "./ProductTab.scss"

type imgUrl = {
  order: number,
  imageUrl: string
}
type imgList = imgUrl[]


function ProductTab({name, price, img} : {name: string, price: number, img: imgList}) {
    const product_img = process.env.PUBLIC_URL + "/img/pdt_id2_img_1.jpg"
    const not_found_img = process.env.PUBLIC_URL + "/img/notavailable.jpg"
  return (
    <div className='ProductTab'>
        <img src={(img!=null && img.length>0)? img[0]["imageUrl"]:not_found_img} alt="product"/>
        <div className='product-info'>
            <p className='product-name'>{name}</p>
            <p className='price'>{"$" + price}</p>
        </div>
    </div>
  )
}

export default ProductTab