import { changeLinkHostname, convertToSlug } from "../../utils/utils";
import "./ProductDescription.scss";
import React, { useEffect, useState } from "react";

interface ProductAttributeValue {
  name: string;
  value: string;
}

interface ProductAttributeValueResponse {
  value: string;
  id: number;
  type: string;
  attribute: {
    name: string;
    _link: object;
  };
}

function ProductDescription({
  id,
  description,
}: {
  id: number;
  description: string;
}) {
  const [productAttribute, setproductAttribute] = useState<
    ProductAttributeValue[]
  >([]);

  // category level state 
  const [category_level1, setCategoryLevel1] = useState<string>();
  const [category_level2, setCategoryLevel2] = useState<string>();
  const [category_level3, setCategoryLevel3] = useState<string>();
  const [category_level1_link, setCategoryLevel1Link] = useState<string>("/");
  const [category_level2_link, setCategoryLevel2Link] = useState<string>("/");
  const [category_level3_link, setCategoryLevel3Link] = useState<string>("/");

  useEffect(() => {
    const getCategory = async () => {
      const result = await fetch(`/api/v1/product/${id}/category`);
      const data = await result.json();
      
      const result2 = await fetch(changeLinkHostname(data._links.parentId.href));
      const data2 = await result2.json();
      
      const result3 = await fetch(changeLinkHostname(data2._links.parentId.href));
      const data3 = await result3.json();


      setCategoryLevel3(data.name);
      setCategoryLevel3Link(`/category/` + convertToSlug(data2.name + ' ' + data2.id) + '.'+ convertToSlug(data.name + " " + data.id))
      setCategoryLevel2(data2.name);
      setCategoryLevel2Link(`/category/` + convertToSlug(data2.name + " " + data2.id))
      setCategoryLevel1(data3.name);
      setCategoryLevel1Link(`/category/` + convertToSlug(data3.name + " " + data3.id))
    }

    getCategory()

  }, [])


  // side effect to fetch attribute value
  useEffect(() => {
    const getAttribute = async () => {
      const result = await fetch(`/api/v1/product/${id}/attribute_value`);
      const data = await result.json();
      let attributeValue = [];
      data._embedded.attribute_value.map(
        (attribute_value: ProductAttributeValueResponse) => {
          attributeValue.push({
            name: attribute_value.attribute.name,
            value: attribute_value.value,
          });
        }
      );
      setproductAttribute(attributeValue);
    };

    getAttribute();
  }, []);

  return (
    <div className="product-description">
      <h2>Product details</h2>
      <div className="md:p-3">
        <div className="flex">
          <label className="attribute-label">Category</label>
          <p className="attribute-category">
            <a href="/" className="category-url">Shopee</a> <span className="--category-arrow">&gt;</span>{" "}
            {/* <a href={category_level1_link} className="category-url">{category_level1}</a> <span className="--category-arrow">&gt;</span>{" "} */}
            <a href={category_level2_link} className="category-url">{category_level2}</a> <span className="--category-arrow">&gt;</span>{" "}
            <a href={category_level3_link} className="category-url">{category_level3}</a>
          </p>
        </div>

        {productAttribute.map((attribute, index) => {
          return (
            <div key={index} className="flex">
              <label className="attribute-label">{attribute.name}</label>
              <p className="attribute-value">{attribute.value}</p>
            </div>
          );
        })}
      </div>

      <h2>Product description</h2>

      <p className="md:p-3">
        {description}
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Maxime
        mollitia, molestiae quas vel sint commodi repudiandae consequuntur
        voluptatum laborum numquam blanditiis harum quisquam eius sed odit
        fugiat iusto fuga praesentium optio, eaque rerum! Provident similique
        accusantium nemo autem. Veritatis obcaecati tenetur iure eius earum ut
        molestias architecto voluptate aliquam nihil, eveniet aliquid culpa
        officia aut! Impedit sit sunt quaerat, odit, tenetur error, harum
        nesciunt ipsum debitis quas aliquid. Reprehenderit, quia. Quo neque
        error repudiandae fuga? Ipsa laudantium molestias eos sapiente officiis
        modi at sunt excepturi expedita sint? Sed quibusdam recusandae alias
        error harum maxime adipisci amet laborum. Perspiciatis minima nesciunt
        dolorem! Officiis iure rerum voluptates a cumque velit quibusdam sed
        amet tempora. Sit laborum ab, eius fugit doloribus tenetur fugiat,
        temporibus enim commodi iusto libero magni deleniti quod quam
        consequuntur! Commodi minima excepturi repudiandae velit hic maxime
        doloremque. Quaerat provident commodi consectetur veniam similique ad
        earum omnis ipsum saepe, voluptas, hic voluptates pariatur est explicabo
        fugiat, dolorum eligendi quam cupiditate excepturi mollitia maiores
        labore suscipit quas? Nulla, placeat. Voluptatem quaerat non architecto
        ab laudantium modi minima sunt esse temporibus sint culpa, recusandae
        aliquam numquam totam ratione voluptas quod exercitationem fuga.
        Possimus quis earum veniam quasi aliquam eligendi, placeat qui corporis!
      </p>
    </div>
  );
}

export default ProductDescription;
