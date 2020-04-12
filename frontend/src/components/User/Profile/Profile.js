import React, { Component } from 'react';
import UServ from "../../../service/userService";
import NotFound from "../../common/NotFound/NotFound";
import ServerError from "../../common/ServerError/ServerError";
import {withRouter} from 'react-router-dom';

class Profile extends Component {
    constructor(props){
        super(props);
        this.state={
            user: null,
            notFound: false,
            serverError: false
        };
        
    };
    loadUserProfile(username){
        
        UServ.getProfile(username).then(
            response=>{
                this.setState({
                    user:response.data
                })
            }
        ).catch(error=>{
            let code=error.message.slice(error.message.length-3).trim();
            if(code === '404') {
                this.setState({
                    notFound: true,
                });
            } else {
                this.setState({
                    serverError: true,
                });        
            }
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
                    <div className="tab-pane fade show active" id="likedbooks" role="tabpanel" aria-labelledby="liked-books-tab">Liked books</div>
                    <div className="tab-pane fade" id="reviews" role="tabpanel" aria-labelledby="reviews-tab">...</div>
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