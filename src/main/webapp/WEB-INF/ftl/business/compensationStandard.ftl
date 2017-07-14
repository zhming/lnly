<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>资金补偿标准维护</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="${basePath}/css/build.css"/>
    <link rel="stylesheet" href="${basePath}/css/font-awesome.min.css"/>
</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 1/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
<#--row-->
    <div class="row">
        <div class="col-md-10">
            <h2>资金补偿标准维护</h2>
            <hr>

            <div class="col-sm-6">
                <div class="radio">
                    <input type="radio" name="radio1" id="radio1" value="option1" checked>
                    <label for="radio1">
                        Small
                    </label>
                </div>
                <div class="radio">
                    <input type="radio" name="radio1" id="radio2" value="option2">
                    <label for="radio2">
                        Big
                    </label>
                </div>
            </div>

            <div class="form-group">
                <div class="input-group date form_datetime col-md-3" >
                    <input class="form-control" size="16" type="text" value="2017" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
                <input type="hidden" id="dtp_input1" value="" /><br/>
            </div>


            <div class="form-group">
            <div id="getPermissionTree" class="input-group date col-md-3">
            </div>
            </div>
        </div>
    </div>
<#--/row-->
</div>
<script src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
<script src="${basePath}/js/common/layer/layer.js"></script>
<script src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="${basePath}/js/common/bootstrap/bootstrap-treeview.js"></script>
<script src="${basePath}/js/bootstrap-datetimepicker.min.js"></script>
<script src="${basePath}/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>

<script src="${basePath}/js/shiro.demo.js"></script>

<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        format: 'yyyy',
        autoclose: true,
        startView: 4,
        minView:4,
        forceParse: false,
        language: 'zh-CN'
    });

     $(function(){
        var load = layer.load();
    <@shiro.hasPermission name="/adminDict/findAll.shtml">
        $.post("/adminDict/findAll.shtml",{},function(data){
            layer.close(load);
            if(data && !data.length){
                return $("#getPermissionTree").html('<code>您没有任何权限。只有默认的个人中心。</code>'),!1;
            }
            $('#getPermissionTree').treeview({
                levels: 1,//层级
                color: "#428bca",
                nodeIcon: "glyphicon glyphicon-tree-conifer",
                showTags: false,//显示数量
                data: data//数据
            });
        },'json');
    
    </@shiro.hasPermission>
     });

    </script>

</body>
</html>