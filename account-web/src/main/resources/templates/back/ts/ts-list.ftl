<#assign childrenTitle='帐号管理' />
<#assign childrenSubTitle='帐号管理列表' />
<@override name="middle">
 <div class="layui-card">
     <div class="layui-form layui-card-header layuiadmin-card-header-auto" lay-filter="tsFormList">
         <div class="layui-form-item">
             <div class="layui-inline">
                 <label class="layui-form-label">帐号</label>
                 <div class="layui-input-block">
                     <input type="text" name="username" placeholder="请输入帐号" autocomplete="off" class="layui-input">
                 </div>
             </div>
             <div class="layui-inline">
                 <button class="layui-btn layuiadmin-btn-useradmin" lay-submit="" lay-filter="tsSearch">
                     <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
                 </button>
             </div>
         </div>
     </div>
     <div class="layui-card-body">
        <table class="layui-hide" id="listTs" lay-filter="test"></table>
     </div>
 </div>
</@override>
<@extends name="/back/common/children-context.ftl"></@extends>
<script type="text/html" id="listTsBar">
    <div class="layui-btn-container">
       <span>单击单元格进行编辑</span>
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
            elem: '#listTs'
            ,height: 700
            ,url: '/back/ts' //数据接口
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
                ,{field: 'username', title: '账户',  sort: true,edit: 'text'}
                ,{field: 'password', title: '密码',  sort: true,edit: 'text' }
                ,{field: 'token', title: 'TOKEN',  sort: true, edit: 'text'}
               ,{fixed: 'right',  align:'center', toolbar: '#listTsBar'}
            ]]
        });


        //监听搜索
        form.render(null, 'tsFormList');
        form.on('submit(tsSearch)', function(data){
            var field = data.field;
            //执行重载
            table.reload('listTs', {
                where: field
            });
        });

        //监听单元格编辑
        table.on('edit(test)', function(obj){
            var value = obj.value //得到修改后的值
                ,data = obj.data //得到所在行所有键值
                ,field = obj.field; //得到字段
            console.log("data",data,field)
            $.ajax({
                url:'${request.contextPath}/back/ts',
                data:data,
                type:'post',
                dataType:'json',
                success:function(dataObj){
                    layer.msg(dataObj.msg);
                    if(dataObj.code> 0){
                        //执行重载
                        table.reload('listTs', {
                        });
                    }
                }
            });

        });



    });
</script>
