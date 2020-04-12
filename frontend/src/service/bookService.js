import apiUtils from "../utils/apiUtils";


const BS = {
    listPopular: ()=>{
        return apiUtils.get("/book/popular");
    },
    getBook: (bookName)=>{
        return apiUtils.get("/book/?bookName="+bookName);
    },
    listBooksByGenre:(genreName)=>{
        return apiUtils.get("/book/genre/"+genreName);
    },
    listBooksByAuthor:(authorName)=>{
        return apiUtils.get("/book/author/"+authorName);
    }
}
export default BS;