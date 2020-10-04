import axios from "axios";
/**
 * Get URL parameters
 */
const URL_PARAMS = new URLSearchParams(location.search);

/**
 * Get shop base path
 */
const  BASE_PATH = window.location.protocol + '//' + window.location.hostname+ ':8080';

const STUDY_PLAN_URL = `${BASE_PATH}/api/studyplans/`;

export default {
    async getList() {
        let result = await axios.get(STUDY_PLAN_URL);
        return result.data;
    },

    async getPlan(id) {
        let result = await axios.get(STUDY_PLAN_URL + id);
        return result.data;
    },

    async save(studyPlan) {
        let result = await axios.put(STUDY_PLAN_URL + studyPlan.id, studyPlan);
        return result.data;
    }
};
