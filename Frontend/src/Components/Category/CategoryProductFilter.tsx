import './CategoryProductFilter.scss'
import React, { useEffect, useRef, useState } from 'react'
import { useLocation, useParams } from 'react-router-dom';
import axios from 'axios';
import { convertToSlug } from '../../utils/utils';
import ProductTab from '../ProductTab/ProductTab';

function CategoryProductFilter({ categoryData, currentCategoryData, productList }: { categoryData: CategoryGetApiResponse, currentCategoryData: CategoryGetApiResponse, productList: ProductApiGetResponse[] }) {
    // 
    const location = useLocation();
    const params = useParams();
    const inputMinRef = useRef(null)
    const inputMaxRef = useRef(null)
    // related category var
    const [categories, setCategories] = useState<CategoryGetApiResponse[]>()
    // product list
    const [productListState, setproductList] = useState<ProductApiGetResponse[]>(productList)
    // current category props
    const [currentParentCategory, setcurrentParentCategory] = useState<CategoryGetApiResponse>();
    const [joinedCategories, setjoinedCategories] = useState<CategoryGetApiResponse[]>()
    // button filter state
    const [isFeatureBtnSelected, setisFeatureBtnSelected] = useState(true)
    const [isHighToLowBtnSelected, setisHighToLowBtnSelected] = useState(false)
    const [isLowToHighSelected, setisLowToHighSelected] = useState(false)
    // set current category
    useEffect(() => {
        console.log(productListState);

        if (categoryData) {
            setcurrentParentCategory(categoryData)
        }

        return () => {

        }
    }, [])
    // update related categories
    useEffect(() => {
        const fetchData = async () => {
            try {
                const category = await axios.get(`/api/v1/category/search/getCategoriesByParentId?parent_id=${categoryData.id}`)

                if (category.status === 200) {
                    setCategories(category.data._embedded.category)
                    setjoinedCategories([categoryData, ...category.data._embedded.category])
                }
            }
            catch (err) {
                console.log(err)
            }
        }

        fetchData();

        return () => {

        }
    }, [])

    // some css side effect 
    useEffect(() => {
        // if (currentCategoryData.id == categoryData.id) {
        // }
        return () => {
        }
    }, [])

    // handle click on categoryList
    const handleClickOnCategoryList = (event) => {
        // event.preventDefault()
        // prevent default

    }

    const [minPrice, setminPrice] = useState(0)
    const [maxPrice, setmaxPrice] = useState(Number.MAX_SAFE_INTEGER)
    // price range btn handler
    const priceRangeHandler = () => {
        const filtered = productList.filter((product) => {
            return product.price >= minPrice && product.price <= maxPrice
        })
        setproductList(filtered)
    }

    const clearAllFilter = () => {
        setproductList(productList)
        setisFeatureBtnSelected(true)
        setisHighToLowBtnSelected(false)
        setisLowToHighSelected(false)
        setminPrice(0)
        setmaxPrice(Number.MAX_SAFE_INTEGER)
        inputMinRef.current.value = ''
        inputMaxRef.current.value = ''
    }

    const handleMinPriceChange = (event) => {
        setminPrice(parseFloat(event.target.value))
        console.log("minPrice changed:" + parseFloat(event.target.value));

    }

    const handleMaxPriceChange = (event) => {
        setmaxPrice(parseFloat(event.target.value))
        console.log("max price changed:" + event.target.value)
    }


    // sort product high to low
    const sortProductHighToLow = () => {
        const sorted = productListState.sort((a, b) => { return b.price - a.price })
        setproductList([...sorted])
        setisHighToLowBtnSelected(true)
        setisLowToHighSelected(false)
        setisFeatureBtnSelected(false)
    }
    // sort product low to high
    const sortProductLowToHigh = () => {
        const sorted = productListState.sort((a, b) => { return a.price - b.price })
        setproductList([...sorted])
        setisLowToHighSelected(true)
        setisHighToLowBtnSelected(false)
        setisFeatureBtnSelected(false)
    }
    const featureProduct = () => {
        setproductList(productList)
        setisFeatureBtnSelected(true)
        setisHighToLowBtnSelected(false)
        setisLowToHighSelected(false)
    }

    return (
        <div className='md:container mx-auto --category-product-filter'>
            <div className='flex --container-wrapper'>
                <div className='--filter-selection'>
                    <p className='text-bold'>All Categories</p>
                    <div className='--filter-group'>
                        <ul className='ps-2 text-bold'>
                            {
                                categoryData && joinedCategories && joinedCategories.map((category, index) => (
                                    <li key={index} onClick={(e) => handleClickOnCategoryList(e)} className='sub-category'>
                                        <a
                                            href={`/category/${convertToSlug(categoryData.name + ' ' + categoryData.id)}${(category.id !== categoryData.id) ? ('.' + convertToSlug(category.name) + '-' + category.id) : ''}`}
                                        >
                                            {category.name}
                                        </a>
                                    </li>
                                ))
                            }
                        </ul>
                    </div>
                    <p className='--filter-group text-bold'>Price range</p>
                    {/* input type min max */}
                    <div className='flex flex-wrap items-center'>
                        <input type="text" placeholder='$ MIN' className='input-price-range' min={0} onChange={handleMinPriceChange} ref={inputMinRef}/>
                        <div className='place'></div>
                        <input type="text" placeholder='$ MAX' className='input-price-range' min={0} max={100000} onChange={handleMaxPriceChange} ref= {inputMaxRef}/>
                        <button className='--button-filter mt-3 mb-3 flex-grow-1 ' onClick={priceRangeHandler}>Apply</button>
                    </div>

                    <div className='--filter-group flex'>
                        <button className='--button-filter flex-grow-1 mt-3 mb-3 ' onClick={clearAllFilter}>Clear All</button>
                    </div>
                </div>
                <div className='--product-filtered ms-5'>
                    <div className='filter-header my-2'>
                        Sort by:
                        <button className={'--button-filter mx-2 px-2 py-1 ' + (isFeatureBtnSelected ? "selected" : "not-selected")} onClick={featureProduct}>Featured</button>
                        <button className={`--button-filter mx-2 px-2 py-1 ` + (isHighToLowBtnSelected ? "selected" : "not-selected")} onClick={sortProductHighToLow}>High to Low</button>
                        <button className={`--button-filter mx-2 px-2 py-1 ` + (isLowToHighSelected ? "selected" : "not-selected")} onClick={sortProductLowToHigh}>Low to High</button>
                    </div>
                    <div className="product-list mt-3 grid grid-col-2 sm:grid-cols-4 md:grid-cols-5 lg:grid-cols-5 xl:grid-cols-5 gap-2">
                        {productListState && productListState.map((product, index) => (
                            <ProductTab
                                name={product["name"]}
                                price={product["price"]}
                                img={product["imageUrls"]}
                                id={product["id"]}
                                key={index}
                            ></ProductTab>
                        ))}
                        {productListState.length == 0 && `No test data for this category`}
                    </div>
                </div>
            </div>
        </div>
    )
}

export default CategoryProductFilter