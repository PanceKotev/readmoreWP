import apiUtils from "../utils/apiUtils";


const BS = {
    listPopular: ()=>{
        return apiUtils.get("/book/popular");
    },
    getBook: (bookName)=>{
        return apiUtils.get("/book/"+bookName);
    }
}
export default BS;