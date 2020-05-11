import React from 'react';
import Rating from 'react-rating';
import avatar from '../../../Images/avatar.png'
import { Link } from 'react-router-dom';
import './ReviewItem.css'
import {capitalize} from '../../../utils/stringUtils'

const ReviewItem = (props) => {
    const deleteReview=(choice)=>{
        if(choice){
            props.handleDelete(props.review.id);}
        else{

        }
    }
    return (
        <div className="d-flex w-100 row my-2 mx-auto">
            <div className="col-1">
            
                <img src={avatar} className="img-fluid w-60 h-60 d-inline-block" width="75px" height="75px" alt={props.review.userName}/>
                
            </div>
            <div className="col-10 pl-0 my-auto">
             <div className="rating">
             <b ><Link to={"/users/"+props.review.userName} className="link-no-decor">{props.review.userName}</Link></b>  rated <Link to={"/book/"+props.review.bookName} className="link-no-decor">{props.review.bookName!=='undefined'?capitalize(props.review.bookName):''}</Link>  <Rating style={{ fontSize:'0.6vw',color:'#212529'}} readonly initialRating={props.review.rating} emptySymbol="fa fa-star-o fa-2x" fullSymbol="fa fa-star fa-2x" fractions={2}/>
            </div>
            <p className="my-2">
                {props.review.summary}
            </p>
            <p>
               {props.review.reviewedBy===true?<button className="btn btn-sm btn-danger" onClick={(e) => { if (window.confirm('Delete review??')) deleteReview(true)}}>delete</button>:""}
            </p>
        </div>
    </div>
    );
};

export default ReviewItem;