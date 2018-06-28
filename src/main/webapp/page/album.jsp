<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" %>

<script>
    var $album;
    $(function () {
        $album = $('#album');

        $album.treegrid({
            fit: true,
            fitColumns: true,
            url: '${pageContext.request.contextPath}/album/queryAll',
            method: 'get',
            idField: 'id',
            treeField: 'name',
            onDblClickRow: function (row) {
                var row = $album.treegrid("getSelected");
                //选中章节并且双击时播放音频
                if (row != null && row.size != null) {
                    $("#audioPlayerDialog").dialog("open")
                    $("#audioPlayer").prop("src", "${pageContext.request.contextPath}/audio/" + row.url);
                }
            },
            columns: [[
                {field: 'name', title: '名字', width: 80},
                {field: 'size', title: '文件大小', width: 80},
                {field: 'time', title: '时长', width: 80}
            ]],
            toolbar: [{
                iconCls: 'icon-edit',
                text: "专辑详情",
                handler: function () {
                    showDetailAlbum();
                }
            }, '-', {
                text: "添加专辑",
                iconCls: 'icon-help',
                handler: function () {
                }
            }, '-', {
                text: "添加章节",
                iconCls: 'icon-help',
                handler: function () {
                    addChapterDialog();
                }
            }, '-', {
                text: "下载章节",
                iconCls: 'icon-help',
                handler: function () {
                    downloadChapter();
                }
            }]
        });
    });

    //展示专辑详情
    function showDetailAlbum() {
        var row = $album.treegrid("getSelected");
        if (row == null) {
            $.messager.alert('我的消息', '请选中专辑！', 'info');
        } else {
            if (row.size == null) {
                $("#detailAlbumDialog").dialog("open");
                $('#detailAlbumForm').form('load', row);
                $("#img").prop("src", "${pageContext.request.contextPath}" + row.img)
            } else {
                $.messager.alert('我的消息', '请选中专辑！', 'info');
            }
        }
    }

    //添加章节对话框
    function addChapterDialog() {
        var row = $album.treegrid("getSelected");
        if (row == null) {
            $.messager.alert('我的消息', '请选中专辑！', 'info');
        } else {
            if (row.size == null) {
                //alert("添加章节")
                $("#addChapter_dialog").dialog("open");
                $("#AlbumId").val(row.id);
            } else {
                $.messager.alert('我的消息', '请选中专辑！', 'info');
            }
        }
    }

    //添加章节
    function saveChapter() {
        $('#addChapter_form').form('submit', {
            url: "${pageContext.request.contextPath}/chapter/save",
            success: function (result) {
                //result form提交返回的json数据不会自动转为js对象
                var jsObj = $.parseJSON(result);
                $.messager.show({title: "提示", msg: jsObj.message, timeout: 4000});
                $("#addChapter_dialog").dialog('close');
                //刷新treegrid
                $album.treegrid("reload");

            }
        });
    }

    //下载章节
    function downloadChapter() {
        var row = $album.treegrid("getSelected");
        if (row == null) {
            $.messager.alert('我的消息', '请选中章节！', 'info');
        }
        if (row.size != null) {
            location.href = "${pageContext.request.contextPath}/chapter/download?url=" + row.url + "&&oldName=" + row.name;
        } else {
            $.messager.alert('我的消息', '请选中章节！', 'info');
        }
    }
</script>

<%--专辑treegrid--%>
<table id="album" style="width:600px;height:400px"></table>

<%--展示专辑详情的对话框--%>
<div id="detailAlbumDialog" class="easyui-dialog" title="专辑详情" style="width:400px;height:300px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <%--专辑详情form表单--%>
    <form id="detailAlbumForm" method="post" style="text-align: center;">
        <div>
            <label for="name">专辑名:</label>
            <input id="name" class="easyui-validatebox" type="text" name="name" data-
                   options="required:true"/>
        </div>
        <div>
            <label for="count">章节数:</label>
            <input id="count" class="easyui-validatebox" type="text" name="count" data-
                   options=""/>
        </div>
        <div>
            <label for="createDate">创建时间:</label>
            <input id="createDate" class="easyui-validatebox" type="text" name="createDate"
                   data-options=""/>
        </div>
        <div>
            <label for="score">评分:</label>
            <input id="score" class="easyui-validatebox" type="text" name="score" data-
                   options=""/>
        </div>
        <div>
            <label for="author">作者:</label>
            <input id="author" class="easyui-validatebox" type="text" name="author" data-
                   options=""/>
        </div>
        <div>
            <label for="broadCast">播音:</label>
            <input id="broadCast" class="easyui-validatebox" type="text" name="broadCast"
                   data-options=""/>
        </div>
        <div>
            <label for="brief">专辑简介:</label>
            <input id="brief" class="easyui-validatebox" type="text" name="brief" data-
                   options=""/>
        </div>
        <div>
            <label for="brief">专辑封面:</label>
            <img id="img" src="">
        </div>
    </form>
</div>

<%--添加章节对话框--%>
<div id="addChapter_dialog" class="easyui-dialog" title="添加章节"
     style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true,
    buttons:[{
                text:'保存',
                iconCls:'icon-save',
                handler: function(){saveChapter();},
            },{
                text:'关闭',
                iconCls:'icon-cancel',
                handler:function(){$('#addChapter_dialog').dialog('close')},
            }]">
    <form id="addChapter_form" method="post" style="text-align: center"
          enctype="multipart/form-data">
        <div>
            <input id="AlbumId" type="hidden" name="id" style="width:300px">
        </div>
        <div>
            <label for="audio">上传:</label>
            <input id="audio" class="easyui-filebox" name="audio" style="width:300px">
        </div>
    </form>
</div>
<%--播放音频对话框--%>
<div id="audioPlayerDialog" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,modal:true,closed:true">
    <div>
        <audio id="audioPlayer" src="" controls="controls" autoplay="autoplay"></audio>
    </div>
</div>