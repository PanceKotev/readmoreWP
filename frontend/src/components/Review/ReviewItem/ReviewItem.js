import React from 'react';
import Rating from 'react-rating';
import avatar from '../../../Images/avatar.png'

const ReviewItem = (props) => {
    return (
        <div className="d-flex w-100 row my-2 mx-auto">
            <div className="col-2">
                <img src={avatar} className="img-fluid w-75 h-75" alt={props.review.userName}/>
            </div>
            <div className="col-10">
             <div className="rating d-flex"/>
                <b>{props.review.userName}</b> rated {props.review.bookName}  <Rating style={{ fontSize:'0.6vw',color:'#212529'}} readonly initialRating={props.review.rating} emptySymbol="fa fa-star-o fa-2x" fullSymbol="fa fa-star fa-2x" fractions={2}/>
            </div>
            <p className="">
                {props.review.summary}
            </p>
        </div>
    );
};

export default ReviewItem;