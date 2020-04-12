import React from 'react';
import './BookItem.css'
import Rating from 'react-rating'
import {Link, withRouter} from 'react-router-dom'
import UServ from "../../../service/userService"
const BookItem = (props) => {
    const genreButtons= props.genres.map((genre,index)=>{
        return (<Link to={"/genre/"+genre} type="button" className="btn btn-info btn-sm mr-1 mb-1 p-1 genre">{genre.toString().charAt(0).toUpperCase()+genre.toString().slice(1)}</Link>)
    });
    let likeButton=[];
    if (!props.authenticated){
       likeButton= <i className="fa fa-heart" style={{fontSize:'1.8vw'}}></i>
    }
    else if(props.liked === true){
        likeButton= <i className="fa fa-heart" style={{fontSize:'1.8vw',color:'#235952',cursor:'pointer'}} onClick={()=>props.handleUnlike(props.id)}></i>
    }
    else if(props.liked === false){
        likeButton= <i className="fa fa-heart" style={{fontSize:'1.8vw',cursor:'pointer'}} onClick={()=>props.handleLike(props.id)}></i>
    }

    return (
        <div className="card mb-3" style={{minWidth:"28vw", minHeight:"30vh", maxHeight:"60vh"}}>
         <div className="row no-gutters">
            <div className="col-md-5">
                <img src={props.cover} className="card-img h-100 w-100 img-fluid" alt={props.name}/>
            </div>
            <div className="col-md-7">
            <div className="card-body bookCard-body">
                <h5 className="card-title">{props.name.toString().charAt(0).toUpperCase()+props.name.toString().slice(1)}</h5>
                <p className="card-text">{props.description.toString().charAt(0).toUpperCase()+props.description.toString().slice(1)}</p>
                <p className="card-text">{genreButtons} </p>
                <p className="card-text"><Link to={"/author/"+props.author} className="text-muted">{props.author.toString().charAt(0).toUpperCase()+props.author.toString().slice(1)}</Link></p>
                <p className="card-text d-flex align-content-between"><Rating style={{cursor:'pointer', fontSize:'1vw'}} className="mr-auto" readonly initialRating={props.stars} emptySymbol="fa fa-star-o fa-2x" fullSymbol="fa fa-star fa-2x" fractions={2}/>
                {likeButton}</p>
                <p className="card-text"><small className="text-muted">{props.datePublished}</small></p>
            </div>
            </div>
        </div>
        </div>
    );
};

export default withRouter(BookItem);