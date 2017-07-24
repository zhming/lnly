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
                <div class="input-group date form_datetime ">
                    <input id="yearSelect" class="form-control" size="16" type="text" value="2017" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
                <input type="hidden" id="dtp_input1" value=""/><br/>
            </div>

            <div class="form-group col-sm-12">
                <div id="getPermissionTree" class="input-group date ">
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <div class="col-sm-12">
                <div class="table">
                    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                        <thead>
                        <tr><strong id="tableTitle">2017年度国家补偿标准</strong></tr>
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

<script  src="${basePath}/js/datagrid/jszip.min.js"></script>
<script  src="${basePath}/js/datagrid/buttons.html5.min.js"></script>

<!-- datagrid -->

<script type="text/javascript">

    $('.form_datetime').datetimepicker({
        format: 'yyyy',
        autoclose: true,
        startView: 4,
        minView: 4,
        forceParse: false,
        language: 'zh-CN'
    });

    $(function () {
        var load = layer.load();
    <@shiro.hasPermission name="/adminDict/findAll.shtml">
        $.post("${basePath}/adminDict/findAll.shtml", {}, function (data) {
            layer.close(load);
            if (data && !data.length) {
                return $("#getPermissionTree").html('<code>您没有任何权限。只有默认的个人中心。</code>'), !1;
            }
            $('#getPermissionTree').treeview({
                levels: 1,//层级
                color: "#428bca",
                nodeIcon: "glyphicon glyphicon-tree-conifer",
                showTags: false,//显示数量
                data: data//数据
            });
        }, 'json');

    </@shiro.hasPermission>
    });



    function itemOnclick(target) {
        //找到当前节点id
        var nodeid = $(target).attr('data-nodeid');
        var tree = $('#getPermissionTree');
        //获取当前节点对象
        var node = tree.treeview('getNode', nodeid);

        var nodeName = node.text;
        var yearSelect = $("#yearSelect").val();
        var type = $('input:radio:checked').val();
        var tableTitle = nodeName + yearSelect + "年度" + type + "资金补偿标准";
        $("#tableTitle").html(tableTitle);
        if (node.state.expanded) {
            //处于展开状态则折叠
            tree.treeview('collapseNode', node.nodeId);
        } else {
            //展开
            tree.treeview('expandNode', node.nodeId);
        }
    }



    function format ( rowData ) {
        var div = $('<div/>')
                .addClass( 'loading' )
                .text( 'Loading...' );

        $.ajax( {
            url: '/media/blog/2017-03-31/details.php',
            data: {
                name: rowData.name
            },
            dataType: 'json',
            type: 'post',
            success: function ( json ) {
                div
                        .html( json.html )
                        .removeClass( 'loading' );
            }
        } );

        return div;
    }



    $(document).ready(function() {
        var table = $('#example').DataTable( {
            dom: 'Blrtip',
            "bLengthChange": true,
            "lengthMenu": [[100, 500, 1000, -1], [100, 500, 1000, "所有"]],
            "bSort": false,
            //"sPaginationType": "full_numbers",
            "bProcessing": true,
            "bServerSide": true,
            "sAjaxSource": "${basePath}/countryStandard/findAllPage.shtml",
            "language": {
                "url": "${basePath}/js/datagrid/Chinese.json"
            },
            "sServerMethod": "POST",
            "columns": [
                { "data": "id" },
                { "data": "year" },
                { "data": "city" },
                { "data": "county" },
                { "data": "area" },
                { "data": "countryZbje" },
                { "data": "createTime" }
            ],
            "order": [[1, 'asc']],

            buttons: [{
                'extend': 'excelHtml5',//导出文件格式为excel
                'text': '导出',  //按钮标题
                'title': 'XXX-', //导出的excel标题
                'className': 'btn btn-primary', //按钮的class样式
                'exportOptions':{ //从DataTable中选择要收集的数据。这包括列、行、排序和搜索的选项。请参阅button.exportdata()方法以获得完整的详细信息——该参数所提供的对象将直接传递到该操作中，以收集所需的数据，更多options选项参见：https://datatables.net/reference/api/buttons.exportData()
                    'format': { //用于导出将使用的单元格格式化函数的容器对象 format有三个子标签，header，body和foot
                        body: function ( data, row, column, node ) { //body区域的function，可以操作需要导出excel的数据格式
                            if(column === 4 && (data == null || data == "" || data == "0%")){
                                return 0;
                            }
                            else{
                                return data;
                            }
                        }
                    }
                }
            }]
            /*
            buttons: [ {
                extend: 'excelHtml5',
                customize: function( xlsx ) {
                    var sheet = xlsx.xl.worksheets['sheet1.xml'];

                    $('row c[r^="C"]', sheet).attr( 's', '2' );
                }
            } ]*/
        } );

        // Add event listener for opening and closing details
        $('#example tbody').on('click', 'td.details-control', function () {
            var tr = $(this).closest('tr');
            var row = table.row( tr );

            if ( row.child.isShown() ) {
                // This row is already open - close it
                row.child.hide();
                tr.removeClass('shown');
            }
            else {
                // Open this row
                row.child( format(row.data()) ).show();
                tr.addClass('shown');
            }
        } );
    } );

</script>

</body>
</html>