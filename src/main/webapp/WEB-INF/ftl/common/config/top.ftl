<#macro top index>
<script baseUrl="${basePath}" src="${basePath}/js/user.login.js"></script>
<div class="navbar navbar-inverse navbar-fixed-top animated fadeInDown" style="z-index: 101;height: 41px;">
	  
      <div class="container" style="padding-left: 0px; padding-right: 0px;">
        <div class="navbar-header">
          <button data-target=".navbar-collapse" data-toggle="collapse" type="button" class="navbar-toggle collapsed">
            <span class="sr-only">导航</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
	     </div>
	     <div role="navigation" class="navbar-collapse collapse">
	     		<a id="_logo"  href="${basePath}" style="color:#fff; font-size: 24px;" class="navbar-brand hidden-sm">辽宁省林业厅生态公益林管理系统</a>
	          <ul class="nav navbar-nav" id="topMenu">
                  <li class="dropdown ">
                      <a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown" class="dropdown-toggle" href="#">
                          年度资金补偿标准<span class="caret"></span>
                      </a>
                      <ul class="dropdown-menu">
                          <li><a href="${basePath}/countryStandard/compensationStandard.shtml" >国家年度补偿标准维护</a></li>
                          <li><a href="${basePath}/localStandard/localCompensationStandard.shtml">地方年度补偿标准维护</a></li>
                          <li><a href="${basePath}/localStandard/localCompensationStandard.shtml">年度补偿标准设置</a></li>
                      </ul>                                            
                  </li>
                  <li class="dropdown ">
                      <a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown" class="dropdown-toggle" href="<%=basePath%>/user/index.shtml">
                          资金数据管理<span class="caret"></span>
                      </a>
                      <ul class="dropdown-menu">
                          <li><a href="${basePath}/countryDetail/countryCompensationDetail.shtml">国家补偿资金发放明细</a></li>
                          <li><a href="${basePath}/localDetail/localCompensationDetail.shtml" >地方补偿资金发放明细</a></li>
                      </ul>
                  </li>
                  <li class="dropdown ">
                      <a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown" class="dropdown-toggle" href="<%=basePath%>/user/index.shtml">
                          数据统计<span class="caret"></span>
                      </a>
                      <ul class="dropdown-menu">
                          <li><a href="${basePath}/report/localZbdhJdReport.shtml" >地方公益林直补到户工作进度报表</a></li>
                          <li><a href="${basePath}/report/contryZbdhJdReport.shtml">国家公益林直补到户工作进度报表</a></li>
                          <li><a href="${basePath}/report/localSmallClassGrantReport.shtml">地方公益林发放面积小于小班面积统计表</a></li>
                          <li><a href="${basePath}/report/countrySmallClassGrantReport.shtml">国家公益林发放面积小于小班面积统计表</a></li>
                          <li><a href="${basePath}/report/localGrantAreaSumReport.shtml">地方公益林直补到户发放面积与金额汇总报表</a></li>
                          <li><a href="${basePath}/report/countryGrantAreaSumReport.shtml">国家公益林直补到户发放面积与金额汇总报表</a></li>
                          <li><a href="${basePath}/report/grantOkAreaSumDetail.shtml">国家及地方直补到户资金发放明细</a></li>
                          <li><a href="${basePath}/report/grantNoOkAreaSumDetail.shtml">国家及地方直补到户资金未发放明细</a></li>
                          <li><a href="${basePath}/report/localGrantAreaSumResearchReport.shtml">地方公益林直补到户调查面积与金额汇总报表</a></li>
                          <li><a href="${basePath}/report/countryGrantAreaSumResearchReport.shtml">国家公益林直补到户调查面积与金额汇总报表</a></li>
                          <li><a href="${basePath}/report/localZbdhDataInputReport.shtml">地方公益林直补到户相关数据录入率统计表</a></li>
                          <li><a href="${basePath}/report/countryZbdhDataInputReport.shtml">国家公益林直补到户相关数据录入率统计表</a></li>
                          <li><a href="${basePath}/smallClass/smallClassDataDetail.shtml">国家及地方小班数据明细</a></li>

                      </ul>
                  </li>
                  <li class="dropdown ${(index==1)?string('active','')}">
					<a aria-expanded="false" aria-haspopup="true" role="button" data-toggle="dropdown" class="dropdown-toggle" href="${basePath}/user/index.shtml">
						个人中心<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<li><a href="${basePath}/user/index.shtml">个人资料</a></li>
						<li><a href="${basePath}/user/updateSelf.shtml" >资料修改</a></li>
						<li><a href="${basePath}/user/updatePswd.shtml" >密码修改</a></li>
						<li><a href="${basePath}/role/mypermission.shtml">我的权限</a></li>
					</ul>
				</li>	  
				<#--拥有 角色888888（管理员） ||  100002（用户中心）-->
				<@shiro.hasAnyRoles name='888888,100002'>          
				<li class="dropdown ${(index==2)?string('active','')}">
					<a aria-expanded="false" aria-haspopup="true"  role="button" data-toggle="dropdown" class="dropdown-toggle" href="${basePath}/member/list.shtml">
						用户中心<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<@shiro.hasPermission name="/member/list.shtml">
							<li><a href="${basePath}/member/list.shtml">用户列表</a></li>
						</@shiro.hasPermission>
						<@shiro.hasPermission name="/member/online.shtml">
							<li><a href="${basePath}/member/online.shtml">在线用户</a></li>
						</@shiro.hasPermission>
					</ul>
				</li>	
				</@shiro.hasAnyRoles>         
				<#--拥有 角色888888（管理员） ||  100003（权限频道）-->
				<@shiro.hasAnyRoles name='888888,100003'>
					<li class="dropdown ${(index==3)?string('active','')}">
						<a aria-expanded="false" aria-haspopup="true"  role="button" data-toggle="dropdown" class="dropdown-toggle" href="${basePath}/permission/index.shtml">
							权限管理<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<@shiro.hasPermission name="/role/index.shtml">
								<li><a href="${basePath}/role/index.shtml">角色列表</a></li>
							</@shiro.hasPermission>
							<@shiro.hasPermission name="/role/allocation.shtml">
								<li><a href="${basePath}/role/allocation.shtml">角色分配（这是个JSP页面）</a></li>
							</@shiro.hasPermission>
							<@shiro.hasPermission name="/permission/index.shtml">
								<li><a href="${basePath}/permission/index.shtml">权限列表</a></li>
							</@shiro.hasPermission>
							<@shiro.hasPermission name="/permission/allocation.shtml">
								<li><a href="${basePath}/permission/allocation.shtml">权限分配</a></li>
							</@shiro.hasPermission>
						</ul>
					</li>	
				</@shiro.hasAnyRoles>
	          </ul>
	           <ul class="nav navbar-nav  pull-right" >
				<li class="dropdown ${(index==10)?string('active','')}" style="color:#fff;">
					<a aria-expanded="false" aria-haspopup="true"  role="button" data-toggle="dropdown"  
						<@shiro.user>  
							onclick="location.href='${basePath}/user/index.shtml'" href="${basePath}/user/index.shtml" class="dropdown-toggle qqlogin" >
							${token.nickname?default('阿西吧')}<span class="caret"></span></a>
							<ul class="dropdown-menu" userid="${token.id}">
								<li><a href="${basePath}/user/index.shtml">个人资料</a></li>
								<li><a href="${basePath}/role/mypermission.shtml">我的权限</a></li>
								<li><a href="javascript:void(0);" onclick="logout();">退出登录</a></li>
							</ul>
						</@shiro.user>  
						<@shiro.guest>   
							 href="javascript:void(0);" onclick="location.href='${basePath}/u/login.shtml'" class="dropdown-toggle qqlogin" >
							<img src="#">&nbsp;登录</a>
						</@shiro.guest>  					
				</li>	
	          </ul>
	          <style>#topMenu>li>a{padding:10px 13px}</style>
	    </div>
  	</div>
</div>
</#macro>
