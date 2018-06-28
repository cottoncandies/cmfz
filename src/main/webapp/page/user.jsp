<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" %>
<script>
    $(function () {
        $("#btn").linkbutton({
            onClick: function () {
                var titles = $("#cc").combotree("getText");

                var c = "";
                var fileds = $("#cc").combotree("getValues");
                console.log(fileds);
                console.log(titles);
                $.each(fileds, function (index, filed) {
                    if (fileds.length != index + 1) {
                        c += filed + ",";
                    } else {
                        c += filed;
                    }
                })
                $('#userCustomerExportForm').form('submit', {
                    url: "${pageContext.request.contextPath}/user/customeExport",
                    queryParams: {
                        titles: titles,
                        fields: c,
                    },
                    success: function (result) {
                        //result form提交返回的json数据不会自动转为js对象
                        var jsObj = $.parseJSON(result);
                        $.messager.show({title: "提示", msg: jsObj.message, timeout: 4000});

                    }
                })
                //关闭自定义导出对话框
                $("#userCustomerExportDialog").dialog('close');
            }
        })
        $('#userDataGrid').edatagrid({
            url: "${pageContext.request.contextPath}/user/queryAll",
            method: "get",
            columns: [[
                {field: 'id', title: '编号', width: 100},
                {field: 'farmington', title: '法名', width: 100},
                {field: 'nickname', title: '昵称', width: 100},
                {field: 'gender', title: '性别', width: 100},
                {field: 'photo', title: '头像', width: 100},
                {field: 'location', title: '所在地', width: 100},
                {field: 'province', title: '省市', width: 100},
                {field: 'city', title: '城市', width: 100},
                {field: 'description', title: '个人签名', width: 100},
                {field: 'phone', title: '电话', width: 100},
                {field: 'shangshi', title: '上师', width: 100},
                {field: 'createDate', title: '注册时间', width: 100},
                {
                    field: 'password',
                    title: '密码',
                    width: 100,
                    align: 'right',
                    editor: {type: "text", options: {required: true}}
                },
            ]],
            idField: "id",
            pagination: true,
            fitColumns: true,
            fit: true,
            pageSize: 4,
            pageList: [4, 10, 15],
            toolbar: [{
                iconCls: 'icon-edit',
                text: "导入用户数据",
                handler: function () {
                    $("#addUserData_dialog").dialog('open');
                }
            }, '-', {
                iconCls: 'icon-help',
                text: "自定义导出",
                handler: function () {
                    $("#userCustomerExportDialog").dialog("open")
                }
            },]
        });
    })

    //导入用户数据
    function saveUserData() {
        $('#addUserData_form').form('submit', {
            url: "${pageContext.request.contextPath}/user/import",
            success: function (result) {
                //result form提交返回的json数据不会自动转为js对象
                var jsObj = $.parseJSON(result);
                $.messager.show({title: "提示", msg: jsObj.message, timeout: 4000});
                //刷新edatagrid
                $('#userDataGrid').edatagrid("reload");
                $("#addUserData_dialog").dialog('close');
            }
        });

    }


</script>


<table id="userDataGrid"></table>

<%--自定义导出对话框--%>
<div id="userCustomerExportDialog" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <select id="cc" class="easyui-combotree" style="width:200px;"
            data-options="required:true,checkbox:true,onlyLeafCheck:true,multiple:true,data: [{
		text: '自定义选择属性',
		state: 'open',
		children: [{
		    id:'id',
			text: '编号'
		},{
		    id:'farmington',
			text: '法名'
		},{
		    id:'nickname',
			text: '昵称'
		},{
		    id:'gender',
			text: '性别'
		},{
		    id:'photo',
			text: '头像'
		},{
		    id:'location',
			text: '所在地'
		},{
		    id:'province',
			text: '省市'
		},{
		    id:'city',
			text: '城市'
		},{
		    id:'description',
			text: '个人签名'
		},{
		    id:'phone',
			text: '电话'
		},{
		    id:'shangshi',
			text: '上师'
		},{
		    id:'createDate',
			text: '注册时间'
		},{
		    id:'password',
			text: '密码'
		}]
	}]
">
    </select>
    <form id="userCustomerExportForm" method="post">
        <a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">导出</a>
    </form>

</div>


<%--导入用户数据对话框--%>
<div id="addUserData_dialog" class="easyui-dialog" title="导入用户数据"
     style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
    buttons:[{
                text:'导入',
                iconCls:'icon-save',
                handler: function(){saveUserData();},
            },{
                text:'关闭',
                iconCls:'icon-cancel',
                handler:function(){$('#addUserData_dialog').dialog('close')},
            }]">
    <form id="addUserData_form" method="post" style="text-align: center"
          enctype="multipart/form-data">
        <div>
            <label for="UserDataFile">选择文件:</label>
            <input id="UserDataFile" class="easyui-filebox" name="UserDataFile" style="width:300px">
        </div>
    </form>
</div>