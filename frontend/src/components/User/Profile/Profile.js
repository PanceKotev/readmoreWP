import React, { Component } from 'react';
import UServ from "../../../service/userService";
import NotFound from "../../common/NotFound/NotFound";
import ServerError from "../../common/ServerError/ServerError";
import {withRouter, Redirect} from 'react-router-dom';
import LoadingIndicator from '../../common/LoadingIndicator/LoadingIndicator';
import BookList from '../../Book/BookList/BookList';
import ReviewList from '../../Review/ReviewList/ReviewList';

class Profile extends Component {
    constructor(props){
        super(props);
        this.state={
            user: null,
            reviews:[],
            isLoading:false,
            books:[],
            expiredToken:false,
            notFound: false,
            serverError: false
        };
        this.loadUserProfile=this.loadUserProfile.bind(this);
        this.bookUnlike=this.bookUnlike.bind(this);
        
    };
    loadUserProfile(username){
        this.setState({
            isLoading:true
        })
        UServ.getProfile(username).then(
            response=>{
                this.setState({
                    user:response.data,
                    books:response.data.likedBooks,
                    reviews:response.data.reviews,
                    isLoading:false
                })
            }
        ).catch(error=>{
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
                });        
            }else {
                this.setState({
                    isLoading:false,
                    serverError: true,
                });        
            }
        });
    }
    bookUnlike(bookId){
        UServ.unlikeBook(bookId).then((data)=>{
            this.setState((state) => {
                const tempBooks = state.books.filter((t) => {
                    return t.id !== bookId;
                });
                return {
                    "books":tempBooks
                }
            })

        }).catch(error=>{
            
        });
    }
    componentDidMount(){
        let username = this.props.match.params.username;
        this.loadUserProfile(username);
    }
    componentDidUpdate(nextProps){
        if(this.props.match.params.username !== nextProps.match.params.username) {
            this.loadUserProfile(nextProps.match.params.username);
        }
    }
    render() {
        if(this.state.isLoading){
            return <LoadingIndicator/>
        }
        if(this.state.expiredToken){
            return <Redirect to={"/signin"}/>
        }
        if(this.state.notFound) {
            return <NotFound />;
        }

        if(this.state.serverError) {
            return <ServerError />;
        }
        if(this.state.user){
        return (
            <div className="row">
                   <div className="d-block mr-auto m-3 p-3">
                        <h5>Username : <b>{this.state.user.username}</b></h5>
                        <h5>Name: <b>{this.state.user.name}</b></h5>
                        <ul className="nav nav-tabs" id="myTab" role="tablist">
                    <li className="nav-item">
                        <a className="nav-link active" id="liked-books-tab" data-toggle="tab" href="#likedbooks" role="tab" aria-controls="liked-books" aria-selected="true">Liked books</a>
                    </li>
                    <li className="nav-item">
                        <a className="nav-link" id="reviews-tab" data-toggle="tab" href="#reviews" role="tab" aria-controls="reviews" aria-selected="false">Reviews</a>
                    </li>
                </ul>
                <div className="tab-content" id="myTabContent">
                    <div className="tab-pane fade show active" id="likedbooks" role="tabpanel" aria-labelledby="liked-books-tab"><BookList handleLike={this.bookLike} handleUnlike={this.bookUnlike} authenticated={true} books={this.state.books}/></div>
                    <div className="tab-pane fade" id="reviews" role="tabpanel" aria-labelledby="reviews-tab">
                    <ReviewList reviews={this.state.reviews}></ReviewList></div>
                </div>
                   </div>
                   
            </div>
        );}
        return (
            <div className="row">
                Error
            </div>
        );
    }
}

export default Profile;