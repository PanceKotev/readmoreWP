import apiUtils from "../util/apiUtils";
import {ACCESS_TOKEN} from "../constants";


const Auth = {
    login: (request) => {
        return apiUtils.post('/auth/login', request);
    },
    registerAdmin: (request) => {
        return  apiUtils.post('/auth/register-admin', request);
    },
    registerManager: (request) => {
        return  apiUtils.post('/auth/register-manager', request);
    },
    getCurrentUser: () => {
        if(!localStorage.getItem(ACCESS_TOKEN)) {
            return Promise.reject("No access token set.");
        }
        return apiUtils.get('/users/me')
    }
};

export default Auth;