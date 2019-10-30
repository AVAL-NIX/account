<!DOCTYPE html>
<html class="x-admin-sm">
<#include "../common/head.ftl" />
<body>
<div class="layui-fluid">
    <div class="layui-row">
        <form class="layui-form" >
            <div class="layui-form-item">
                <label for="price" class="layui-form-label">
                    <span class="x-red">*</span>金额
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="price" name="price" required="" lay-verify="required"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="type" class="layui-form-label">
                    <span class="x-red">*</span>交易类型
                </label>
                <div class="layui-input-inline">

                    <select name="type">
                        <#list typeEnum as item >
                            <option value="${(item.code )}">${(item.value)!''}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="userId" class="layui-form-label">
                    <span class="x-red">*</span>关联用户
                </label>
                <div class="layui-input-inline">
                    <select name="userId">
                        <option value="-1">无用户</option>
                        <#list users as item >
                            <option value="${(item.id)}">${(item.name)!''}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="descrition" class="layui-form-label">
                    <span class="x-red"></span>备注
                </label>
                <div class="layui-input-inline">
                    <textarea placeholder="请输入内容" id="descrition" name="descrition" lay-verify="required" class="layui-textarea"></textarea>
                </div>
            </div>

            <div class="layui-form-item">
                <label for="L_repass" class="layui-form-label">
                </label>
                <button class="layui-btn" lay-filter="save" lay-submit="">
                    增加
                </button>
            </div>
        </form>
    </div>
</div>
<script>layui.use(['form', 'layer'],
        function () {
            $ = layui.jquery;
            var form = layui.form,
                layer = layui.layer;


            //监听提交
            form.on('submit(save)',
                function (data) {
                    $.ajax({
                        url: "/back/accountDetail/",
                        type: "POST",
                        dataType: "json",
                        data: data.field,
                        success: function (data) {
                            xadmin.msg(data)
                        }
                    })
                    return false;
                });

            form.render()

        });



</script>

</body>

</html>
