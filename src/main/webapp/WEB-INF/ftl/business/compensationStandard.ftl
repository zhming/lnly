<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>辽宁省林业厅生态公益林管理系统-资金补偿标准维护</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport"/>
    <link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${basePath}/favicon.ico"/>
    <link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
    <link href="${basePath}/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" href="${basePath}/css/build.css"/>
    <link rel="stylesheet" href="${basePath}/css/font-awesome.min.css"/>

    <!-- datagrid css -->
    <link rel="stylesheet" href="${basePath}/css/datagrid/buttons.bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/datagrid/dataTables.bootstrap.min.css"/>
    <link rel="stylesheet" href="${basePath}/css/datagrid/select.bootstrap.min.css"/>
    <!-- datagrid css -->

    <style>
        div.dataTables_length {
            padding-left: 2em;
        }
        div.dataTables_length,
        div.dataTables_filter {
            padding-top: 0.55em;
        }

    </style>

</head>
<body data-target="#one" data-spy="scroll">

<@_top.top 1/>
<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
<#--row-->
    <div class="row">

        <div class="col-md-12">
            <h2>资金补偿标准维护</h2>
            <hr>

        </div>
        <div class="col-md-4">


            <div class="col-sm-12">
                <div class="radio radio-info radio-inline">
                    <input type="radio" name="radio1" id="radio1" value="国家" checked>
                    <label for="radio1">
                        国家标准
                    </label>
                </div>
                <div class="radio radio-info radio-inline">
                    <input type="radio" name="radio1" id="radio2" value="地方">
                    <label for="radio2">
                        地方标准
                    </label>
                </div>
            </div>

            <div class="form-group col-sm-12">
                <div class="input-group date form_datetime " >
                    <input id="yearSelect" class="form-control" size="16" type="text" value="2017" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
                <input type="hidden" id="dtp_input1" value="" /><br/>
            </div>

            <div class="form-group col-sm-12">
                <div id="getPermissionTree" class="input-group date ">
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <div class="col-sm-12">
                <div class="table" >
                    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                        <thead>
                        <tr ><strong id="tableTitle">2017年度国家补偿标准</strong></tr>
                        <tr>
                            <th>ID</th>
                            <th>年度</th>
                            <th>城市</th>
                            <th>区县</th>
                            <th>面积</th>
                            <th>补偿金额</th>
                            <th>创建时间</th>
                        </tr>
                        </thead>
                    </table>
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

<!-- datagrid -->
<script src="${basePath}/js/datagrid/jquery.dataTables.min.js"></script>
<script src="${basePath}/js/datagrid/dataTables.bootstrap.min.js"></script>
<script src="${basePath}/js/datagrid/dataTables.buttons.min.js"></script>
<script src="${basePath}/js/datagrid/buttons.bootstrap.min.js"></script>
<script src="${basePath}/js/datagrid/dataTables.select.min.js"></script>

<!-- datagrid -->

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

    $(document).ready(function() {


        var table = $('#example').DataTable( {
            "bLengthChange": true,
            lengthChange: false,
            ajax: "../countryStandard/findAll.shtml",
            "language": {
                "url": "${basePath}/js/datagrid/Chinese.json"
            },
            columns: [
//                { data: null, render: function ( data, type, row ) {
//                    // Combine the first and last names into a single table field
//                    return data.first_name+' '+data.last_name;
//                } },
                { data: "id" },
                { data: "year" },
                { data: "city" },
                { data: "county" },
                { data: "area" },
                { data: "countryZbje" },
                { data: "createTimeStr" }
                //{ data: "salary", render: $.fn.dataTable.render.number( ',', '.', 0, '$' ) }
            ],
            "columnDefs": [
                { "orderable": false, "targets": [0, 1, 2, 3] }
            ] ,
            "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50,100, "所有"]],
            select: true
        } );

        table.buttons().container()
                .appendTo( $('.col-sm-6:eq(0)', table.table().container() ) );
    } );



    function itemOnclick(target){
        //找到当前节点id
        var nodeid = $(target).attr('data-nodeid');
        var tree = $('#getPermissionTree');
        //获取当前节点对象
        var node = tree.treeview('getNode', nodeid);

        var nodeName = node.text;
        var  yearSelect = $("#yearSelect").val();
        var type = $('input:radio:checked').val();
        var tableTitle = nodeName + yearSelect + "年度" +  type +  "资金补偿标准";
        $("#tableTitle").html(tableTitle);
        if(node.state.expanded){
            //处于展开状态则折叠
            tree.treeview('collapseNode', node.nodeId);
        } else {
            //展开
            tree.treeview('expandNode', node.nodeId);
        }
    }

</script>

</body>
</html>