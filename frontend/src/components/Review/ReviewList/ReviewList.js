import React from 'react';
import ReviewItem from '../ReviewItem/ReviewItem';

const ReviewList = (props) => {
    const reviews_list=props.reviews.map((review,index)=>{
        return <div key={index} className="w-100"> <ReviewItem handleDelete={props.handleReviewDelete} key={index} review={review}/><hr/></div>
    })
    return (
        <div className="d-flex row row-cols-1 w-75 ml-2 border-darken-1">
            {reviews_list}
        </div>
    );
};

export default ReviewList;