<!DOCTYPE html>
<html lang="zh-cn">
	<head>
		<meta charset="utf-8" />
		<title>Datatables</title>
		<meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
		<link rel="icon" href="${basePath}/favicon.ico" type="image/x-icon" />
		<link   rel="shortcut icon" href="${basePath}/favicon.ico" />
		<link href="${basePath}/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
		<link href="${basePath}/css/common/base.css?${_v}" rel="stylesheet"/>
        <link href="https://cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css" rel="stylesheet"/>
        <link href="https://cdn.datatables.net/buttons/1.3.1/css/buttons.dataTables.min.css" rel="stylesheet"/>



	</head>
	<body data-target="#one" data-spy="scroll">
		
		<div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
			<#--row-->
                <table id="myTable" class="display" width="100%">
                    <thead>
                    <tr>
                        <th></th>
                        <th>id</th>
                        <th>nickname</th>
                        <th>email</th>
                        <th>createTime</th>
                    </tr>
                    </thead>

                </table>
			<#--/row-->
		</div>
		<script  src="${basePath}/js/common/jquery/jquery1.8.3.min.js"></script>
		<script  src="${basePath}/js/common/layer/layer.js"></script>
		<script  src="${basePath}/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script  src="${basePath}/js/common/bootstrap/bootstrap-treeview.js"></script>
        <script  src="https://cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
        <script  src="https://cdn.datatables.net/buttons/1.3.1/js/dataTables.buttons.min.js"></script>
        <script  src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
        <script  src="https://cdn.datatables.net/buttons/1.3.1/js/buttons.html5.min.js"></script>
        <script type="text/javascript">



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
                var table = $('#myTable').DataTable( {
                    dom: 'Blfrtip',
                    "bLengthChange": true,
                    "lengthMenu": [[100, 500, 1000, -1], [100, 500, 1000, "所有"]],
                    "bFilter": true,
                    "bSort": false,
                    "iDisplayLength": 10,
                    //"sPaginationType": "full_numbers",
                    "bProcessing": true,
                    "bServerSide": true,
                    "sAjaxSource": "/member/findAll.shtml",
                    "sServerMethod": "POST",
                    "columns": [
                        {
                            "className":      'details-control',
                            "orderable":      false,
                            "data":           null,
                            "defaultContent": ''
                        },
                        { "data": "id" },
                        { "data": "nickname" },
                        { "data": "email" },
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
                $('#myTable tbody').on('click', 'td.details-control', function () {
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

            table.buttons().container()
                    .appendTo( $('.col-sm-6:eq(0)', table.table().container() ) );

        </script>
	</body>
</html>