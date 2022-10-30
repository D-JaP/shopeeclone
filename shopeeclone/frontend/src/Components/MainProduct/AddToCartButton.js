import React from 'react';
import styled from 'styled-components';

function AddToCartButton() {
  const AddToCart = styled.button`
    color: var(--primary-color);
    font-size: 1rem;
    cursor: pointer;
    border-radius: 4px;
    height: 48px;
    padding: 0 20px;
    margin-right: 15px;
    border: 1px solid var(--primary-color);
    background-color: rgba(255, 87, 34, 0.1);
  `;
  return <AddToCart>Add To Cart</AddToCart>;
}

export default AddToCartButton;
