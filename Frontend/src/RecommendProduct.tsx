import React, { useEffect, useState } from "react";
import "./RecommendProduct.scss";

import ProductTab from "./Components/ProductTab/ProductTab";

function RecommendProduct() {
  type product = {
    name: string;
    price: number;
    description: string;
    seller: number | null;
    _links: {
      [key: string]: {
        href: string;
      };
    };
  };

  const [producttabList, setproducttabList] = useState<product[] | null>([]);
  const size = 24;
  const [page, setpage] = useState(0);

  useEffect(() => {
    const fetchProduct = async () => {
      await fetch(
        process.env.REACT_APP_PRODUCT_API_PATH + `/?size=${size}&page=${page}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Method": "GET",
          },
        }
      )
        .then((response) => {
          if (!response.ok) {
            throw new Error("Network failed.");
          } else {
            return response.json();
          }
        })
        .then((data) => {
          setproducttabList([...producttabList,...data._embedded.product]);
        })
    };

    fetchProduct();
  }, [page]);

  const loadmoreHandle = async () => {
    setpage(page + 1);
  };

  useEffect(() => {
    console.log(producttabList[0]);

    return () => {};
  }, [producttabList]);

  return (
    <div className="--main-container">
      <div className="--container-wrapper">
        {/* Product header */}
        <div className="--stardust-tab-header">
          <div>DAILY DISCOVER</div>
          <div className="--stardust-tab-header-underline"></div>
        </div>
        {/* List product */}
        <div className="product-list mt-2 grid grid-col-2 sm:grid-cols-4 md:grid-cols-6 lg:grid-cols-6 xl:grid-cols-6 gap-3">
          {producttabList.map((product, index) => (
            <ProductTab
              name={product["name"]}
              price={product["price"]}
              img={product["imageUrls"]}
              id = {product["id"]}
              key={index}
            ></ProductTab>
          ))}
        </div>
        <div
          className="btn bg-gray-500 hover:bg-gray-400 rounded loadmore-btn mt-5 mx-auto d-flex"
          onClick={loadmoreHandle}
        >
          Loadmore
        </div>
      </div>
    </div>
  );
}

export default RecommendProduct;
