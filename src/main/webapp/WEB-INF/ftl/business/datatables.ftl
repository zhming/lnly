<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>jquery DataTables插件自定义分页ajax实现</title>

    <link href="http://cdn.bootcss.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="http://cdn.bootcss.com/datatables/1.10.11/css/dataTables.bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="http://cdn.bootcss.com/datatables/1.10.11/css/jquery.dataTables.min.css" rel="stylesheet" media="screen">

    <script src="http://cdn.bootcss.com/datatables/1.10.11/js/jquery.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="http://cdn.bootcss.com/datatables/1.10.11/js/jquery.dataTables.min.js"></script>
    <script src="http://cdn.bootcss.com/datatables/1.10.11/js/dataTables.bootstrap.min.js"></script>
 <style>
     .btn{
         background-image:"basePath/images/1.png";
     }

     .btn-danger:hover{
         color:#fff;
         background-color:##3399CC;
         border-color:#fff;
     }


     .nav-tabs>li.active>a, .nav-tabs>li.active>a:focus, .nav-tabs>li.active>a:hover{
         background-color: #3399CC;
     }
 </style>

</head>
<body>

<!DOCTYPE html>
<html>
<head>
    <title>Try Bootstrap Online</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.bootcss.com/jquery/2.0.0/jquery.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
<div style="background-color:#3399CC">
<ul id="myTab" class="nav nav-tabs" >


        <li class="active" ><a class="btn" href="#home" data-toggle="tab" style="color: #000000; font-size: 16px;">
            资金标准设置</a>
        </li>


    <li><a  class="btn" href="#ios" data-toggle="tab" style="color: #000000; font-size: 16px;">数据填报</a></li>
    <li >
        <a class="btn"  href="#" id="myTabDrop1" style="color: #000000; font-size: 16px;"
           data-toggle="tab">数据统计
        </a>

    </li>
</ul>
</div>
<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active col-sm-12 center-block" id="home" style="background-color:#3399CC" >

        <div class="btn-group  col-sm-12 " style="padding-top: 8px">
            <div class=" col-sm-2  ">
                <a class="btn  btn-info btn-sm " tabindex="0" href="#"><span >年度补偿标准维护</span></a>

            </div>

            <div class="col-sm-2 ">
            <@shiro.hasPermission name="/role/addRole.shtml">
                <a class="btn  btn-info btn-sm " tabindex="0" href="#"
                   onclick="#"><span>年度补偿标准设置</span></a>
            </@shiro.hasPermission>

            </div>





        </div>
    </div>
    <div class="tab-content tab-pane fade" id="ios">
        <div class="tab-pane fade in active col-sm-12 center-block" id="home" style="background-color:#3399CC" >
            <div class=" col-sm-2  ">
                <a class="btn  btn-info btn-sm " tabindex="0" href="#"><span >年度补偿标准维护</span></a>

            </div>

            <div class="col-sm-2 ">
            <@shiro.hasPermission name="/role/addRole.shtml">
                <a class="btn  btn-info btn-sm " tabindex="0" href="#"
                   onclick="#"><span>年度补偿标准设置</span></a>
            </@shiro.hasPermission>

            </div>
        </div>
    </div>
    <div class="tab-pane fade" id="jmeter">
        <div class="tab-pane fade in active col-sm-12 center-block" id="home" style="background-color:#3399CC" >
            <div class=" col-sm-2  ">
                <a class="btn  btn-info btn-sm " tabindex="0" href="#"><span >年度补偿标准维护</span></a>

            </div>

            <div class="col-sm-2 ">
            <@shiro.hasPermission name="/role/addRole.shtml">
                <a class="btn  btn-info btn-sm " tabindex="0" href="#"
                   onclick="#"><span>年度补偿标准设置</span></a>
            </@shiro.hasPermission>

            </div>
        </div>
    </div>
   
</div>
<script>
    $(function () {
        $('#myTab li:eq(0) a').tab('show');
    });
</script>

</body>
</html>

</body>
</html