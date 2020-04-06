import axios from 'axios';

const api = axios.create({
    baseURL: 'http://sgrhubackend.herokuapp.com'
})
 export default api;