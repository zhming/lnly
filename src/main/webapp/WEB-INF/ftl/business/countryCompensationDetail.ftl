<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8"/>
    <title>辽宁省林业厅生态公益林管理系统-国家补偿资金发放明细</title>
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
            <h2>国家补偿资金发放明细</h2>
            <hr>

        </div>
        <div class="col-md-4">
            <div class="form-group col-sm-12">
                <div class="input-group date form_datetime ">
                    <input id="yearSelect" class="form-control" size="16" type="text" value="2017" readonly>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                </div>
                <input type="hidden" id="dtp_input1" value=""/><br/>

            </div>
            <div class="form-group col-sm-12">
                <div id="getDictTree" class="input-group date ">
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
                                <strong id="tableTitle">2017年度国家补偿资金发放明细</strong>
                                
                                <div class="hidden" id="hidden_filter">
                                    <div class="row" style="margin-right:0;">
                                        <input type="text" id="searchContent" name="searchContent" value="" class="form-control input-small" style = "width:100px" placeholder = "请输入地名" />
                                        <input type="hidden" id="searchYear" name="searchYear" value="" class="form-control input-small" style = "width:150px" placeholder = "" />
                                        <input type="hidden" id="searchContentFromSelect" name="searchContentFromSelect" value="${token.dictCode}" class="form-control input-small" style = "width:150px" placeholder = "" />
                                        <button id="go_search" class="btn btn-default">查询</button>
                                        <button id="addButton" onclick="viewAddModal();" class="btn btn-default">新增</button>
                                        <button id="updateButton" onclick="viewUpdateModal();" class="btn btn-default">修改</button>
                                        <button id="deleteButton" onclick="deleteById();" class="btn btn-default">删除</button>
                                    </div>

                                </div>

                            </div>

                        </tr>

                        <tr >
                            <div id="tr_h" >
                                <th>ID</th>
                                <th>年度</th>
                                <th>城市</th>
                                <th>区县</th>
                                <th>乡镇</th>
                                <th>村</th>
                                <th>林班</th>
                                <th>小班</th>
                                <th>细班</th>
                                <th>地类</th>
                                <th>林地所有权</th>
                                <th>土地所有权</th>
                                <th>起源</th>
                                <th>权属证明</th>
                                <th>身份证号码</th>
                                <th>户名</th>
                                <th>联户户名</th>
                                <th>面积</th>
                                <th>补偿标准</th>
                                <th>补偿金额</th>
                                <th>汇款帐号</th>
                                <th>汇款户名</th>
                                <th>是否发放</th>
                                <th>审批状态</th>
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
                            <h4 class="modal-title" id="addBcbzLabel">新增-国家补偿资金发放明细</h4>
                        </div>
                        <form id="addGjbcBzForm" enctype="multipart/form-data"
                              action="${basePath}/countryStandard/add.shtml" method="post">
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
                                <input type="text" class="form-control" id="forestClass" name="forestClass" placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">小班:</label>
                                <input type="text" class="form-control" id="smallClass" name="smallClass" placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">地类:</label>
                                <input type="text" class="form-control" id="landTypes" name="landTypes" placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">细班:</label>
                                <input type="text" class="form-control" id="littleClass" name="littleClass" placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">林地所有权:</label>
                                <input type="text" class="form-control" id="forestBelong" name="forestBelong" placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">土地所有权:</label>
                                <input type="text" class="form-control" id="landBelong" name="landBelong" placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">起源:</label>
                                <input type="text" class="form-control" id="source" name="source" placeholder="">
                            </div>


                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">权属证明:</label>
                                <input type="text" class="form-control" id="belongProve" name="belongProve" placeholder="">
                            </div>


                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">身份证号码:</label>
                                <input type="text" class="form-control" id="identityCard" name="identityCard" placeholder="">
                            </div>


                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">户名:</label>
                                <input type="text" class="form-control" id="username" name="username" placeholder="">
                            </div>


                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">联户户名:</label>
                                <input type="text" class="form-control" id="uniteUsername" name="uniteUsername" placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">面积: 亩</label>
                                <input type="text" class="form-control" id="area" name="area"
                                       placeholder="请输入面积  [ 数字]">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">国家补偿标准:</label>
                                <input type="text" class="form-control" id="compensationStandard" name="compensationStandard" placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">补偿金额:</label>
                                <input type="text" class="form-control" id="compensationAmount" name="compensationAmount" placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">汇款帐号:</label>
                                <input type="text" class="form-control" id="remitNum" name="remitNum" placeholder="">
                            </div>

                            <div class="form-group col-sm-4">
                                <label for="recipient-name" class="control-label">汇款户名:</label>
                                <input type="text" class="form-control" id="remitUserName" name="remitUserName" placeholder="">
                            </div>
                            

                            <div class="form-group col-sm-4 gj">
                                <label for="recipient-name" class="control-label">是否发放:</label>
                                <input type="text" class="form-control" id="sendFlag" name="sendFlag"
                                       placeholder="">
                            </div>
                            <div class="form-group col-sm-4 gj">
                                <label for="recipient-name" class="control-label">审批:</label>
                                <input type="text" class="form-control" id="checkFlag" name="checkFlag"
                                       placeholder="">
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
        var dictCode = "${token.dictCode}";
        

        var load = layer.load();
    <@shiro.hasPermission name="/adminDict/findDictTree.shtml">
        $.post("${basePath}/adminDict/findDictTree.shtml", {dictCode:dictCode}, function (data) {
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

                var type = "国家";

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

        var tablePrefix = "#example_";
        $("#example").dataTable({
            serverSide: true,//分页，取数据等等的都放到服务端去
            processing: true,//载入数据的时候是否显示“载入中”
            pageLength: 10,//首次加载的数据条数
            ordering: false,//排序操作在服务端进行，所以可以关了。
            ajax: {//类似jquery的ajax参数，基本都可以用。
                type: "post",//后台指定了方式，默认get，外加datatable默认构造的参数很长，有可能超过get的最大长度。
                url: "${basePath}/countryDetail/findAll.shtml",
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
            columns: [//对应上面thead里面的序列
                { data: "id", },//字段名字和返回的json序列的key对应
                { data: "year", },
                { data: "city", },
                { data: "county", },
                { data: "town", },
                { data: "village", },
                { data: "forestClass", },
                { data: "smallClass", },
                { data: "landTypes", },
                { data: "littleClass", },
                { data: "forestBelong", },
                { data: "landBelong", },
                { data: "source", },
                { data: "belongProve", },
                { data: "identityCard", },
                { data: "username", },
                { data: "uniteUsername", },
                { data: "area", },
                { data: "compensationStandard", },
                { data: "compensationAmount", },
                { data: "remitNum", },
                { data: "remitUserName", },
                { data: "sendFlag", },
                { data: "checkFlag", },
                { data: "comment", },
                {
                    //Student 没有hireDate
                    data: function (e) {
                        if (e.createTime) {//默认是/Date(794851200000)/格式，需要显示成年月日方式
                            return new Date(Number((""+e.createTime).replace(/\D/g, ''))).toLocaleDateString();
                        }
                        return "空";
                    }
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
                lengthMenu: '<select class="form-control input-xsmall">' + '<option value="5">5</option>' + '<option value="10">10</option>' + '<option value="20">20</option>' + '<option value="30">30</option>' + '<option value="40">40</option>' + '<option value="50">50</option>' + '</select>条记录',//左上角的分页大小显示。
                processing: "载入中",//处理页面数据的时候的显示
                paginate: {//分页的样式文本内容。
                    previous: "上一页",
                    next: "下一页",
                    first: "第一页",
                    last: "最后一页"
                },

                zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。
                //下面三者构成了总体的左下角的内容。
                info: "总共_PAGES_ 页，显示第_START_ 到第 _END_ ，筛选之后得到 _TOTAL_ 条，初始_MAX_ 条 ",//左下角的信息显示，大写的词为关键字。
                infoEmpty: "0条记录",//筛选为空时左下角的显示。
                infoFiltered: ""//筛选之后的左下角筛选提示(另一个是分页信息显示，在上面的info中已经设置，所以可以不显示)，
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
            var table =  $("#example").dataTable();
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

            $("#area").val(rowData.area);

            $("#countryZbje").val(rowData.countryZbje);

            $("#otherZbje").val(rowData.otherZbje);

            $("#je").val(rowData.je);

            $("#comment").val(rowData.comment);


        });


    });





    function itemOnclick(target) {
        //找到当前节点id
        var nodeid = $(target).attr('data-nodeid');
        var tree = $('#getDictTree');
        //获取当前节点对象
        var node = tree.treeview('getNode', nodeid);

        var nodeName = node.text;
        var yearSelect = $("#yearSelect").val();
        $("#filter_form [name='searchContentFromSelect']").val(nodeName);
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
        var type = "国家";
        $("#addBcbzLabel").html("新增-国家补偿资金发放明细");

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
        var type = "国家";
        $("#addBcbzLabel").html("修改-国家补偿资金发放明细");

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


    <@shiro.hasPermission name="/countryDetail/delete.shtml">
    <#--根据ID删除数据-->
    function deleteById(){
        var id = $("#id").val();
        if(null == id || "" == id){
            return;
        }
        var deleteUrl = "${basePath}/countryDetail/delete.shtml";

        var index = layer.confirm("确定删除这条数据？",function(){
            var load = layer.load();
            $.post(deleteUrl,{id:id},function(result){
                layer.close(load);
                if(result && result.status != 200){
                    return layer.msg(result.message,so.default),!0;
                }else{
                    layer.msg(result.message);
                    $('.selected').remove();
                    $("#example").DataTable().draw(false);//点搜索重新绘制table。
                }
            },'json');
            layer.close(index);
        });
    }
    </@shiro.hasPermission>


    

</script>

</body>
</html>
