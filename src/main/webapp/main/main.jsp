<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>持名法州主页</title>
    <link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../themes/IconExtension.css">
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/jquery.etree.js"></script>
    <script type="text/javascript" src="../js/datagrid-detailview.js"></script>
    <script type="text/javascript" src="../js/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="../js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        <!--菜单处理-->
        $(function () {
            $.post('/cmfz/menu/queryMenus', function (menus) {
                //遍历一级菜单
                $.each(menus, function (idx, parentMenu) {
                    //遍历二级菜单
                    var content = "<div style='text-align:center;'>";
                    $.each(parentMenu.childrenMenu, function (idx, childMenu) {
                        //注意:js传递对象参数时 必须将对象转为json格式字符串   js对象如何转为json格式字符串
                        content += "<a class='easyui-linkbutton' onclick='addtabs(" + JSON.stringify(childMenu) + ");' data-options=\"iconCls:'" + childMenu.iconCls + "',plain:true\" style='margin:5px 5px; width:95%;border: 1px red solid;'>" + childMenu.name + "</a><br/>";
                    });
                    content += "</div>";
                    //追加菜单
                    $("#menus").accordion('add', {
                        title: parentMenu.name,
                        iconCls: parentMenu.iconCls,
                        content: content
                    });
                })
            }, 'JSON')
        })

        //添加选项卡
        function addtabs(obj) {
            var ex = $("#tabs").tabs('exists', obj.name);
            if (!ex) { //不存在添加
                $("#tabs").tabs('add', {
                    title: obj.name,
                    iconCls: obj.iconCls,
                    closable: true,
                    href: "${pageContext.request.contextPath}" + obj.href,
                });
            } else {
                $("#tabs").tabs('select', obj.name);
            }
        }
    </script>

</head>
<body class="easyui-layout">
<div data-options="region:'north',split:true" style="height:60px;background-color:  #5C160C">
    <div style="font-size: 24px;color: #FAF7F7;font-family: 楷体;font-weight: 900;width: 500px;float:left;padding-left: 20px;padding-top: 10px">
        持名法州后台管理系统
    </div>
    <div style="font-size: 16px;color: #FAF7F7;font-family: 楷体;width: 300px;float:right;padding-top:15px">欢迎您:xxxxx
        &nbsp;<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改密码</a>&nbsp;&nbsp;<a
                href="/cmfz/admin/exit" class="easyui-linkbutton" data-options="iconCls:'icon-01'">退出系统</a>
    </div>
</div>
<div data-options="region:'south',split:true" style="height: 40px;background: #5C160C">
    <div style="text-align: center;font-size:15px; color: #FAF7F7;font-family: 楷体"></div>
</div>

<div data-options="region:'west',title:'导航菜单',split:true" style="width:220px;">
    <div id="menus" class="easyui-accordion" data-options="fit:true">

    </div>
</div>
<div data-options="region:'center'">
    <div id="tabs" class="easyui-tabs" data-options="fit:true,narrow:true,pill:true">
        <div title="主页" data-options="iconCls:'icon-neighbourhood',"
             style="background-image:url(${pageContext.request.contextPath}/main/image/shouye.jpg);background-repeat: no-repeat;background-size:100% 100%;">

        </div>
    </div>
</div>
</body>
</html>