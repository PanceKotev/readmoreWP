import React, { Component } from 'react';
import RS from "../../../service/reviewService"

class Reviews extends Component {
    constructor(props){
        super(props);
        this.setState({
            reviews:[],
            loadFrom:"",
            isLoading:false,
            notFound:false,
            serverError:false,
            tokenExpired:false
        })
    }
    componentDidMount(){
        
    }
    loadReviews(loadFrom){
        this.setState({
            isLoading:true
        });
        switch(loadFrom){
            case "book":{
                RS.listReviewsByBook(this.props.match.params.bookId)
                break;
            }
            default :{
                this.setState({
                    isLoading:false
                })
            }
        }
    }
    render() {
        return (
            <div>
                
            </div>
        );
    }
}

export default Reviews;