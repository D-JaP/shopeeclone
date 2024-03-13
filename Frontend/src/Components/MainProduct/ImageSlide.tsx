import './ImageSlide.scss'
import React from 'react'
import styled from 'styled-components'

const SmallProductImage = styled.div`
  background-image: url(${(props) => props.backgroundImage});
  margin: 5px;
  width: 82px;
  height: 82px;
  background-repeat: no-repeat;
  background-size: contain;
  background-position-x: center;
  background-origin: content-box;
  &:hover {
    box-shadow: inset 0 0 0 1px #ee4d2d;
    transition: all 0.05s 0s;
  }
`;
function ImageSlide({img_arr, setMainImageOnClick}: {
    img_arr: string[],
    setMainImageOnClick: (imageUrl: string) => void 
}) {
    // static asset 
    const rightArrow = process.env.PUBLIC_URL+"/img/arrow/right-arrow.svg"
    

    const [startIndex, setStartIndex] = React.useState(0)
    const prevSlide = () => {
        setStartIndex((prevIndex) => Math.max(0, prevIndex - 1));
    }

    const nextSlide = () => {
        setStartIndex((prevIndex) => Math.min(img_arr.length - 5, prevIndex + 1));
    }


    return (
        <div className='product-image-slide'>
            <button onClick={prevSlide} className='nav-button-slider left' style={{backgroundImage:`url(${rightArrow})`}}></button>
            {img_arr && img_arr.slice(startIndex,startIndex+5).map((image, index) => { // Replace && with && and add return statement
                return (
                    <SmallProductImage
                        key={index}
                        backgroundImage={image}
                        onClick={() => setMainImageOnClick(image)} // Import the missing setMainImageOnClick function
                    ></SmallProductImage>
                );
            })}
            <button onClick={nextSlide} className='nav-button-slider right' style={{backgroundImage:`url(${rightArrow})`}}></button>
        </div>
    )
}

export default ImageSlide