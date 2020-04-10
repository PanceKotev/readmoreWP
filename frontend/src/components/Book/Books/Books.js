import React, { Component } from 'react';
import BookCardCarousel from "../BookCardCarousel/BookCardCarousel"
import BS from "../../../service/bookService"


class Books extends Component {
    constructor(props){
        super(props);

        this.state={
            books:[]
        }

    }
    loadBooks(){
        BS.listPopular().then((data)=>{
            this.setState({
                books:data.data})
        })
    }
    componentDidMount(){
        this.loadBooks();
    }
    render() {
        return (
            <div className="row">
                <BookCardCarousel books={this.state.books} />
            </div>
        );
    }
}

export default Books;