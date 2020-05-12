import React, { Component } from 'react';
import {Link, withRouter, Redirect} from 'react-router-dom';
import Rating from 'react-rating';
import BS from '../../../service/bookService';
import NotFound from '../../common/NotFound/NotFound';
import ServerError from '../../common/ServerError/ServerError';
import LoadingIndicator from '../../common/LoadingIndicator/LoadingIndicator';
import UServ from '../../../service/userService';
import ReviewList from '../../Review/ReviewList/ReviewList';
import RS from '../../../service/reviewService'
import BR from '../../../service/bookReviewService'
import ReviewAdd from '../../Review/ReviewAdd/ReviewAdd';
import {capitalize} from '../../../utils/stringUtils';
import { toast } from 'react-toastify';

class BookDetails extends Component {
    constructor(props){
        super(props);
        this.state={
            book:[],
            isLoading:false,
            notFound:false,
            serverError:false,
            reviews:[],
            tokenExpired:false,
            reviewExists:false
        }
        this.loadBook=this.loadBook.bind(this);
        this.bookUnlike=this.bookUnlike.bind(this);
        this.bookLike=this.bookLike.bind(this);
        this.handleReviewDelete=this.handleReviewDelete.bind(this);
        this.deleteBook = this.deleteBook.bind(this);
    }
    loadBook(bookName){
        this.setState({
            isLoading:true
        });
        BR.getBookAndReviews(bookName).then((data)=>{
            let exists = data[1].data.some(el=>el.reviewedBy===true);
            this.setState({
                book:data[0].data,
                reviews:data[1].data,
                isLoading:false,
                reviewExists:exists
            })

        }).catch(error=>{
            console.log(error);
            let code=error.message.slice(error.message.length-3).trim();
            if(code === '404') {
                this.setState({
                    isLoading:false,
                    notFound: true,
                });
            } else if(code === '401') {
                this.setState({
                    isLoading:false,
                    tokenExpired: true,
                }); }   
            else {
                this.setState({
                    isLoading:false,
                    serverError: true,
                });      
            }  
        });
    }
    bookLike(){
        UServ.likeBook(this.state.book.id).then((data)=>{
            this.setState({
                book:data.data
            })
        }).catch(error=>{
            console.log(error);
        })
    }
    bookUnlike(){
        UServ.unlikeBook(this.state.book.id).then((data)=>{
            this.setState({
                book:data.data
            })
        }).catch(error=>{
            console.log(error);
        })
    }
    handleReviewDelete(reviewId){
        RS.deleteReview(reviewId).then(data=>{
            toast.success("Review deleted.");
            this.loadBook(this.props.match.params.bookName);
        }).catch(data=>{
            toast.error("Error");
        })
       
        
    }
    deleteBook(){
        BS.deleteBook(this.state.book.id).then(data=>{
            toast.success("Book deleted.")
            this.props.history.push("/");
        }).catch(data=>{
            toast.error("Server error");
        })
    }
    componentDidMount(){
        let bookName=this.props.match.params.bookName;
        this.loadBook(bookName);
    }

    componentDidUpdate(nextProps){
        if(this.props.match.params.bookName!==nextProps.match.params.bookName){
            this.loadBook(nextProps.match.params.bookName);
           
        }
    }
    render() {
        if(this.state.tokenExpired){
           return <Redirect to={"/signin"}/>
        }
        if(this.state.isLoading && !this.state.book){
            return <LoadingIndicator/>
        }
        if(this.state.notFound){
            return <NotFound/>
        }
        if (this.state.serverError){
            return <ServerError/>
        }
        let genres=[];
        if(this.state.book.genreNames){
         genres= this.state.book.genreNames.map((genre,index)=>{
             if(index===this.state.book.genreNames.length-1){
            return (<Link className="pl-1 ml-1 mb-1" style={{color:'#212529'}} to={"/genre/"+genre}>{genre}</Link>);}
            else {
                return (<Link className="pl-1 ml-1 mb-1" style={{color:'#212529'}} to={"/genre/"+genre}>{genre+','}</Link>);
            }
        });}
        let series=[];
        if(this.state.book.seriesName!==""){
           
            series.push(<Link to={"/series/"+this.state.book.seriesName} className="" style={{color:'#212529'}}><i>{this.state.book.seriesName!==undefined?capitalize(this.state.book.seriesName):""}</i></Link>);
        }
        let likes=[];
        if(this.state.book.liked===true){
            likes=<div className="" onClick={this.bookUnlike} style={{color:'green',cursor:'pointer'}}><i class="fa fa-check"></i> Liked</div>
        }else{
            likes=<div className="" onClick={this.bookLike} style={{color:'red',cursor:'pointer'}}><i class="fa fa-times"></i> Liked</div>
        }
        return (
            <div className="d-block">
            <div className=" w-100 row mt-5 mx-auto col-12 pb-2">
            <div className="col-3 d-block">
                <img src={this.state.book.cover} className="img-fluid w-95 h-95"  alt={this.state.book.name}/>
                <span className="text-muted">{this.state.book.datePublished}</span>
                {likes}
            </div>
            <div className="col-7">
                <h3>{this.state.book.name!==undefined?capitalize(this.state.book.name):""}</h3>
                <div className="">{series}</div>
                <span className="text-muted">by <Link to={"/author/"+this.state.book.authorName} style={{color:'#7e758c'}}>{this.state.book.authorName!==undefined?capitalize(this.state.book.authorName):""}</Link></span>
                <div className="ratings d-flex">
                    <div className="col pl-0 "><Rating style={{ fontSize:'0.6vw',color:'#212529'}} readonly initialRating={this.state.book.starRating} emptySymbol="fa fa-star-o fa-2x" fullSymbol="fa fa-star fa-2x" fractions={2}/></div>
                    <div className="col"><i>likes : {this.state.book.nmbrLikes}</i></div>
                    <div className="col"><i>reviews : {this.state.book.nmbrReviews}</i></div>
                    <div className="col"></div>
                    <div className="col"></div>
                </div>
                <p className="">{this.state.book.shortDescription}</p>
                <hr/>
                <div className="genres d-flex">{genres}</div>
                <a href={this.state.book.downloadList} type="button" className="btn btn-sm btn-light">Download</a>
                {this.props.user.roleName==="ROLE_ADMIN"?<button className="btn btn-sm btn-danger d-block mt-1" onClick={(e) => { if (window.confirm('Delete book??')) this.deleteBook()} }>Delete</button>:""}
                
            </div>
        </div>
        <hr/>
        <div className="bookReviews d-block mt-5">
           {this.state.reviewExists!==true?<ReviewAdd bookName={this.state.book.name} reloadBook={this.loadBook} bookId={this.state.book.id}/>:""}
            <ReviewList reviews={this.state.reviews} handleReviewDelete={this.handleReviewDelete} userRole={this.props.user.roleName} />
        </div>
        </div>
        );
    }
}

export default withRouter(BookDetails);