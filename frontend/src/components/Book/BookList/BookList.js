import React from 'react';
import BookItem from "../BookItem/BookItem"

const BookList = (props) => {
    const bookCards=props.books.map((book,index)=>{
        return (<div className="col" key={index}>#{index+1}<BookItem handleUnlike={props.handleUnlike} handleLike={props.handleLike} liked={book.liked} authenticated={props.authenticated} key={index} name={book.name} description={book.shortDescription} id={book.id} 
            author={book.authorName} cover={book.cover} genres={book.genreNames} stars={book.starRating} datePublished={book.datePublished} /></div>)
    });
    return (
        <div className="row row-cols-1 row-cols-sm-1 row-cols-md-2 justify-content-between mt-2">
                {bookCards}
        </div>
    );
};

export default BookList;