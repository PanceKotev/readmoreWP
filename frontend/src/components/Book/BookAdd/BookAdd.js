import React from 'react';
import BS from '../../../service/bookService';
import { toast } from 'react-toastify';
import {withRouter} from 'react-router-dom'

const BookAdd = (props) => {
    const handleSubmit=(e)=>{
        e.preventDefault();
        if(validate()){
        let title= document.getElementById("title").value;
        let shortDescript = document.getElementById("shortDescript").value;
        let genreNames = document.getElementById("genres").value;
        genreNames= genreNames.trim().replace(/\s/g, "").split(",");
        let cover = document.getElementById("cover").value;
        let language = document.getElementById("language").value;
        let author = document.getElementById("author").value;
        let datePublished = document.getElementById("datePublished").value;
        let seriesName = document.getElementById("series").value;
        let pageCount = document.getElementById("pageCount").value;
        let downloadLink = document.getElementById("download").value;
        let BookCreationRequest={
            name:title,
            shortDescription:shortDescript,
            genreNames:genreNames,
            cover:cover,
            author:author,
            datePublished:datePublished,
            pageCount:pageCount,
            language:language,
            downloadList:downloadLink,
            seriesName:seriesName  
        };
        
        
        console.log(BookCreationRequest);
        BS.createBook(BookCreationRequest).then(data=>{
            toast.success("Successfully created book!");
            props.history.push("/");
        }).catch(data=>{
            toast.error("There was an issue with the server.");
        })
    }

    }
    const validate=()=>{
        let title= document.getElementById("title").value.length;
        console.log(title);
        let shortDescription = document.getElementById("shortDescript").value.length;
        let title_constraint =title <3 && title>40;
        let descript_constraint = shortDescription <10 && shortDescription>150;
        if(title_constraint){
            document.getElementById("title-err").hidden = true;
        }
        if(!title_constraint){
            document.getElementById("title-err").hidden = false;
        }
        
        if(descript_constraint){
            document.getElementById("description-err").hidden = true;
        }
        if(!descript_constraint){
            document.getElementById("description-err").hidden = false;
        }
        return !title_constraint && !descript_constraint;

    }
    return (
        <form onSubmit={handleSubmit} >
        <div className="col-sm-4 mx-auto mt-4 p-5 "
             style={{backgroundColor:'#ecfbfc71', border:'0.1em solid #235952',borderRadius:'0.2em'}}>
             <div className="form-group">
                <label htmlFor="title">Title:</label>
                <input id="title" className="form-control text-center" type="text" required/>
                <small id="title-err" className="form-text text-danger" hidden="true">Book title must be between 3 and 40 letters.</small>
                </div>
            <div className="form-group">
                <label htmlFor="shortDescript">Short Description:</label>
                <textarea className="form-control" id="shortDescript" rows="3" required></textarea>
                <small id="description-err" className="form-text text-danger" hidden="true">Book description must be between 10 and 150 letters.</small>
                </div>
                <label htmlFor="genres">Genres:</label>
                <input id="genres" className="form-control text-center" placeholder="Mystery, Thriller..." type="text" required/>
                <br/>
                <label htmlFor="cover">Cover:</label>
                <input id="cover" className="form-control text-center" placeholder="image url" type="text" required/>
                <br/>
                <label htmlFor="author">Author:</label>
                <input id="author" className="form-control text-center" type="text" required/>
                <br/>
                <label htmlFor="datePublished">Date published:</label>
                <input id="datePublished" className="form-control text-center" type="date" required/>
                <br/>
                <label htmlFor="pageCount">Page count:</label>
                <input id="pageCount" className="form-control text-center" type="number" required/>
                <br/>
                <label htmlFor="language">Language:</label>
                <input id="language" className="form-control text-center" type="text" required/>
                <br/>
                <label htmlFor="download">Download link:</label>
                <input id="download" className="form-control text-center" type="text" required/>
                <br/>
                <label htmlFor="series">Series name:</label>
                <input id="series" className="form-control text-center" type="text"/>
                <br/>
                <div className="col-sm-8 ml-auto mr-auto">
                <button type={'submit'} className="btn btn-info btn-block">Add book</button>
            </div>
        </div>
    </form>
    );
};

export default withRouter(BookAdd);