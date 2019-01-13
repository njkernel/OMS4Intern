let abnormalModel = new Vue({
    el: '#abnormalModel',
    data: function () {
        return {
            id: ''
        }
    },
    mounted(){
        this.getParams()
    },
    methods: {
        getParams () {
            console.log(JSON.parse(sessionStorage.getItem("id")));
        }
    },

});


