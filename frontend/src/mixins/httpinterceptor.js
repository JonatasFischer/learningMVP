import axios from 'axios'
import ErrorHandler from '@/mixins/errorHandler'
import authHeader from '../services/auth-header';

export default {
    mixins: [ErrorHandler],
    created () {
        axios.interceptors.response.use(
            this.handleResponse,
            error => {
                this.handleError(error.response)
                return Promise.reject(error)
            }
        )

        // Add header to all the requests
        axios.interceptors.request.use(config => {
            config.headers = {...config.headers, ...authHeader()};
            return config;
        }, error => Promise.reject(error))
    },
    methods: {
        handleResponse (response) {
            const { data } = response
            const status = data.status || '';

            if (status === 'error') {
                this.handleResponseError(response)
            }
            return response
        },
        handleError (response) {
            // "errors" mixin
            this.handleResponseError(response)
        }
    }
}
