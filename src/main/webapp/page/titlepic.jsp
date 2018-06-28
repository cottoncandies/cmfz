<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<%--轮播图数据表格--%>
<table id="titlePicDatagrid"></table>

<%--保存图片的对话框--%>
<div id="picSaveDialog"></div>

<script>

    var $picdg, $picSavedg, $picSaveInputForm;

    $(function () {
        $picdg = $("#titlePicDatagrid");
        $picSavedg = $("#picSaveDialog");


        //初始化用户的数据表格
        $picdg.edatagrid({
            url: "${pageContext.request.contextPath}/pic/queryAll",
            fitColumns: true,
            fit: true,
            pagination: true,
            pageSize: 2,
            pageList: [2, 5, 10, 20, 30],
            destroyUrl: '${pageContext.request.contextPath}/pic/remove',
            updateUrl: '${pageContext.request.contextPath}/pic/modify',
            toolbar: [{
                iconCls: 'icon-add',
                text: "添加",
                handler: openSavePicDialog
            }, '-', {
                iconCls: 'icon-reload',
                text: "修改",
                handler: modifyPic
            }, {
                iconCls: 'icon-remove',
                text: "删除",
                handler: removePic
            }, '-', {
                iconCls: 'icon-save',
                text: "保存",
                handler: function () {
                    $picdg.edatagrid('saveRow');
                }
            }],
            columns: [[
                {title: "编号", field: "id", width: 80,},
                {title: "图片名", field: "name", width: 80,},
                {title: "描述", field: "desc", width: 40,},
                {title: "路径", field: "path", width: 100,},
                {title: "展示状态", field: "status", width: 40, editor: {type: 'text', options: {required: true}}},
                {title: "创建时间", field: "createDate", width: 100, editor: {type: 'text', options: {required: true}}}
            ]],
            view: detailview,
            detailFormatter: function (rowIndex, rowData) {
                return '<table><tr>' +
                    '<td rowspan=2 style="border:0"><img src="' + rowData.path + '"  style="height:50px;"></td>' +
                    '<td style="border:0">' +
                    '<p>图片名: ' + rowData.name + '</p>' +
                    '<p>展示状态: ' + rowData.status + '</p>' +
                    '<p>创建时间: ' + rowData.createDate + '</p>' +
                    '</td>' +
                    '</tr></table>';
            }
        });
    });


    function openSavePicDialog() {
        $picSavedg.dialog({
            title: "保存图片",
            iconCls: 'icon-user',
            width: 600,
            height: 400,
            href: '${pageContext.request.contextPath}/page/addpic.jsp',
            buttons: [{
                text: '保存',
                iconCls: 'icon-save',
                handler: savePicInfo,
            }, {
                text: '关闭',
                iconCls: 'icon-cancel',
                handler: function () {
                    $picSavedg.dialog('close')
                },
            }]
        });
    }

    //删除轮播图
    function removePic() {
        //获取选中行
        var selected = $picdg.datagrid('getSelected');
        if (selected == null) {
            $.messager.show({title: "提示", msg: '请选中一行数据', timeout: 4000});
        } else {
            $picdg.edatagrid('destroyRow');
        }
    }

    //修改轮播图
    function modifyPic() {
        //获取选中行
        var selected = $picdg.datagrid('getSelected');
        if (selected == null) {
            $.messager.show({title: "提示", msg: '请选中一行数据', timeout: 4000});
        } else {
            //获取选中行下标
            var index = $picdg.datagrid('getRowIndex', selected);
            $picdg.edatagrid('editRow', index);
        }
    }

    //添加轮播图
    function savePicInfo() {
        $picSaveInputForm.form('submit', {
            url: "${pageContext.request.contextPath}/pic/save",
            onSubmit: function () {
                return $(this).form('validate');
            },
            success: function (result) {
                //result form提交返回的json数据不会自动转为js对象
                var jsObj = $.parseJSON(result);
                $.messager.show({title: "提示", msg: jsObj.message, timeout: 4000});
                $picSavedg.dialog('close');
                //刷新datagrid
                $picdg.datagrid('reload');
            }
        });

    }
</script>

