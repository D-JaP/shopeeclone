import React, { useEffect, useState } from "react";
import MainPageSection from "../MainPageSection";
import "./Category.scss"
import { chunkArray, convertToSlug } from "../../utils/utils";
import { Carousel } from "react-bootstrap";
export const Category = () => {
  const category_icon_path =
    process.env.PUBLIC_URL + "/img/Main/category_icon.png";
  type CategoryType = {
    id:number;
    icon_url: string;
    name: string;
    link: string;
  };
  const [categoryList, setCategoryList] = useState<CategoryType[] | null>(null);
  const [categoryChunk, setCategoryChunk] = useState<CategoryType[][]>();

  useEffect(() => {
    const fetchCategory = async () => {
      await fetch(
        `${process.env.REACT_APP_CATEGORY_API_LEVEL_1_PATH}${"/?level=2&page=0&size=25"}`,
        {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Access-Control-Allow-Method": "GET",
          },
        }
      )
        .then(async (response) => {
          if (!response.ok) {
            throw new Error(
              "Fetch failed with network error. Cannot connect to endpoint API"
            );
          } else {
            return await response.json();
          }
        })
        .then((result) => {
          console.log(result);

          const categories = result._embedded.category;
          let category_list: CategoryType[] = [];
          categories.forEach((category) => {
            let newCategory: CategoryType = {
              id: category.id,
              icon_url: category_icon_path,
              name: category.name,
              link: "category.link",
            };
            category_list = [...category_list, newCategory];
          });
          setCategoryList(category_list);
          setCategoryChunk(chunkArray(category_list, 10))
        })
        .catch((error) => {
          console.log("error when fetching categories: ", error);
        });
    };
    //   fetch fn
    fetchCategory();
  }, []);

  const category_tab = (id:number, img: string, name: string, link: string, index) => (
    <a href={`/category/${convertToSlug(name)+"-" +id}`} className="tab mx-auto flex flex-col text-decoration-none" key={index}>
      <img src={img} alt="category-icon" className="category-icon" />
      <p className="category-name text-center mb-4">{name}</p>
    </a>
  );

  return (
    <MainPageSection section="Category">
      <Carousel interval={null} wrap={false}>
        {
          categoryChunk && categoryChunk.map((category, index) => (
            <Carousel.Item key={index}>
              <div className="category flex md:grid md:grid-cols-10 gap-0 md:grid-rows-1">
                {categoryList != null ? (Array.from(category).map((category, index) =>
                  category_tab(category.id,category.icon_url, category.name, category.link, index)
                )) : null}
              </div>
            </Carousel.Item>
          ))
        }
      </Carousel>
    </MainPageSection>
  );
};
