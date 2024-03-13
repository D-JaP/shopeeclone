import React, { useEffect, useState } from "react";
import styled from "styled-components";
import AddToCartButton from "./AddToCartButton";
import BuyNowButton from "./BuyNowButton";
import "./MainProductCard.scss";
import "font-awesome/css/font-awesome.min.css";
import Rating from "./Rating";
import { productImageApiGet } from "../../mock/productApiMock";
import ImageSlide from "./ImageSlide";
import QuantitySelect from "./QuantitySelect";

// label
const ProductInfoLabel = styled.div`
  color: #757575;
  width: 110px;
  text-transform: capitalize;
  flex-shrink: 0;
  align-items: center;
`;

export default function MainProductCard({product} : {product: ProductApiGetResponse}) {
  const [productImages, setproductImages] = useState<string[]>([]);
  const [mainImage, setMainImage] = useState<string>("");
  const [loading, setLoading] = useState(true);
  const [quantity, setQuantity] = useState(1);
  const [maxQuantity, setMaxQuantity] = useState<number|undefined>();
  const [price, setprice] = useState("10.00");

  useEffect(() => {
    setMaxQuantity(product.quantity)
    const getProductImages = async () => {
      await fetch(`/api/v1/product/${product.id}/imageUrls`, {
        method: "GET",
      })
        .then((res) => {
          if (res.status === 200) {
            return res.json();
          }
        })
        .then((data) => {
          const productImageUrls = data._embedded.productImages.map((imageApi) => imageApi.imageUrl);
          setproductImages(productImageUrls);
          setMainImage(productImageUrls[0]);
          
        })
        .catch((err) => {
          throw new Error("Cannot get product images");
        });
    };

    getProductImages();

    setLoading(false);
    return () => {
      setproductImages([]);
      setMainImage("");
    };
  }, []);

  // set main image on click on side images
  const setMainImageOnClick = (imageUrl: string) => {
    setMainImage(imageUrl);
  };

  return (
    <div className="--product-card --container-wrapper">
      <div className="--product-image-card">
        <div
          className="--product-main-image"
          style={{ backgroundImage: `url(${mainImage})` }}
        ></div>
        <div className="--product-nav-images">
          {
            <ImageSlide
              img_arr={productImages}
              setMainImageOnClick={setMainImageOnClick}
            ></ImageSlide>
          }
        </div>
      </div>
      <div className="--product-purchase-info">
        <div className="--product-info-name">{product.name}</div>
        {/* <Rating sold={product.sold} rating={product.rating}></Rating> */}
        <div className="--product-price">AUD {price}</div>
        <div className="--product-shipping"></div>
        <div className="--product-quantity">
          <div className="flex mt-5 mb-5 ms-0 items-center quantity-container">
            <ProductInfoLabel>Quantity</ProductInfoLabel>
            <QuantitySelect
              maxQuantity={maxQuantity}
              setQuantity={setQuantity}
            ></QuantitySelect>
            <p className="ms-5 mb-0">{maxQuantity} products available</p>
          </div>
        </div>
        <AddToCartButton></AddToCartButton>
        <BuyNowButton></BuyNowButton>
      </div>
    </div>
  );
}
