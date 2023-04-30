import axios from 'axios';
import config from '../config/config_api';

export class BookService {
    apiURL = config.apiLoginUrl;

    constructor() {
        this.config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };
    }

    async getAllBooks() {
        return axios
            .get(`${this.apiURL}/api/v1/book/list`, this.config)
            .then((res) => res.data)
            .catch((err) => {
                if (err.response && err.response.data) {
                    throw err.response.data;
                } else {
                    throw err;
                }
            });
    }

    setToken(token) {
        this.config.headers.Authorization = `Bearer ${token}`;
    }
}
