import React from 'react';
import avatar from "../../../Images/avatar.png"

const ReviewAdd = (props) => {
   const handleSubmit=(e)=>{
        e.preventDefault();

    }
    return (
            <div>
            <div className="col-2">
                <img src={avatar} className="img-fluid w-75 h-75" alt={props.userName}/>
                <div className="text-muted d-block">{props.userName}</div>
            </div>
            <div className="col-10">
            <form onSubmit={handleSubmit}>
                <div className="form-group border-aqua">
                <label htmlFor="reviewSummary">Summary</label>
                <textarea className="form-control" id="reviewSummary" rows="3" required></textarea>
                 </div>
                <div className="col-sm-8 ml-auto mr-auto">
                    <button type={'submit'} className="btn btn-info btn-block">Save</button>
                </div>
            </form>
            </div>
            </div>
            
            
    );
};

export default ReviewAdd;