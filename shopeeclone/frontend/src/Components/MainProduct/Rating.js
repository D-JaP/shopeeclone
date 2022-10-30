import React from 'react';
import styled from 'styled-components';
import Product from '../Product';
import RatingStar from './RatingStar';
export default function Rating(props) {
  const { rating, sold } = props;
  const VerticalBar = styled.div`
    height: 1.4rem;
    border-left: 1px solid rgb(166, 165, 165);
  `;
  const InfoBox = styled.div`
    padding: 0 1rem;
  `;

  return (
    <div className="--product-rating flex">
      <InfoBox>
        
        <RatingStar percent="80"></RatingStar>
      </InfoBox>
      <VerticalBar></VerticalBar>
      {/* number of rating */}
      <InfoBox>{rating} Ratings</InfoBox>
      <VerticalBar></VerticalBar>
      {/* number sold */}
      <InfoBox>{sold} Sold</InfoBox>
    </div>
  );
}
