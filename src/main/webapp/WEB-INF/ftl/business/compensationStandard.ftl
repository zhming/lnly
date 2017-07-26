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

    <script src="${basePath}/js/datagrid/jszip.min.js"></script>
    <script src="${basePath}/js/datagrid/buttons.html5.min.js"></script>
    <script src="${basePath}/js/common/jquery/jquery.form-2.82.js?${_v}"></script>
    <script src="${basePath}/js/bootstrapValidator.min.js"></script>


    <!-- datagrid -->

    <style>
        div.dataTables_length {
            padding-left: 2em;
        }

        div.dataTables_length,
        div.dataTables_filter {
            padding-top: 0.55em;
        }

        /* 个性化 */
        .btn-primary {
            background-color: #006dcc;
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
                        <tr>
                            <div class="col-lg-7">
                                <strong id="tableTitle">2017年度国家补偿标准</strong>

                            </div>

                            <div class="btn-group col-lg-5">
                                <a class="btn btn-default btn-query " tabindex="0" href="#"><span>查看</span></a>
                            <@shiro.hasPermission name="/role/addRole.shtml">
                                <a class="btn btn-default btn-insert " tabindex="0" href="#"
                                   onclick="viewAddModal();"><span>新增</span></a>
                            </@shiro.hasPermission>

                                <a class="btn btn-default btn-update " tabindex="0" onclick="viewUpdateModal();" href="#"><span>修改</span></a>
                                <a class="btn btn-default btn-delete " tabindex="0"  onclick="deleteById();" href="#"><span>删除</span></a>
                               
                            </div>

                        </tr>

                        <tr >
                            <div id="tr_h" >
                                <th>ID</th>
                                <th>年度</th>
                                <th>城市</th>
                                <th>区县</th>
                                <th>面积</th>
                                <th>国有指标金额</th>
                                <th>其他指标金额</th>
                                <th>金额</th>
                                <th>备注</th>
                                <th>创建时间</th>
                            </div>

                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div class="hidden">
                <input type="hidden" id="selectedDictCode" value=""/>
            </div>


        <@shiro.hasPermission name="/countryStandard/add.shtml">
        <#--添加弹框-->
            <div class="modal fade" id="addRecord" tabindex="-1" role="dialog" aria-labelledby="addroleLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="addBcbzLabel">新增-国家补偿标准维护</h4>
                        </div>
                        <form id="addGjbcBzForm" enctype="multipart/form-data"
                              action="${basePath}/countryStandard/add.shtml" method="post">
                            <div class="form-group col-sm-12"></div>
                            <div class="form-group col-sm-12">
                                <label for="recipient-name" class="control-label">年度:</label>
                                <div class="input-group date form_datetime ">
                                    <input id="year" name="year" class="form-control" size="16" type="text" value="2017"
                                           readonly>
                                    <span class="input-group-addon"><span
                                            class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                                </div>
                                <input type="hidden" id="dtp_input1" value=""/>
                            </div>
                            <div class="form-group col-sm-12">
                                <label for="recipient-name" class="control-label">市:</label>
                                <input type="text" class="form-control" id="city" name="city" placeholder="请输入城市">
                            </div>
                            <div class="form-group col-sm-12">
                                <label for="recipient-name" class="control-label">区县:</label>
                                <input type="text" class="form-control" id="county" name="county" placeholder="请输入区县名称">
                            </div>
                            <div class="form-group col-sm-12">
                                <label for="recipient-name" class="control-label">面积: 亩</label>
                                <input type="text" class="form-control" id="area" name="area"
                                       placeholder="请输入面积  [ 数字]">
                            </div>
                            <div class="form-group col-sm-12 gj">
                                <label for="recipient-name" class="control-label">国有指标金额: 元</label>
                                <input type="text" class="form-control" id="countryZbje" name="countryZbje"
                                       placeholder="请输入金额  [数字]">
                            </div>
                            <div class="form-group col-sm-12 gj">
                                <label for="recipient-name" class="control-label">其他指标金额: 元</label>
                                <input type="text" class="form-control" id="otherZbje" name="otherZbje"
                                       placeholder="请输入金额  [数字]">
                            </div>
                            <div class="form-group col-sm-12 df">
                                <label for="recipient-name" class="control-label">金额: 元</label>
                                <input type="text" class="form-control" id="je" name="je"
                                       placeholder="请输入金额  [数字]">
                            </div>
                            <div class="form-group col-sm-12">
                                <label for="recipient-name" class="control-label">备注:</label>
                                <input type="text" class="form-control" id="comment" name="comment"
                                       placeholder="请输入备注  ">
                            </div>
                            <div class="hidden">
                                <input type="hidden" id="createUser" name="createUser" value="${token.id}"/>
                                <input type="hidden" id="updateUser" name="updateUser" value="${token.id}"/>

                                <input type="hidden" id="id" name="id" value=""/>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                                <button type="submit" class="btn btn-primary">保存</button>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
        <#--/添加弹框-->
        </@shiro.hasPermission>
        </div>
    </div>
<#--/row-->
</div>


<script type="text/javascript">

    var table_flag = false;
    var table;


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
                return $("#getPermissionTree").html('<code>您没有权限。</code>'), !1;
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


        $(".btn-query").click(function () {
            if (table_flag) {
                $('#example').dataTable({
                    "srollY": "200px",
                    "bRetrieve": true
                });

// Some time later, recreate with just filtering (no scrolling)
                $('#example').dataTable({
                    "filter": false,
                    "bRetrieve": true,
                    "destroy": true
                });
            }
            getData();
        });


        var load;
        $("#addGjbcBzForm").ajaxForm({
            success: function (result) {
                layer.close(load);
                if (result && result.status == 300) {
                    layer.msg(result.message, function () {
                    });
                    return !1;
                }
                if (result && result.status == 500) {
                    layer.msg("操作失败！", function () {
                    });
                    return !1;
                }
                layer.msg('操作成功！');
            },
            beforeSubmit: function () {
                //判断参数
                if ($.trim($("#year").val()) == '') {
                    layer.msg('年份为空！', function () {
                    });
                    $("#year").parent().removeClass('has-success').addClass('has-error');
                    return !1;
                } else {
                    $("#year").parent().removeClass('has-error').addClass('has-success');
                }

                if ($.trim($("#city").val()) == '') {
                    layer.msg('城市不能为空！', function () {
                    });
                    $("#city").parent().removeClass('has-success').addClass('has-error');
                    return !1;
                } else {
                    $("#city").parent().removeClass('has-error').addClass('has-success');
                }

                if ($.trim($("#county").val()) == '') {
                    layer.msg('区县不能为空！', function () {
                    });
                    $("#county").parent().removeClass('has-success').addClass('has-error');
                    return !1;
                } else {
                    $("#county").parent().removeClass('has-error').addClass('has-success');
                }

                if ($.trim($("#area").val()) == '') {
                    layer.msg('面积不能为空！', function () {
                    });
                    $("#area").parent().removeClass('has-success').addClass('has-error');
                    return !1;
                } else {
                    if (!checkIsDouble($("#area").val())) {
                        layer.msg('面积必须是数字！', function () {
                        });
                        $("#area").parent().removeClass('has-success').addClass('has-error');
                        return !1;
                    }

                    $("#area").parent().removeClass('has-error').addClass('has-success');
                }

                var type = $('input:radio:checked').val();

                if ("国家" == type) {
                    if ($.trim($("#countryZbje").val()) == '') {
                        layer.msg('国有指标金额不能为空！', function () {
                        });
                        $("#countryZbje").parent().removeClass('has-success').addClass('has-error');
                        return !1;
                    } else {
                        if (!checkIsDouble($("#countryZbje").val())) {
                            layer.msg('国有指标金额必须是数字！', function () {
                            });
                            $("#countryZbje").parent().removeClass('has-success').addClass('has-error');
                            return !1;
                        }
                        $("#countryZbje").parent().removeClass('has-error').addClass('has-success');
                    }

                    if ($.trim($("#otherZbje").val()) != '') {
                        if (!checkIsDouble($("#otherZbje").val())) {
                            layer.msg('其他指标金额必须是数字！', function () {
                            });
                            $("#otherZbje").parent().removeClass('has-success').addClass('has-error');
                            return !1;
                        }
                        $("#otherZbje").parent().removeClass('has-error').addClass('has-success');
                    } else {
                        $("#otherZbje").parent().removeClass('has-error').addClass('has-success');
                    }

                } else {
                    if ($.trim($("#je").val()) == '') {
                        layer.msg('金额不能为空！', function () {
                        });
                        $("#je").parent().removeClass('has-success').addClass('has-error');
                        return !1;
                    } else {
                        if (!checkIsDouble($("#je").val())) {
                            layer.msg('金额必须是数字！', function () {
                            });
                            $("#je").parent().removeClass('has-success').addClass('has-error');
                            return !1;
                        }
                        $("#je").parent().removeClass('has-error').addClass('has-success');
                    }
                }

                load = layer.load();
            },
            dataType: "json",
            clearForm: false
        });


    });


    function getData() {
        var yearSelect = $("#yearSelect").val();
        var dictCode = $("#selectedDictCode").val();

        console.log(dictCode);
        var type = $('input:radio:checked').val();
        var urlStrGj = "${basePath}/countryStandard/findAll.shtml?year=" + yearSelect + "&dictCode=" + dictCode;
        var urlStrDf = "${basePath}/localStandard/findAll.shtml?year=" + yearSelect + "&dictCode=" + dictCode;
        var urlStr = "";
        if ("地方" == type) {
            urlStr =  urlStrDf;
        }else{
            urlStr =  urlStrGj;
        }



        table = $('#example').DataTable({
            "bLengthChange": true,
            lengthChange: false,
            "processing": true,
            "bRetrieve": true,
            "bServerSide" : true,
            "bDestroy" : true,
            ajax: urlStr,
            "language": {
                "url": "${basePath}/js/datagrid/Chinese.json"
            },
            columns: [
//                { data: null, render: function ( data, type, row ) {
//                    // Combine the first and last names into a single table field
//                    return data.first_name+' '+data.last_name;
//                } },
                {data: "id"},
                {data: "year"},
                {data: "city"},
                {data: "county"},
                {data: "area"},
                {data: "countryZbje"},
                {data: "otherZbje"},
                {data: "je"},
                {data: "comment"},
                {data: "createTimeStr"}
                //{ data: "salary", render: $.fn.dataTable.render.number( ',', '.', 0, '$' ) }
            ],
            "columnDefs": [
                {"orderable": false, "targets": [0, 1, 2, 3]},
            ],
            "aLengthMenu": [[10, 25, 50, 100, -1], [10, 25, 50, 100, "所有"]],
            select: true,
            dom: 'Bltip',
            buttons: [{
                'extend': 'excelHtml5',//导出文件格式为excel
                'text': '导出',  //按钮标题
                'title': 'XXX-', //导出的excel标题
                'className': 'btn btn-primary', //按钮的class样式
                'exportOptions': { //从DataTable中选择要收集的数据。这包括列、行、排序和搜索的选项。请参阅button.exportdata()方法以获得完整的详细信息——该参数所提供的对象将直接传递到该操作中，以收集所需的数据，更多options选项参见：https://datatables.net/reference/api/buttons.exportData()
                    'format': { //用于导出将使用的单元格格式化函数的容器对象 format有三个子标签，header，body和foot
                        body: function (data, row, column, node) { //body区域的function，可以操作需要导出excel的数据格式
                            if (column === 4 && (data == null || data == "" || data == "0%")) {
                                return 0;
                            }
                            else {
                                return data;
                            }
                        }
                    }
                }
            }]
        });


        table.buttons().container()
                .appendTo($('.col-sm-6:eq(0)', table.table().container()));
        table_flag = true;


        $('#example tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            }
            else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
            var rowData = table.row(this).data();

            $("#id").val(rowData.id);

            $("#city").val(rowData.city);

            $("#county").val(rowData.county);

            $("#area").val(rowData.area);

            $("#countryZbje").val(rowData.countryZbje);

            $("#otherZbje").val(rowData.otherZbje);

            $("#je").val(rowData.je);

            $("#comment").val(rowData.comment);


        });

        if ("地方" == type) {
            var column = table.column(5);
            column.visible(!column.visible());

            column = table.column(6);
            column.visible(!column.visible());

            column = table.column(7);
            column.visible(column.visible());
        } else{
            var column = table.column(5);
            column.visible(column.visible());

            column = table.column(6);
            column.visible(column.visible());
            column = table.column(7);
            column.visible(!column.visible());
        }




    }


    function itemOnclick(target) {
        //找到当前节点id
        var nodeid = $(target).attr('data-nodeid');
        var tree = $('#getPermissionTree');
        //获取当前节点对象
        var node = tree.treeview('getNode', nodeid);

        var nodeName = node.text;
        var yearSelect = $("#yearSelect").val();
        $("#selectedDictCode").val(nodeName);
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


    function viewAddModal() {
        $("#id").val("");
        var type = $('input:radio:checked').val();
        $("#addBcbzLabel").html("新增-" + type + "资金补偿标准");

        if ("地方" == type) {
            $("#addGjbcBzForm").attr("action", "${basePath}/localStandard/add.shtml");
            $(".gj").css("display", "none");
            $(".df").css("display", "");
        } else {
            $(".gj").css("display", "");
            $(".df").css("display", "none");
        }

        $('#addRecord').modal();
    }


    function viewUpdateModal() {
        var type = $('input:radio:checked').val();
        $("#addBcbzLabel").html("修改-" + type + "资金补偿标准");

        if ("地方" == type) {
            $("#addGjbcBzForm").attr("action", "${basePath}/localStandard/update.shtml");
            $(".gj").css("display", "none");
            $(".df").css("display", "");
        } else {
            $("#addGjbcBzForm").attr("action", "${basePath}/countryStandard/update.shtml");
            $(".gj").css("display", "");
            $(".df").css("display", "none");
        }

        $('#addRecord').modal();
    }


   

    function checkIsDouble(value) {
        var reg = /^[-\+]?\d+(\.\d+)?$/;

        if (reg.test(value)) {
            return true;
        } else {
            return false;
        }

    }


    <@shiro.hasPermission name="/role/deleteRoleById.shtml">
    <#--根据ID删除数据-->
    function deleteById(){
        var id = $("#id").val();
        if(null == id || "" == id){
            return;
        }
        var type = $('input:radio:checked').val();
        var deleteUrl = "";
        if ("地方" == type) {
           deleteUrl = "${basePath}/localStandard/delete.shtml";
        } else {
            deleteUrl = "${basePath}/countryStandard/delete.shtml";
        }


        var index = layer.confirm("确定删除这条数据？",function(){
            var load = layer.load();
            $.post(deleteUrl,{id:id},function(result){
                layer.close(load);
                if(result && result.status != 200){
                    return layer.msg(result.message,so.default),!0;
                }else{
                    layer.msg(result.message);
                    table.row('.selected').remove().draw( false );
                }
            },'json');
            layer.close(index);
        });
    }
    </@shiro.hasPermission>


    

</script>

</body>
</html>