import React, { useEffect, useState } from "react";
import MainPageSection from "../MainPageSection";
import "./Category.scss"
export const Category = () => {
  const category_icon_path =
    process.env.PUBLIC_URL + "/img/Main/category_icon.png";
  type CategoryType = {
    icon_url: string;
    name: string;
    link: string;
  };
  const [categoryList, setCategoryList] = useState<CategoryType[] | null>(null);

  useEffect(() => {
    const fetchCategory = async () => {
      await fetch(
        `${process.env.REACT_APP_CATEGORY_API_LEVEL_1_PATH}${"/?level=2"}`,
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
              icon_url: category_icon_path,
              name: category.name,
              link: "category.link",
            };
            category_list = [...category_list, newCategory];
          });
          setCategoryList(category_list);
        })
        .catch((error) => {
          console.log("error when fetching categories: ", error);
        });
    };
    //   fetch fn
    fetchCategory();
  }, []);

  const category_tab = (img: string, name: string, link: string, index) => (
    <a href={`/${name}`} className="tab mx-auto flex flex-col" key={index}>
      <img src={img} alt="category-icon" className="category-icon" />
      <p className="category-name text-center pb-4">{name}</p>
    </a>
  );

  return (
    <MainPageSection section="Category">
      <div className="category flex grid grid-cols-2 sm:grid-cols-4 md:grid-cols-6 lg:grid-cols-8 xl:grid-cols-10 gap-0">
        {categoryList != null ? (Array.from(categoryList).map((category, index) =>
          category_tab(category.icon_url, category.name, category.link, index)
        )) : null}
      </div>
    </MainPageSection>
  );
};
