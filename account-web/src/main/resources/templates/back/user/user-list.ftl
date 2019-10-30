<#assign childrenTitle='用户管理' />
<#assign childrenSubTitle='用户管理列表' />
<@override name="middle">
 <div class="layui-card">
     <div class="layui-form layui-card-header layuiadmin-card-header-auto" lay-filter="userFormList">
         <div class="layui-form-item">
             <div class="layui-inline">
                 <label class="layui-form-label">登录名</label>
                 <div class="layui-input-block">
                     <input type="text" name="name" placeholder="请输入登录名" autocomplete="off" class="layui-input">
                 </div>
             </div>
             <div class="layui-inline">
                 <button class="layui-btn layuiadmin-btn-useradmin" lay-submit="" lay-filter="userSearch">
                     <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                 </button>
             </div>
         </div>
     </div>
     <div class="layui-card-body">
        <table class="layui-hide" id="listUser" lay-filter="test"></table>
     </div>
 </div>
</@override>
<@extends name="/back/common/children-context.ftl"></@extends>
<script type="text/html" id="listUserBar">
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
            elem: '#listUser'
            ,height: 700
            ,url: '/back/user' //数据接口
            ,title: '用户表'
            ,page: true //开启分页
            ,limit: 10
            ,cellMinWidth: 160
            // ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
            // ,defaultToolbar: ['add']
            ,totalRow: false //开启合计行
            ,cols: [[ //表头
                {type: 'checkbox', fixed: 'left'}
                ,{field: 'id', title: 'id', width:'10%', sort: true, fixed: 'left'}
                ,{field: 'name', title: '用户名'}
                // ,{field: 'balance', title: '余额', width: '10%', sort: true, }
                ,{field: 'amount', title: '留存金额',  sort: true, }
               ,{fixed: 'right',  align:'center', toolbar: '#listUserBar'}
            ]]
        });

        //监听头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id)
                    ,data = checkStatus.data; //获取选中的数据
            switch(obj.event){
                case 'add':
                    layer.msg('添加');
                    window.location.href='${request.contextPath}/back/user/add';
                    break;
                case 'update':
                    if(data.length === 0){
                        layer.msg('请选择一行');
                    } else if(data.length > 1){
                        layer.msg('只能同时编辑一个');
                    } else {
                        window.location.href='${request.contextPath}/back/user/'+checkStatus.data[0].id+'/edit';
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
                            url:'${request.contextPath}/back/user/'+ ids.toString(),
                            data:{
                            },
                            type:'delete',
                            dataType:'json',
                            success:function(dataObj){
                                layer.msg(dataObj.msg);
                                if(dataObj.code> 0){
                                    table.reload('listUser');
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
                        url:'${request.contextPath}/back/user/'+ data.id,
                        data:{
                        },
                        type:'delete',
                        dataType:'json',
                        success:function(data){
                            layer.msg(data.msg);
                            if(data.code> 0){
                                table.reload('listUser');
                            }
                        }
                    });
                });
            } else if(layEvent === 'edit'){
                layer.msg('编辑操作');
                window.location.href='${request.contextPath}/back/user/'+data.id+'/edit';
            }
        });
        //监听搜索
        form.render(null, 'userFormList');
        form.on('submit(userSearch)', function(data){
            var field = data.field;
            //执行重载
            table.reload('listUser', {
                where: field
            });
        });



    });
</script>
