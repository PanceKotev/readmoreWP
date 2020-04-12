import React from 'react';
import BookCard from "../BookCard/BookCard"
import './BookCardCarousel.css'

const BookCardCarousel = (props) => {
    const allBooks= props.books.map((book,index)=>{
      let classNm= "";
      if(index=== 0){
        classNm="carousel-item active ";
      }
      else
        classNm="carousel-item"
      return (<div className={classNm.toString()}><div className="parent d-flex justify-content-center"><BookCard handleLike={props.handleLike} handleUnlike={props.handleUnlike} liked={book.liked} authenticated={props.authenticated} key={index} name={book.name} description={book.shortDescription} id={book.id} 
      author={book.authorName} cover={book.cover} genres={book.genreNames} stars={book.starRating} likes={book.nmbrLikes}/></div></div>)
    });
      let indicators=[];
      let tempInd=[];
      for (let i=0;i<props.books.length;i++){
        if(i=== 0){
          tempInd.push(<li className="active" data-target="#carouselExampleIndicators" data-slide-to={i.toString()}></li>)}
          else{
            tempInd.push(<li data-target="#carouselExampleIndicators" data-slide-to={i.toString()}></li>)
          }}
      indicators=[<ol className="carousel-indicators">{tempInd}</ol>]
      

    
    return (
        <div id="carouselExampleIndicators" className="carousel slide" data-interval="false" data-ride="carousel">
            {indicators}
        <div className="carousel-inner">
            {allBooks}
        </div>
        <a className="carousel-control-prev indicator" href="#carouselExampleIndicators" role="button" data-slide="prev">
          <span className="carousel-control-prev-icon" aria-hidden="true"></span>
          <span className="sr-only indicator">Previous</span>
        </a>
        <a className="carousel-control-next indicator" href="#carouselExampleIndicators" role="button" data-slide="next">
          <span className="carousel-control-next-icon" aria-hidden="true"></span>
          <span className="sr-only ">Next</span>
        </a>
      </div>
    );
};

export default BookCardCarousel;