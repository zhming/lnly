<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>辽宁省林业厅生态公益林管理系统-国家及地方小班数据明细</title>
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
    <div class="row" style="margin-lef: -10px;">

        <div class="col-md-12">
            <h2>国家及地方小班数据明细</h2>
            <hr>

        </div>
        <div class="col-md-4">
            <div class="form-group col-sm-12">
                <div class="input-group date form_datetime col-sm-8">
                    <input id="yearSelect" class="form-control" size="16" type="text" value="" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
                <input type="hidden" id="dtp_input1" value=""/><br/>
                <div  class="row col-sm-8">
                    <input type="text" id="searchTree" name="searchTree" value=""
                           class="form-control input-small" style="width:225px"
                           placeholder="请输入乡镇、区县、市名称"/>
                </div>
                <div id="getDictTree" class="input-group date col-sm-8">
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <div class="col-sm-12">
                <div class="table">
                    <table id="example" class="table table-striped table-bordered" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <div class="col-lg-9">
                                <strong id="tableTitle">2017年度国家及地方小班数据明细</strong>

                                <div class="hidden" id="hidden_filter">
                                    <div class="row" style="margin-right:0;">
                                        <input type="hidden" id="searchYear" name="searchYear" value=""
                                               class="form-control input-small" style="width:150px" placeholder=""/>
                                        <input type="hidden" id="searchId" name="searchId" value=""
                                               class="form-control input-small" style="width:150px" placeholder=""/>
                                        <input type="hidden" id="searchContentFromSelect" name="searchContentFromSelect"
                                               value="${token.dictCode}" class="form-control input-small"
                                               style="width:150px" placeholder=""/>
                                        <button id="go_search" class="btn btn-default">查询</button>
                                        <div class="hidden">
                                            <button id="addButton" onclick="viewAddModal();" class="btn btn-default">
                                                新增
                                            </button>
                                            <button id="updateButton" onclick="viewUpdateModal();"
                                                    class="btn btn-default">修改
                                            </button>
                                            <button id="deleteButton" onclick="deleteById();" class="btn btn-default">
                                                删除
                                            </button>
                                        </div>

                                    </div>

                                </div>

                            </div>

                        </tr>


                        </thead>
                    </table>
                </div>
            </div>
            <div class="hidden">
                <input type="hidden" id="selectedDictCode" value=""/>
            </div>


        <@shiro.hasPermission name="/localStandard/add.shtml">
        <#--添加弹框-->
            <div class="modal fade" id="addRecord" tabindex="-1" role="dialog" aria-labelledby="addroleLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                    aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="addBcbzLabel">新增-国家及地方小班数据明细</h4>
                        </div>
                        <form id="addRecordForm" enctype="multipart/form-data"
                              action="${basePath}/smallClass/add.shtml" method="post">
                            <div class="form-group col-sm-12"></div>
                            <div class="form-group col-sm-6">
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
                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">市:</label>
                                <input type="text" class="form-control" id="city" name="city" placeholder="">
                            </div>
                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">区县:</label>
                                <input type="text" class="form-control" id="county" name="county" placeholder="">
                            </div>
                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">乡:</label>
                                <input type="text" class="form-control" id="town" name="town" placeholder="">
                            </div>
                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">村:</label>
                                <input type="text" class="form-control" id="village" name="village" placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">林班:</label>
                                <input type="text" class="form-control" id="forestClass" name="forestClass"
                                       placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">小班:</label>
                                <input type="text" class="form-control" id="smallClass" name="smallClass"
                                       placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">地类:</label>
                                <input type="text" class="form-control" id="landTypes" name="landTypes" placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">细班:</label>
                                <input type="text" class="form-control" id="littleClass" name="littleClass"
                                       placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">林地所有权:</label>
                                <input type="text" class="form-control" id="forestBelong" name="forestBelong"
                                       placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">土地所有权:</label>
                                <input type="text" class="form-control" id="landBelong" name="landBelong"
                                       placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">起源:</label>
                                <input type="text" class="form-control" id="source" name="source" placeholder="">
                            </div>


                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">权属证明:</label>
                                <input type="text" class="form-control" id="belongProve" name="belongProve"
                                       placeholder="">
                            </div>


                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">身份证号码:</label>
                                <input type="text" class="form-control" id="identityCard" name="identityCard"
                                       placeholder="">
                            </div>


                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">户名:</label>
                                <input type="text" class="form-control" id="username" name="username" placeholder="">
                            </div>


                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">联户户名:</label>
                                <input type="text" class="form-control" id="uniteUsername" name="uniteUsername"
                                       placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">面积: 亩</label>
                                <input type="text" class="form-control" id="area" name="area"
                                       placeholder="请输入面积  [ 数字]">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">地方补偿标准:</label>
                                <input type="text" class="form-control" id="compensationStandard"
                                       name="compensationStandard" value="11" placeholder="" readonly>
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">补偿金额:</label>
                                <input type="text" class="form-control" id="compensationAmount"
                                       name="compensationAmount" placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">汇款帐号:</label>
                                <input type="text" class="form-control" id="remitNum" name="remitNum" placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">汇款户名:</label>
                                <input type="text" class="form-control" id="remitUserName" name="remitUserName"
                                       placeholder="">
                            </div>


                            <div class="form-group col-sm-4 gj">
                                <label for="recipient-name" class="control-label">是否发放:</label>
                                <select id="sendFlag" name="sendFlag" class="form-control">
                                    <option value="0">否</option>
                                    <option value="1">是</option>
                                </select>
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

    //初始化年份
    var nowYear = 1900 + new Date().getYear();
    $("#yearSelect").val(nowYear);
    var tableTitle = nowYear + "年度国家及地方小班数据明细";
    $("#tableTitle").html(tableTitle);


    $('.form_datetime').datetimepicker({
        format: 'yyyy',
        autoclose: true,
        startView: 4,
        minView: 4,
        forceParse: false,
        language: 'zh-CN'
    });

    $(function () {
        var dictCode = "${token.dictCode}";


        var load = layer.load();
    <@shiro.hasPermission name="/adminDict/findDictTree.shtml">
        $.post("${basePath}/adminDict/findDictTree.shtml", {dictCode: dictCode}, function (data) {
            layer.close(load);
            if (data && !data.length) {
                return $("#getDictTree").html('<code>您没有权限。</code>'), !1;
            }
            $('#getDictTree').treeview({
                levels: 1,//层级
                color: "#428bca",
                nodeIcon: "glyphicon glyphicon-tree-conifer",
                showTags: false,//显示数量
                data: data//数据
            });
        }, 'json');

    </@shiro.hasPermission>
    <@shiro.hasPermission name="/smallClass/smallClassDataDetail.shtml">
        var load;
        $("#addRecordForm").ajaxForm({
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


                if ($.trim($("#compensationAmount").val()) == '') {
                    layer.msg('补偿金额不能为空！', function () {
                    });
                    $("#compensationAmount").parent().removeClass('has-success').addClass('has-error');
                    return !1;
                } else {
                    if (!checkIsDouble($("#compensationAmount").val())) {
                        layer.msg('补偿金额必须是数字！', function () {
                        });
                        $("#compensationAmount").parent().removeClass('has-success').addClass('has-error');
                        return !1;
                    }
                    $("#compensationAmount").parent().removeClass('has-error').addClass('has-success');
                }


                load = layer.load();
            },
            dataType: "json",
            clearForm: false
        });

        var tablePrefix = "#example_";
        $("#example").dataTable({
            serverSide: true,//分页，取数据等等的都放到服务端去
            processing: true,//载入数据的时候是否显示“载入中”
            pageLength: 10,//首次加载的数据条数
            ordering: false,//排序操作在服务端进行，所以可以关了。
            scrollX: true, //水平滚动条
            autoWidth: false, //启用或者禁止自动列宽的计算
            ajax: {//类似jquery的ajax参数，基本都可以用。
                type: "post",//后台指定了方式，默认get，外加datatable默认构造的参数很长，有可能超过get的最大长度。
                url: "${basePath}/smallClass/findAll.shtml",
                dataSrc: "data",//默认data，也可以写其他的，格式化table的时候取里面的数据
                data: function (d) {//d 是原始的发送给服务器的数据，默认很长。
                    var param = {};//因为服务端排序，可以新建一个参数对象
                    param.iDisplayLength = d.length;//页面显示记录条数，在页面显示每页显示多少项的时候
                    param.iDisplayStart = d.start;//开始的记录序号
                    var formData = $("#filter_form").serializeArray();//把form里面的数据序列化成数组
                    formData.forEach(function (e) {
                        param[e.name] = e.value;
                    });
                    return param;//自定义需要传递的参数。
                },
            },
            "fnDrawCallback": function () {
                this.api().column(0).nodes().each(function (cell, i) {
                    cell.innerHTML = i + 1;
                });
            },
            columns: [//对应上面thead里面的序列
                {
                    "sTitle": "序号",
                    "sClass": "dt-center",
                    "bSortable": false,
                    "sWidth": "8%",
                    "data": null,
                    "targets": 0
                },
                {
                    "sTitle": "ID",
                    "sClass": "hidden",
                    "data": "id",

                },//字段名字和返回的json序列的key对应
                {
                    data: "year",
                    "sTitle": "年份"
                },
                {
                    data: "city",
                    "sTitle": "市"
                },
                {
                    data: "county",
                    "sTitle": "县"
                },
                {
                    data: "town",
                    "sTitle": "乡"
                },
                {
                    data: "village",
                    "sTitle": "村"
                },
                {
                    data: "forestClass",
                    "sTitle": "林班"
                },
                {
                    data: "smallClass",
                    "sTitle": "小班"
                },
                {
                    data: "area",
                    "sTitle": "面积(亩)"
                },
                {
                    data: "sqdj",
                    "sTitle": "事权等级"
                },
                {
                    data: "forestBelong",
                    "sTitle": "林木所有权"
                },
                {
                    data: "landBelong",
                    "sTitle": "土地所有权"
                },
                {
                    data: "xdm",
                    "sTitle": "小地名"
                },
                {
                    data: "landZl",
                    "sTitle": "地类"
                },
                {
                    data: "source",
                    "sTitle": "起源"
                },
                {
                    data: "forestZl",
                    "sTitle": "林木种类"
                },
                {
                    data: "comment",
                    "sTitle": "备注"
                }

                /*
                ,
                {
                    data: function (e) {//这里给最后一列返回一个操作列表
                        //e是得到的json数组中的一个item ，可以用于控制标签的属性。
                        return '<a class="btn btn-default btn-xs show-detail-json"><i class="icon-edit"></i>显示详细</a>';
                    }
                }
                */
            ],
            initComplete: function (setting, json) {
                //初始化完成之后替换原先的搜索框。
                //本来想把form标签放到hidden_filter 里面，因为事件绑定的缘故，还是拿出来。
                $(tablePrefix + "filter").html("<form id='filter_form'>" + $("#hidden_filter").html() + "</form>");

            },
            language: {
                url: "${basePath}/js/datagrid/Chinese.json"
            }
        });
        //$("#table_server_filter input[type=search]").css({ width: "auto" });//右上角的默认搜索文本框，不写这个就超出去了。

        $(document).on("submit", "#filter_form", function () {
            return false;
        });
        $(document).on("click", "#go_search", function () {
            var yearSelect = $("#yearSelect").val();
            $("#filter_form [name='searchYear']").val(yearSelect);
            $("#example").DataTable().draw(false);//点搜索重新绘制table。


        });
        $(document).on("click", ".show-detail-json", function () {//取出当前行的数据
            alert(JSON.stringify($("#example").DataTable().row($(this).parents("tr")).data()));
        });

        $('#example tbody').on('click', 'tr', function () {
            var table = $("#example").dataTable();
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            }
            else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
            var rowData = $("#example").DataTable().row($(this)).data();

            $("#id").val(rowData.id);
            $("#city").val(rowData.city);
            $("#county").val(rowData.county);
            $("#town").val(rowData.town);
            $("#village").val(rowData.village);
            $("#forestClass").val(rowData.forestClass);
            $("#smallClass").val(rowData.smallClass);
            $("#area").val(rowData.area);
            $("#sqdj").val(rowData.sqdj);
            $("#forestBelong").val(rowData.forestBelong);
            $("#landBelong").val(rowData.landBelong);
            $("#xdm").val(rowData.xdm);
            $("#landZl").val(rowData.landZl);
            $("#source").val(rowData.source);
            $("#forestZl").val(rowData.landTypes);
            $("#comment").val(rowData.comment);
        });

    </@shiro.hasPermission>


        $("#searchTree").change(function () {
            $("#filter_form [name='searchContentFromSelect']").val("");
            var tree = $('#getDictTree');
            var node = tree.treeview('getNode', 0);
            if (node.state.expanded) {
                //处于展开状态则折叠
                tree.treeview('collapseNode', node.nodeId);
            }
            var param = $(this).val();

            console.log(param);
            tree.treeview('search', [param, {
                ignoreCase: true,     // case insensitive
                exactMatch: false,    // like or equals
                revealResults: true,  // reveal matching nodes
            }]);
            var searchResults = $(".search-result");
            console.log($(searchResults[0]).attr("data-nodeid"));
            var selectedId = $(searchResults[0]).attr("data-nodeid");
            var node = tree.treeview('getNode', selectedId);
            tree.treeview('selectNode', [node, {silent: true}]);

            var nodeName = node.text;
            console.log("@@@@@@@@@@@@"+nodeName);
            $("#filter_form [name='searchContentFromSelect']").val(nodeName);

        });
    });


    function itemOnclick(target) {
        //找到当前节点id
        var nodeid = $(target).attr('data-nodeid');
        var tree = $('#getDictTree');
        //获取当前节点对象
        var node = tree.treeview('getNode', nodeid);
        var tags = node.tags;
        console.log(tags[0]);
        $("#filter_form [name='searchId']").val(tags[0]);
        var nodeName = node.text;
        var yearSelect = $("#yearSelect").val();
        $("#filter_form [name='searchContentFromSelect']").val(nodeName);

        $("#filter_form [name='searchYear']").val(yearSelect);

        var type = $('input:radio:checked').val();
        var tableTitle = nodeName + yearSelect + "年度国家及地方小班数据明细";
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
        $('#addRecord').modal();
    }


    function viewUpdateModal() {
        var type = "地方";
        $("#addBcbzLabel").html("修改-国家及地方小班数据明细");

        $("#addRecordForm").attr("action", "${basePath}/smallClass/update.shtml");

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


    <@shiro.hasPermission name="/smallClass/delete.shtml">
    <#--根据ID删除数据-->
    function deleteById() {
        var id = $("#id").val();
        if (null == id || "" == id) {
            return;
        }
        var deleteUrl = "${basePath}/smallClass/delete.shtml";

        var index = layer.confirm("确定删除这条数据？", function () {
            var load = layer.load();
            $.post(deleteUrl, {id: id}, function (result) {
                layer.close(load);
                if (result && result.status != 200) {
                    return layer.msg(result.message, so.default), !0;
                } else {
                    layer.msg(result.message);
                    $('.selected').remove();
                    $("#example").DataTable().draw(false);//点搜索重新绘制table。
                }
            }, 'json');
            layer.close(index);
        });
    }
    </@shiro.hasPermission>


</script>

</body>
</html>
