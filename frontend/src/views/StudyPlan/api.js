import axios from "axios";
/**
 * Get URL parameters
 */
const URL_PARAMS = new URLSearchParams(location.search);

/**
 * Get shop base path
 */
const  BASE_PATH = window.location.protocol + '//' + window.location.hostname+ ':8081';

const HOST = '';
const GET_THEMES_URL = () => `${BASE_PATH}/api/studyplans/`;

export default {
    async get() {
        let result = await axios.get(GET_THEMES_URL())
        return result.data;
    }
};
