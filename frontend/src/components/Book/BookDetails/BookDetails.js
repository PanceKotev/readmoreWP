import React from 'react';

const BookDetails = (props) => {
   let series=[];
   if(props.book.seriesName!==null){
        series= <div className="text-muted">{props.book.seriesName}</div>
    }else series=<div className="text-muted">go from</div>
    return (
        <div className="d-flex w-100 row mt-5 mx-auto">
            <div className="col-3">
                <img src={props.book.cover} className="img-fluid w-100 h-100" alt={props.book.name}/>
                <span className="text-muted">{props.book.datePublished}</span>
                <div className="">Like</div>
                <div className="">Review</div>
            </div>
            <div className="col-7">
                <h3>{props.book.name}</h3>
                <span className="text-muted">by {props.book.authorName}</span>
                <div className="ratings d-flex"></div>
                <p className="">{props.book.shortDescription}</p>
                <hr/>
                <div className="genres d-flex">{props.book.genreNames}</div>
                {series}
                <a href={props.book.downloadList} type="button" className="btn btn-sm btn-light">Download</a>
            </div>
        </div>
    );
};

export default BookDetails;