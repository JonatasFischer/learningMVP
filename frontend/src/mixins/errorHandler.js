export default {
  data() {
    return {
      hasError: false
    }
  },

  created() {
    this.$root.$on('bv::toast:hidden', () => this.hasError = false)
  },

  methods: {
    handleResponseError (error) {
      switch (error.status) {
        case 200:
          this.handleResponseErrorCode(error)
          break
        default:
          if ((error.data.code || null) !== null) {
            this.handleResponseErrorCode(error)
          }

          this.handleErrorMessage(error)
          break
      }
    },
    handleErrorMessage (error) {
      let showError = !(error.config && (error.config.handleError || false) === false)

      if (showError) {
        const errorMessage = `HTTP ${error.status} - ${error.data.message || error.statusText}`

        this.$bvToast.toast(errorMessage, {
          title: `An error has occurred`,
          autoHideDelay: 5000,
          appendToast: true,
          variant: 'danger'
        })
      }
    },
    handleResponseErrorCode (error) {
      const { data } = error
      const errorCode = data.code || null

      switch (errorCode) {
        case 101:
          // TODO: create a renderless component to handle different ways to display the error messages: toast, alert, modal, etc
          this.$store.commit('chooser/toggle', true)
          if (!this.hasError) {
            this.$bvToast.toast(data.message, {
              title: `Error`,
              autoHideDelay: 5000,
              appendToast: false,
              variant: 'danger'
            })
            this.hasError = true
          }
          break
        default:
          break
      }
    }
  }
}