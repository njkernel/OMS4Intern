let outputList = new Vue({
    el: '#outputList',
    data: function () {
        return {
            //选中行的记录id
            checkedDate:"",
            //默认选中
            selected: 'orderId',

        }
    },
    created: function () {
        this.initTable();
    },

    methods: {
        //初始化表格
        initTable(){
            let url='/OutputDetails';
            callAxiosGet(url,this.getListSuc,this.Fail)
        },


        // 出库订单详情
        viewOutputDetails(){
            document.getElementById('iframeId2').src="/orderDetailsAll?orderId="+this.checkedDate;
        },
    }
});