<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table id="log"></table>
<script>
    $("#log").datagrid({
        title: '日志信息',
        method: "GET",
        remoteSort: false,
        singleSelect: true,
        nowrap: false,
        fitColumns: true,
        fit: true,
        url: '/cmfz/log/queryAll',
        pagination: true,
        pageSize: 10,
        pageList: [10, 20, 30, 40, 50],
        idField: 'id',
        toolbar: [{
            text: '刷新',
            iconCls: 'icon-reload',
            handler: function () {
                $("#log").datagrid('reload');
            }
        },],
        columns: [[
            /*{field:'id',title:'图片编号',width:80},*/
            {field: 'userName', title: '操作用户', width: 100, sortable: true},
            {field: 'type', title: '操作类型', width: 80, sortable: true},
            {field: 'createDate', title: '操作日期', width: 80, sortable: true},
            {field: 'message', title: '具体信息', width: 80, sortable: true},]]
    });

</script>