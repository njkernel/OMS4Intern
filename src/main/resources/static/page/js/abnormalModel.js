let abnormalModel = new Vue({
    el: '#abnormalModel',
    data: function () {

        return {
            ids: '1'
        };
        console.log(ids);

    },
    created(){
        /*this.getParams()*/
      /*  window.onload = function() {
            var iframe = document.getElementById("iframeId");
            var targetOrigin = "http://www.php.com";
            var dite="qwe"
            iframe.contentWindow.postMessage(dite, targetOrigin);
        };*/
    },

    methods: {
        getParams (){
            this.ids=JSON.parse(localStorage.getItem("goodsId"));
            console.log("Model",this.ids);
        },
     /*   getParams () {
            console.log("Model",JSON.parse(localStorage.getItem("goodsId")));
            this.ids=JSON.parse(localStorage.getItem("goodsId"));
            console.log(this.id)
        }*/
    },

});


