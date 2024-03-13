import React from "react";
import "./ProductTab.scss";
import { Link } from "react-router-dom";

type imgUrl = {
  id:number;
  order: number;
  imageUrl: string;
};
type imgList = imgUrl[];

function ProductTab({
  name,
  price,
  img,
  id,
}: {
  name: string;
  price: number;
  img: imgList;
  id: number
}) {
  const product_img = process.env.PUBLIC_URL + "/img/pdt_id2_img_1.jpg";
  const not_found_img = process.env.PUBLIC_URL + "/img/notavailable.jpg";
  return (
    <a href={`/product/${convertToSlug(name + '-'+ id)}`} style={{textDecoration:"none", color:`var(--global-text-color)`}}>
      <div className="ProductTab">
        <img
          src={
            img != null && img.length > 0 ? img[0]["imageUrl"] : not_found_img
          }
          alt="product"
        />
        <div className="product-info">
          <p className="product-name">{name}</p>
          <p className="price">{"$" + price}</p>
        </div>
      </div>
    </a>
  );
}

// function convert string to slug
function convertToSlug(str) {
  // Replace spaces with hyphens
  str = str.replace(/\s+/g, '-');
  
  // Convert to lowercase
  str = str.toLowerCase();
  
  // Remove special characters
  str = str.replace(/[^\w\-]+/g, '');
  
  return str;
}



export default ProductTab;
