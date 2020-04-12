import React from 'react';
import ReviewItem from '../ReviewItem/ReviewItem';

const ReviewList = (props) => {
    const reviews_list=props.reviews.map((review,index)=>{
        return <div key={index} className="w-100"> <ReviewItem key={index} review={review}/><hr/></div>
    })
    return (
        <div className="d-flex row row-cols-1 w-75 mx-auto border-darken-1">
            {reviews_list}
        </div>
    );
};

export default ReviewList;