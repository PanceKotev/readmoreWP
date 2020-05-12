import React,{useState,useEffect} from 'react';
import avatar from "../../../Images/avatar.png";
import Auth from "../../../service/authService";
import LoadingIndicator from '../../common/LoadingIndicator/LoadingIndicator';
import Rating from 'react-rating';
import RS from '../../../service/reviewService';
import { toast } from 'react-toastify';
import {withRouter} from 'react-router-dom'


const ReviewAdd = (props) => {
    const [user,setUser] = useState({
        user:null
    });
    const [isLoading,setLoading] = useState({
        isLoading:false
    })
    var rating = 0;
   const handleSubmit=(e)=>{
        e.preventDefault();
        let revSumm = document.getElementById("reviewSummary").value;
        let reviewRequest = {
            bookId: props.bookId,
            summary: revSumm,
            rating: rating
        }
        console.log(reviewRequest)
        RS.createReview(reviewRequest).then(data=>{
            toast.success("You've added a review!");
            props.reloadBook(props.bookName);
        }).catch(data=>{
            toast.error("Error!")
            props.history.push("/");
        })
    }
    useEffect(()=>{
        setLoading(true)
        Auth.getCurrentUser().then(data=>{ 
            setUser(data.data)
            setLoading(false)
        }).catch(data=>{
            setLoading(false)
        })
        
    },[])
    const changeRating=(value)=>{
        rating = value;
    }
    if (isLoading){
        return <LoadingIndicator/>
    }
    return (
            <div className="row-cols-1 col-8 p-1">
            <div className="col-8">
                <div className="d-block row-cols-2">
                <img src={avatar} className="img-fluid" width="75px" height="75px" alt={user.username}/>
                <Rating id="reviewRating" style={{ fontSize:'0.8vw',color:'#212529'}} emptySymbol="fa fa-star-o fa-2x" fullSymbol="fa fa-star fa-2x" fractions={2} onClick={changeRating}/>
                </div>
                <div className="text-muted d-block">{user.username}</div>
            </div>
            <div className="col-8">
            <form onSubmit={handleSubmit}>
                <div className="form-group border-aqua">
                <label htmlFor="reviewSummary">Summary</label>
                <textarea className="form-control" id="reviewSummary" rows="3" required></textarea>
                 </div>
                <div className="col-sm-4 ml-auto mr-auto">
                    <button type={'submit'} className="btn btn-info btn-block">Save</button>
                </div>
            </form>
            </div>
            </div>
            
            
    );
};

export default withRouter(ReviewAdd);