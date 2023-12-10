import React, { useReducer } from 'react';
import './RecommendProduct.css';
// import data from './data';
import axios from 'axios';
import { useEffect } from 'react';
import Product from './Components/Product';
// import logger from 'use-reducer-logger';

const reducer = (state, action) => {
  switch (action.type) {
    case 'FETCH_REQUEST':
      return { ...state, loading: true };
    case 'FETCH_SUCCESS':
      return { ...state, products: action.payload, loading: false };
    case 'FETCH_FAIL':
      return { ...state, loading: false, error: action.payload };
    default:
      return state;
  }
};

function RecommendProduct() {
  const [{ loading, error, products }, dispatch] = useReducer(reducer, {
    products: [],
    loading: true,
    error: '',
  });
  // const [products, setProducts] = useState([]);
  useEffect(() => {
    const fetchData = async () => {
      dispatch({ type: 'FETCH_REQUEST' });
      try {
        const result = await axios.get(process.env.REACT_APP_PRODUCT_API_PATH);
        console.log(result);
        
        dispatch({ type: 'FETCH_SUCCESS', payload: await result.data});
      } catch (err) {
        dispatch({ type: 'FETCH_FAIL', payload: err.message });
      }

      // setProducts(result.data);
    };
    fetchData();
  }, []);

  return (
    <div className="--main-container">
      <div className="--container-wrapper">
        {/* Product header */}
        <div className="--stardust-tab-header">
          <div>DAILY DISCOVER</div>
          <div className="--stardust-tab-header-underline"></div>
        </div>

        {/* List of products */}
        <div className="--product-list">
          {loading ? (
            <div>Loading...</div>
          ) : error ? (
            <div>{error}</div>
          ) : (
            products.map((product, index) => (
              <Product product={product} key={index}></Product>
            ))
          )}
        </div>
      </div>
    </div>
  );
}

export default RecommendProduct;
