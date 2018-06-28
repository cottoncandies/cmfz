<%@ page language="java" pageEncoding="UTF-8" %>
<div style="text-align: center; margin-top: 70px;">

    <form id="picSaveInputForm" method="post" enctype="multipart/form-data">
        <div style="margin-top: 10px; margin-bottom: 5px;">
            <label>图片描述:</label>
            <input class="easyui-textbox" type="text" name="desc"
                   data-options="iconCls:'icon-man',width:220,height:30,required:true"/>
        </div>
        <div style="margin-top: 10px; margin-bottom: 5px;">
            <label>上传图片:</label>
            <input class="easyui-filebox" name="file" data-options="width:220,height:30,">
        </div>
        <div style="margin-top: 10px; margin-bottom: 5px;">
            <label>展示状态:</label>
            <select class="easyui-combobox" name="status" style="width:200px;">
                <option value="y">展示图片</option>
                <option value="n">不展示图片</option>
            </select>
        </div>
    </form>
</div>
<script>
    $(function () {
        $picSaveInputForm = $("#picSaveInputForm");
    })
</script>