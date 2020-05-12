import React from 'react';
import BookItem from "../BookItem/BookItem"
import {useEffect,useState} from 'react'
import Auth from '../../../service/authService';

const BookList = (props) => {
    const [user,setUser] = useState({
        user:null
    });
    useEffect(()=>{
        Auth.getCurrentUser().then(data=>{
            setUser(data.data);
        }).catch(data=>{

        })

    },[])
    const bookCards=props.books.map((book,index)=>{
        return (<div className="col" key={index}>#{index+1}<BookItem user={user} handleUnlike={props.handleUnlike} handleDelete={props.handleDelete} handleLike={props.handleLike} liked={book.liked} authenticated={props.authenticated} key={index} name={book.name} description={book.shortDescription} id={book.id} 
            author={book.authorName} cover={book.cover} genres={book.genreNames} stars={book.starRating} datePublished={book.datePublished} /></div>)
    });
    return (
        <div className="row row-cols-1 row-cols-sm-1 row-cols-md-2 justify-content-between mt-2">
                {bookCards}
        </div>
    );
};

export default BookList;