import { ACCESS_TOKEN} from '../constants';
import axios from "../custom-axios/axios";

const apiUtils = {
    get: (url) => {
        return axios.get(url, {
            headers: {
                Authorization: token()
            }
        });
    },
    post: (url, body) => {
        const data = JSON.stringify(body);

        return axios.post(url, data, {
            headers: {
                Authorization: token(),
                'content-type': 'application/json'
            }
        });
    }
};

function token() {
    if(localStorage.getItem(ACCESS_TOKEN)) {
        return 'Bearer ' + localStorage.getItem(ACCESS_TOKEN);
    }
    return '';
}

export default apiUtils;