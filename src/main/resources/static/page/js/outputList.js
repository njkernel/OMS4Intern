let outputList = new Vue({
    el: '#outputList',
    data: function () {
        return {
            //分页数据
            page:{
                pageSize:5,
                currentPage:1,
                // outputCode:'', orderId:'', receiverName:'', deliveryCode:''
            },
            //选中行的记录id
            checkedDate:"",
            //搜索输入框
            searchInput:"",
            //出库单数据
            outputDate:[],
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
            callAxiosGet(url,this.page,this.getListSuc,this.Fail)
        },
        //初始化
        refresh(){
            this.initialize();
            this.initTable();
        },


        // 出库订单详情
        viewOutputDetails(){
            document.getElementById('iframeId2').src="/orderDetailsAll?orderId="+this.checkedDate;
        },

        // 根据页码，查询到要显示的数据
        to_page(pn) {
            let that=this;
            axios.get('/OutputDetails', {
                params: {
                    currentPage:pn,
                    pageSize: that.page.pageSize
                }
            }).then(res => {
                console.log(res);
                that.outputDate = res.data.data;
                console.log(that.outputDate)
            }, err => {
                console(err);
            })
        },



        //出库订单列表接口反馈
        getListSuc(res){
            let that=this;
            if(res.status===200){
                that.outputDate=res.data;
                //默认选中第一条数据
                that.checkedDate=res.data.list[0].abnormalId;
            }else{
                alert(res.message);
            }
        },

        //出库处理接口反馈
        //接口连通
        Suc(res){
            alert(res.message);
            this.initTable();
        },
        //接口未连通
        Fail(err){
            console.log("网络连接错误")
        },
    }
});