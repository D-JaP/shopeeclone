import React, { useState } from 'react'

function FeatureProduct() {
    const [productRecommend, setproductRecommend] = useState<ProductApiGetResponse[]>()
    useEffect(() => {
      const fetchProductWithCategory = async () => {
        await fetch("/api/v1/product")
      }
        
      return () => {
        
      }
    }, [])
    
  return (
    <div className='--feature-product'>
        <p>Top Feature Product</p>
        
    </div>
  )
}

export default FeatureProduct