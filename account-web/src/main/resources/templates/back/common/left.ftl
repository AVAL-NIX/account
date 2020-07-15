<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <li>
                <a href="javascript:;">
                    <i class="iconfont left-nav-li" lay-tips="会员管理">&#xe6b8;</i>
                    <cite>会员管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i></a>
                <ul class="sub-menu">
                        <li>
                            <a onclick="xadmin.add_tab('用户列表','${request.contextPath}/back/home/user')">
                                <i class="iconfont">&#xe6a7;</i>
                                <cite>用户列表</cite></a>
                        </li>
                        <li>
                            <a onclick="xadmin.add_tab('流水列表','${request.contextPath}/back/home/accountDetail')">
                                <i class="iconfont">&#xe6a7;</i>
                                <cite>流水列表</cite></a>
                        </li>
                    <li>
                        <a onclick="xadmin.add_tab('帐号列表','${request.contextPath}/back/home/ts')">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>帐号列表</cite></a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
</div>