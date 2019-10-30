<#assign childrenTitle='流水管理' />
<#assign childrenSubTitle='流水管理列表' />
<@override name="middle">
 <div class="layui-card">
     <div class="layui-form layui-card-header layuiadmin-card-header-auto" lay-filter="accountDetailFormList">
         <div class="layui-form-item">
             <div class="layui-inline">
                 <label class="layui-form-label">用户名</label>
                 <div class="layui-input-block">
                     <input type="text" name="userName" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                 </div>
             </div>
             <div class="layui-inline">
                 <button class="layui-btn layuiadmin-btn-accountDetailadmin" lay-submit="" lay-filter="accountDetailSearch">
                     <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                 </button>
             </div>
         </div>
     </div>
     <div class="layui-card-body">
        <table class="layui-hide" id="listAccountDetail" lay-filter="test"></table>
     </div>
 </div>
</@override>
<@extends name="/back/common/children-context.ftl"></@extends>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <a class="layui-btn layui-btn-xs" lay-event="add">添加</a>
    </div>
</script>
<script>
    layui.use(['laydate', 'laypage', 'layer', 'table','element','jquery'], function(){
        var laydate = layui.laydate //日期
                ,laypage = layui.laypage //分页
                ,layer = layui.layer //弹层
                ,table = layui.table //表格
        var $ = layui.jquery;
        var form = layui.form;

        //执行一个 table 实例
        table.render({
            elem: '#listAccountDetail'
            ,toolbar: '#toolbarDemo'
            ,height: 700
            ,url: '/back/accountDetail' //数据接口
            ,title: '流水表'
            ,page: true //开启分页
            ,limit: 10
            ,cellMinWidth: 160
            // ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            ,totalRow: true //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'id', width:'10%', sort: true, fixed: 'left'}
                ,{field: 'price', title: '交易金额', width:'10%'}
                ,{field: 'typeStr', title: '类型', width: '10%'}
                ,{field: 'userName', title: '用户名', width: '10%' }
                ,{field: 'descrition', title: '备注', width: '10%' }
               ,{fixed: 'right',  align:'center', toolbar: '#listAccountDetailBar'}
            ]]
        });

        //监听头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                    ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'add':
                    xadmin.open('添加','${request.contextPath}/back/home/accountDetail/add');
                    break;
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        window.location.href='${request.contextPath}/back/accountDetail/'+checkStatus.data[0].id+'/edit';
                    }
                    break;
                case 'delete':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else {
                        var ids = [];
                        $.each(data,function(i,e){
                            ids.push(e.id);
                        });
                        $.ajax({
                            url:'${request.contextPath}/back/accountDetail/'+ ids.toString(),
                            data:{
                            },
                            type:'delete',
                            dataType:'json',
                            success:function(dataObj){
                                layer.msg(dataObj.msg);
                                if(dataObj.code> 0){
                                    table.reload('listAccountDetail');
                                }
                            }
                        });
                    }
                    break;
            };
        });

        //监听行工具事件
        table.on('tool(test)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
            var data = obj.data //获得当前行数据
                    ,layEvent = obj.event; //获得 lay-event 对应的值
            if(layEvent === 'detail'){
                layer.msg('查看操作');
            } else if(layEvent === 'del'){
                layer.confirm('真的删除行么', function(index){
                    //向服务端发送删除指令
                    $.ajax({
                        url:'${request.contextPath}/back/accountDetail/'+ data.id,
                        data:{
                        },
                        type:'delete',
                        dataType:'json',
                        success:function(data){
                            layer.msg(data.msg);
                            if(data.code> 0){
                                table.reload('listAccountDetail');
                            }
                        }
                    });
                });
            } else if(layEvent === 'add'){
                xadmin.open('添加','${request.contextPath}/back/home/accountDetail/add');
            }
        });
        //监听搜索
        form.render(null, 'accountDetailFormList');
        form.on('submit(accountDetailSearch)', function(data){
            var field = data.field;
            //执行重载
            table.reload('listAccountDetail', {
                where: field
            });
        });



    });
</script>
