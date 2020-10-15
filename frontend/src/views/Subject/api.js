import axios from "axios";
/**
 * Get URL parameters
 */
const URL_PARAMS = new URLSearchParams(location.search);

/**
 * Get shop base path
 */
const  BASE_PATH = window.location.protocol + '//' + window.location.hostname+ ':8080';

const SUBJECT_URL = `${BASE_PATH}/api/subjects/`;

export default {
    async getList() {
        let result = await axios.get(SUBJECT_URL);
        return result.data;
    },

    async getSubject(id) {
        let result = await axios.get(SUBJECT_URL + id);
        return result.data;
    },

    async save(subject) {
        let result = await axios.put(SUBJECT_URL + subject.id, subject);
        return result.data;
    },
    async searchSubject(search) {
        let result = await axios.get(`${SUBJECT_URL}?q=${search}`);
        return result.data;
    }
};
