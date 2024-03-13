import './CategoryProductFilter.scss'
import React, { useEffect, useState } from 'react'
import './CategoryProductFilter.scss'
import { useParams } from 'react-router-dom';

function CategoryProductFilter({categoryId} : {categoryId:number}) {
    const [Categories, setCategories] = useState([])
    const [currentCategory, setcurrentCategory] = useState();
    const {params} = useParams();

    // set current category
    useEffect(() => {
        
        
      setcurrentCategory('Category 1')
    
      return () => {
        second
      }
    }, [third])
    

  return (
    <div className='md:container mx-auto --category-product-filter'>
        <div className='flex --container-wrapper'>
            <div className='--filter-selection'>
                <p className='text-bold'>All Categories</p>
                <div className='--filter-group'>
                    <ul className='ps-2 text-bold'>
                        <li>{currentCategory}</li>
                        <li>Category 1</li>
                        <li>Category 2</li>
                        <li>Category 3</li>
                    </ul>
                </div>
                <p className='text-bold'>Price range</p>
                {/* input type min max */}
                <div className='flex flex-wrap items-center'>
                    <input type="text" placeholder='$ MIN' className='input-price-range' min={0}/>
                    <div className='place'></div>
                    <input type="text" placeholder='$ MAX' className='input-price-range' min={0} max={10000}/>
                    <button className='--button-filter mt-2 mb-2 flex-grow-1 '>Apply</button>
                </div>


                <div className='--filter-group flex'>
                    <button className='--button-filter flex-grow-1 mt-2 mb-2 '>Clear All</button>
                </div>
            </div>
            <div className='--product-filtered'>

            </div>
        </div>
    </div>
  )
}

export default CategoryProductFilter