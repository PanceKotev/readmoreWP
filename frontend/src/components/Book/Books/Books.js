import React, { Component } from 'react';
import BookCardCarousel from "../BookCardCarousel/BookCardCarousel"
import BS from "../../../service/bookService"
import BookList from "../BookList/BookList"
import NotFound from '../../common/NotFound/NotFound';
import ServerError from '../../common/ServerError/ServerError';
import LoadingIndicator from "../../common/LoadingIndicator/LoadingIndicator"
import UServ from "../../../service/userService"
import "./Books.css"
import BookDetails from '../BookDetails/BookDetails';


class Books extends Component {
    constructor(props){
        super(props);

        this.state={
            books:[],
            notFound:false,
            serverError:false,
            isLoading:false
        }
        this.handleLike=this.handleLike.bind(this);
        this.handleUnlike=this.handleUnlike.bind(this);

    }
    loadBooks(listBy){
        this.setState({
            isLoading:true
        });
        switch(listBy){
        case "genre":{
            BS.listBooksByGenre(this.props.match.params.genreName).then((data)=>{
                this.setState({
                    books:data.data,
                    isLoading:false
                })
            }).catch(error=>{
                let code=error.message.slice(error.message.length-3).trim();
                if(code === '404'){
                    this.setState({
                        isLoading:false,
                        notFound:true
                    })
                }
                else{
                    this.setState({
                        isLoading:false,
                        serverError:true
                    })
                }
            })
            break;
        }
        case "series":{
            BS.listBooksBySeries(this.props.match.params.seriesName).then((data)=>{
                this.setState({
                    books:data.data,
                    isLoading:false
                })
            }).catch(error=>{
                let code=error.message.slice(error.message.length-3).trim();
                if(code === '404'){
                    this.setState({
                        isLoading:false,
                        notFound:true
                    })
                }
                else{
                    this.setState({
                        isLoading:false,
                        serverError:true
                    })
                }
            })
            break;
        }
        case "":{
            BS.listPopular().then((data)=>{
                this.setState({
                    books:data.data,
                    isLoading:false
                })
            }).catch(error=>{
                this.setState({
                    isLoading:false,
                    serverError:true
                });
               
            
            })
            break;
        }
        case "author":{
            BS.listBooksByAuthor(this.props.match.params.authorName).then((data)=>{
                this.setState({
                    books:data.data,
                    isLoading:false
                })
            }).catch(error=>{
                let code=error.message.slice(error.message.length-3).trim();
                if(code === '404'){
                    this.setState({
                        isLoading:false,
                        notFound:true
                    })
                }
                else{
                    this.setState({
                        isLoading:false,
                        serverError:true
                    })
                }
            })
            break;
        } 
        default:{
            this.setState({
                isLoading:false
            });
        }
        }
        
            
    }
    handleLike(bookId){
        UServ.likeBook(bookId).then((data)=>{
            let book=data.data;
            this.setState((prevState)=>{
                let newBooks=prevState.books.map((item)=>{
                  if(item.id===book.id){
                    return book;
                  }
                  return item;
                })
                return {
                  "books": newBooks
                }
              });
        }).catch(error=>{
            
        });
    };
    handleUnlike(bookId){
        UServ.unlikeBook(bookId).then((data)=>{
            let book=data.data;
            this.setState((prevState)=>{
                let newBooks=prevState.books.map((item)=>{
                  if(item.id===book.id){
                    return book;
                  }
                  return item;
                })
                return {
                  "books": newBooks
                }
              });
        }).catch(error=>{
            console.log(error);
        });
    };
    componentDidMount(){
        let listType= this.props.listTypeBy;
        this.loadBooks(listType);
    }
    componentDidUpdate(nextProps){
        if(this.props.listTypeBy === "genre" && (this.props.match.params.genreName !== nextProps.match.params.genreName)){
            this.loadBooks("genre");
        }else if(this.props.listTypeBy === "author" && (this.props.match.params.authorName !== nextProps.match.params.authorName)){
            this.loadBooks("author");
        }else if(this.props.listTypeBy === "series" && (this.props.match.params.seriesName !== nextProps.match.params.seriesName)){
            this.loadBooks("series");
        }
    }
    render() {
        if (this.state.isLoading){
            return <LoadingIndicator/>
        }
        if(this.state.notFound){
            return <NotFound/>
        }
        if(this.state.serverError){
            return <ServerError/>
        }
        if(this.props.listTypeBy === "author"){
            return (<div className="row row-cols-1 ">
            <div className="d-block mx-auto text-center w-100"><div className="heading-t">Books from the author <b>{this.props.match.params.authorName.toString().charAt(0).toUpperCase()+this.props.match.params.authorName.toString().slice(1)}</b></div></div>
            <BookList handleLike={this.handleLike} handleUnlike={this.handleUnlike} authenticated={this.props.authenticated} books={this.state.books}/>
            </div>);
        }
        if(this.props.listTypeBy === "genre"){
            return (
                <div className="row row-cols-1">
                <div className="d-block mx-auto text-center w-100"><div className="heading-t">Books from the genre <b>{this.props.match.params.genreName.toString().charAt(0).toUpperCase()+this.props.match.params.genreName.toString().slice(1)}</b></div></div>
                <BookList handleLike={this.handleLike} handleUnlike={this.handleUnlike} authenticated={this.props.authenticated} books={this.state.books}/></div>);
        }
        if(this.props.listTypeBy === "series"){
            return (
                <div className="row row-cols-1">
                <div className="d-block mx-auto text-center w-100"><div className="heading-t">Books from the series <b>{this.props.match.params.seriesName.toString().charAt(0).toUpperCase()+this.props.match.params.seriesName.toString().slice(1)}</b></div></div>
                <BookList handleLike={this.handleLike} handleUnlike={this.handleUnlike} authenticated={this.props.authenticated} books={this.state.books}/></div>);
        }
        return (
                <BookCardCarousel authenticated={this.props.authenticated} handleLike={this.handleLike} handleUnlike={this.handleUnlike} books={this.state.books} />
        );
    }
}

export default Books;