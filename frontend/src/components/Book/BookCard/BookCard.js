import React from 'react';
import './BookCard.css'
import Rating from 'react-rating'
const BookCard = (props) => {
    const genreButtons= props.genres.map((genre,index)=>{
        return (<button type="button" class="btn btn-info btn-sm mr-2">{genre.toString().charAt(0).toUpperCase()+genre.toString().slice(1)}</button>)
    })
    return (
        <div className="card mb-3" style={{width:"45vw", height:"60vh"}}>
         <div className="row no-gutters">
            <div className="col-md-5">
                 <img src={props.cover} className="card-img h-100 w-100" alt={props.name}/>
            </div>
            <div className="col-md-7">
            <div className="card-body bookCard-body">
                <h5 className="card-title">{props.name.toString().charAt(0).toUpperCase()+props.name.toString().slice(1)}</h5>
                <p className="card-text">{props.description.toString().charAt(0).toUpperCase()+props.description.toString().slice(1)}</p>
                <p className="card-text">{genreButtons} </p>
                <p className="card-text"><small className="text-muted">{props.author.toString().charAt(0).toUpperCase()+props.author.toString().slice(1)}</small></p>
                <p className="card-text d-flex"><Rating style={{cursor:'pointer', fontSize:'2.5vh'}} className="mr-auto" readonly initialRating={props.stars} emptySymbol="fa fa-star-o fa-2x" fullSymbol="fa fa-star fa-2x" fractions={2}/>
                <i className="fa fa-heart" style={{fontSize:'4.5vh'}}></i></p>
            </div>
            </div>
        </div>
        </div>
    );
};

export default BookCard;