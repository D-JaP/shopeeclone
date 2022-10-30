import React from 'react';
import styled from 'styled-components';
function BuyNowButton() {
  const BuyNow = styled.button`
    color: white;
    font-size: 1rem;
    cursor: pointer;
    border-radius: 4px;
    height: 48px;
    padding: 0 20px;
    border: 1px solid var(--primary-color);
    background-color: var(--primary-color);
  `;
  return <BuyNow>BuyNow</BuyNow>;
}

export default BuyNowButton;
