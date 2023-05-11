import React from 'react';
import styled, { css } from 'styled-components';
import AddToCartButton from './AddToCartButton';
import BuyNowButton from './BuyNowButton';
import './MainProductCard.css';
import 'font-awesome/css/font-awesome.min.css';
import Rating from './Rating';

const SmallProductImage = styled.div`
  background-image: url(${(props) => props.backgroundImage});
  margin: 5px;
  width: 82px;
  height: 82px;
  background-repeat: no-repeat;
  background-size: contain;
  background-position-x: center;
  background-origin: content-box;
  &:hover {
    box-shadow: inset 0 0 0 1px #ee4d2d;
    transition: all 0.05s 0s;
  }
`;

// label
const ProductInfoLabel = styled.div`
  color: #757575;
  width: 110px;
  text-transform: capitalize;
  flex-shrink: 0;
  align-items: center;
`;

export default function MainProductCard(props) {
  const { product } = props;
  // const [first, setfirst] = useState();
  return (
    <div className="--product-card --container-wrapper">
      <div className="--product-image-card">
        <div
          className="--product-main-image"
          style={{ backgroundImage: `url(${product.image[0]})` }}
        ></div>
        <div className="--product-nav-images">
          <SmallProductImage
            backgroundImage={product.image[0]}
          ></SmallProductImage>
          <SmallProductImage
            backgroundImage={product.image[1]}
          ></SmallProductImage>
        </div>
      </div>
      <div className="--product-purchase-info">
        <div className="--product-info-name">{product.name}</div>
        <Rating sold={product.sold} rating={product.rating}></Rating>
        <div className="--product-price"></div>
        <div className="--product-shipping"></div>
        <div className="--product-quantity">
          <ProductInfoLabel>Shipping</ProductInfoLabel>
          <ProductInfoLabel>Variation</ProductInfoLabel>
          <div className="">
            <ProductInfoLabel>Quantity</ProductInfoLabel>
          </div>
        </div>
        <AddToCartButton></AddToCartButton>
        <BuyNowButton></BuyNowButton>
      </div>
    </div>
  );
}
