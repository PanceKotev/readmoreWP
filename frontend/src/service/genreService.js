import apiUtils from "../utils/apiUtils";

const GS = {
    getAll: ()=>{
        return apiUtils.get("/genre/all");
    }
}
export default GS;