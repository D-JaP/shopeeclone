import React, { useState } from "react";
import { useParams } from "react-router-dom";
import MainProductCard from "../Components/MainProduct/MainProductCard";
import { useReducer, useEffect } from "react";
import axios from "axios";
import "./ProductPage.scss";
import { productApiGet } from "../mock/productApiMock";
import Navbar from "../Components/Header/Navbar";
import NavbarWithSearch from "../Components/Header/NavbarWithSearch";
import ProductDescription from "../Components/ProductPageComponent/ProductDescription";
import FeatureProduct from "../Components/ProductPageComponent/FeatureProduct";

function extractIdFromSlug(slug) {
  // split slug with the "-" at the end of the string, take the number after it
  const id = slug.split("-").pop();
  return id;
}

function ProductPage() {
  const params = useParams();
  const { slug } = params;
  const [productGetApi, setProduct] = useState<
    ProductApiGetResponse | undefined
  >();

  useEffect(() => {
    const fetchData = async () => {
      try {
        const result = await axios.get(
          `/api/v1/product/${
            extractIdFromSlug(slug) + "?projection=productProjection"
          }`
        );
        if (result.status === 200) {
          setProduct(result.data);
        }
      } catch (err) {
        console.error(err);
      }
    };
    fetchData();
  }, [slug]);

  return (
    <div role="main" className="--container-background ">
      <header className="App-header ">
        <Navbar />
        <NavbarWithSearch />
      </header>
      {productGetApi && (
        <div className="md:container mx-auto">
          {/* Main product page */}
          <MainProductCard product={productGetApi}></MainProductCard>

          {/* shop info */}

          {/* Product spec */}
          <div className="flex --container-wrapper mt-10">
            <ProductDescription
              id={productGetApi.id}
              description={productGetApi.description}
            ></ProductDescription>
            <FeatureProduct
              category_id={productGetApi.categoryId}
            ></FeatureProduct>
          </div>

          {/* User reviews */}

          {/* Product from the same shop */}
        </div>
      )}
    </div>
  );
}

export default ProductPage;
