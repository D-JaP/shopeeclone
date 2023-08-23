import React from 'react';
import styled from 'styled-components';

const StarWhite = styled.div`
  z-index: 2;
  position: relative;
  background-color: white;
  width: 10px;
  height: 1rem;
  left: -5px;
  margin-right: 3px;
`;
const StarBorder = styled.div`
  position: absolute;
  stroke-width: 1px;
  stroke: var(--primary-color);
  z-index: 4;
`;
export default function RatingStar(props) {
  const { percent } = props;
  return (
    <div className="flex">
      <div
        className="fa fa-star"
        style={{ color: 'var(--primary-color)' }}
      ></div>
      <StarWhite></StarWhite>
      <StarBorder className="fa fa-star"></StarBorder>
    </div>
  );
}
