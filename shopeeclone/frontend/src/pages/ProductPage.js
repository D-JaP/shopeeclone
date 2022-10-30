import React from 'react';
import { useParams } from 'react-router-dom';
import MainProductCard from '../Components/MainProduct/MainProductCard';
import { useReducer, useEffect } from 'react';
import axios from 'axios';
import './ProductPage.css';
const reducer = (state, action) => {
  switch (action.type) {
    case 'FETCH_REQUEST':
      return { ...state, loading: true };
    case 'FETCH_SUCCESS':
      return { ...state, product: action.payload, loading: false };
    case 'FETCH_FAIL':
      return { ...state, loading: false, error: action.payload };
    default:
      return state;
  }
};
function ProductPage() {
  const params = useParams();
  const { slug } = params;

  const [{ loading, error, product }, dispatch] = useReducer(reducer, {
    product: [],
    loading: true,
    error: '',
  });

  useEffect(() => {
    const fetchData = async () => {
      dispatch({ type: 'FETCH_REQUEST' });
      try {
        const result = await axios.get(`/api/product/${slug}`);
        dispatch({ type: 'FETCH_SUCCESS', payload: result.data });
      } catch (err) {
        dispatch({ type: 'FETCH_FAIL', payload: err.message });
      }
    };
    fetchData();
  }, [slug]);
  return loading ? (
    <div>Loading...</div>
  ) : error ? (
    <div>{error}</div>
  ) : (
    <div role="main" className="--container-background">
      {/* Main product page */}
      <MainProductCard product={product}></MainProductCard>

      {/* shop info */}

      {/* Product spec */}

      {/* User reviews */}

      {/* Product from the same shop */}
    </div>
  );
}

export default ProductPage;
