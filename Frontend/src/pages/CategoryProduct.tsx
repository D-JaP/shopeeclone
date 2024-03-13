import React, { useEffect, useState } from "react";
import NavbarWithSearch from "../Components/Header/NavbarWithSearch";
import MainPageSection from "../Components/MainPageSection";
import './CategoryProduct.scss';
import Navbar from "../Components/Header/Navbar";
import CategoryProductFilter from "../Components/Category/CategoryProductFilter";
import { useParams } from "react-router-dom";
import { extractIdFromSlug } from "../utils/utils";
import axios from "axios";

function CategoryProduct() {
  const params = useParams();
  const { slug, slug2 } = params;


  const [productList, setproductList] = useState<ProductApiGetResponse[]>()
  const [categoryId, setcategoryId] = useState()
  const [categoryResponseData, setcategoryResponseData] = useState<CategoryGetApiResponse>()
  const [currentCategoryResponseData, setcurrentCategoryResponseData] = useState<CategoryGetApiResponse>()

  useEffect(() => {
    const slugArr = slug.includes('.') ? slug.split('.') : [slug]
    console.log(slugArr);
    
    const id = extractIdFromSlug(slugArr[0]);
    const id2 = slugArr.length == 2 ? extractIdFromSlug(slugArr[1]) : null

    const fetchProductData = async () => {
      try {
        const result = await axios.get(`/api/v1/product/search/findALlByCategoryLevel2?category_id=${id}`);
        if (result.status === 200) {
          setproductList(result.data._embedded.product)
          console.log(result.data._embedded.product);
          
        }
      }
      catch (err) {
        console.log(err)
      }
    }
    const fetchCategorytData = async (id:string, setState:Function) => {
      try {
        const result = await axios.get(`/api/v1/category/${id}`);
        if (result.status === 200) {
          setState(result.data)
          console.log(result.data);
        }
      } catch (err) {
        console.error(err);
      }
      
    }

    fetchCategorytData(id, setcategoryResponseData);
    fetchProductData();
    if (id2) fetchCategorytData(id2, setcurrentCategoryResponseData)
    
  }, [slug])


  return (
    <div role="main" className="--container-background ">
      <header className="App-header ">
        <Navbar />
        <NavbarWithSearch />
      </header>
      {categoryResponseData && productList && (
        <CategoryProductFilter categoryData={categoryResponseData} currentCategoryData = {currentCategoryResponseData} productList = {productList}></CategoryProductFilter>
      )}
    </div>
  );
}

export default CategoryProduct;
