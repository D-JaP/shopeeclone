import React, { useEffect, useState } from 'react'
import "./FeatureProduct.scss"
import ProductTab from '../ProductTab/ProductTab'

function FeatureProduct({category_id}:{category_id: number}) {
    const [productRecommend, setproductRecommend] = useState<ProductApiGetResponse[]|undefined>()

    // get product with same category
    useEffect(() => {
      const fetchProductWithCategory = async () => {
        await fetch(`/api/v1/product/search/byCategory?category_id=${category_id}&size=5&page=0`)
        .then((response) => {
            if (!response.ok) {
                throw new Error("Network failed.");
            } else {
                return response.json();
            }
        })
        .then((data) => {
            setproductRecommend(data._embedded.product);
            console.log(data._embedded.product);
            
        })
      }
      fetchProductWithCategory()

      return () => {
        
      }
    }, [])
    
  return (
    <div className='--feature-product'>
        <p className='--feature-product-header'>Top Feature Product</p>
        <div>
        {productRecommend && (
            productRecommend.map((product:ProductApiGetResponse, index) => {
                return <ProductTab name={product.name} id={product.id} price= {product.price} img={product.imageUrls} key={index}></ProductTab>
            })
        )}
        </div>
    </div>
  )
}

export default FeatureProduct