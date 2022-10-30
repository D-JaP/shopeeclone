import React from 'react';
import { Link } from 'react-router-dom';
export default function Product(props) {
  const { product } = props;
  // product sold quantity round-up
  function roundSoldQty(num) {
    if (isNaN(num)) return ' ';
    if (num < 1000) return num + ' sold';
    if (num >= 1000) return (num / 1000).toFixed(1) + 'k sold';
  }
  return (
    <Link
      to={`/product/${product.slug}`}
      className="--product"
      key={product.slug}
    >
      <div className="--img-box-product">
        <img
          className="--product-image-ad"
          src={product.image[0]}
          alt={product.name}
        />
      </div>
      <div className="--product-description-sale-box">
        <div className="--product-description-box">{product.name}</div>
        <div className="--product-sale-box">
          <div>${product.price}</div>
          <div className="qty_sold">{roundSoldQty(product.sold)}</div>
        </div>
      </div>
    </Link>
  );
}
