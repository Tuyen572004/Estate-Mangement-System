<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05/05/2024
  Time: 3:21 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="buildingEditUrl" value="/admin/building-list"/>
<c:url var="buildingApi" value="/api/buildings"/>
<html>
<head>
    <title>Thông tin tòa nhà</title>
</head>
<body>
<div class="main-content" id="main-content" style="font-family: 'Times New Roman', Times, serif;">
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Home</a>
                    </li>

                    <li>
                        <a href="#">UI &amp; Elements</a>
                    </li>
                    <li class="active">Content Sliders</li>
                </ul><!-- /.breadcrumb -->
            </div>

            <div class="page-content">

                <div class="page-header">
                    <h1>Sửa tòa nhà
                    </h1>
                </div><!-- /.page-header -->

                <div class="row">
                    <form:form modelAttribute="modelEdit" action="${buildingEditUrl}" method="GET" id="formEditBuilding" enctype="multipart/form-data">
                        <div class="col-xs-12">
                            <form class="form-horizontal" >
                                <div class="form-group">
                                    <label class="col-xs-3">Tên tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:input path="name" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Quận</label>
                                    <div class="col-xs-4">
                                        <form:select path="district" class="form-control">
                                            <!-- Check if the building has a district -->
                                            <c:choose>
                                                <c:when test="${empty modelEdit.district}">
                                                    <!-- If the building does not have a district, display default option -->
                                                    <form:option value="" label="--Chọn quận--"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <!-- If the building has a district, display the district from the model -->
                                                    <form:option value="${modelEdit.district}" label="${districts.get(modelEdit.district)}"/>
                                                </c:otherwise>
                                            </c:choose>
                                            <!-- Display other district options from the model -->
                                            <form:options items="${districts}"/>
                                        </form:select>

                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Phường</label>
                                    <div class="col-xs-9">
                                        <form:input path="ward" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Đường</label>
                                    <div class="col-xs-9">
                                        <form:input path="street" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Kết cấu</label>
                                    <div class="col-xs-9">
                                        <form:input path="structure" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Số tầng hầm</label>
                                    <div class="col-xs-9">
                                        <form:input path="numberOfBasement" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Diện tích sàn</label>
                                    <div class="col-xs-9">
                                        <form:input path="floorArea" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Hướng</label>
                                    <div class="col-xs-9">
                                        <form:input path="direction" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Hạng</label>
                                    <div class="col-xs-9">
                                        <form:input path="level" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Diện tích thuê</label>
                                    <div class="col-xs-9">
                                        <form:input path="rentArea" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Giá thuê</label>
                                    <div class="col-xs-9">
                                        <form:input path="rentPrice" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Mô tả giá</label>
                                    <div class="col-xs-9">
                                        <form:input path="rentPriceDescription" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Phí dịch vụ</label>
                                    <div class="col-xs-9">
                                        <form:input path="serviceFee" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Phí ô tô</label>
                                    <div class="col-xs-9">
                                        <form:input path="carFee" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Phí mô tô</label>
                                    <div class="col-xs-9">
                                        <form:input path="motoFee" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Phí ngoài giờ</label>
                                    <div class="col-xs-9">
                                        <form:input path="overtimeFee" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Tiền điện</label>
                                    <div class="col-xs-9">
                                        <form:input path="electricityFee" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Đặt cọc</label>
                                    <div class="col-xs-9">
                                        <form:input path="deposit" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Thanh toán</label>
                                    <div class="col-xs-9">
                                        <form:input path="payment" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Thời hạn thuê</label>
                                    <div class="col-xs-9">
                                        <form:input path="rentTime" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Thời gian trang trí</label>
                                    <div class="col-xs-9">
                                        <form:input path="decorationTime" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Tên quản lý</label>
                                    <div class="col-xs-9">
                                        <form:input path="managerName" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">SĐT quản lý</label>
                                    <div class="col-xs-9">
                                        <form:input path="managerPhone" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Phí môi giới</label>
                                    <div class="col-xs-9">
                                        <form:input path="brokerageFee" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Loại tòa nhà</label>
                                    <div class="col-xs-9">
                                        <form:checkboxes path="typeCode" items="${typeCodes}"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3">Ghi chú</label>
                                    <div class="col-xs-9">
                                        <form:input path="note" class="form-control"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-xs-3 no-padding-right"> Hình đại diện</label>
                                    <div class="col-xs-9">
                                        <input type="file" id="uploadImage"/>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-xs-3"></div>
                                    <div class="col-xs-9 ">
                                        <c:if test="${not empty modelEdit.image}">
                                            <c:set var="imagePath" value="/repository${modelEdit.image}"/>
                                            <img src="${imagePath}" id="viewImage" width="300px" height="300px" style="margin-top: 50px">
                                        </c:if>
                                        <c:if test="${empty modelEdit.image}">
                                            <img src="/admin/image/default.png" id="viewImage" width="300px" height="300px">
                                        </c:if>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3"></label>
                                    <div class="col-xs-9">
                                        <c:if test="${modelEdit.id != null}">
                                            <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">
                                                Sửa tòa nhà
                                            </button>
                                        </c:if>
                                        <c:if test="${modelEdit.id == null}">
                                            <button type="button" class="btn btn-primary" id="btnAddOrUpdateBuilding">
                                                Thêm tòa nhà
                                            </button>
                                        </c:if>
                                        <a href="/admin/building-list" class="btn btn-primary">
                                            Hủy thao tác
                                        </a>
                                        <img src="/img/loading.gif" style="display: none; height: 100px" id="loading_image">
                                    </div>
                                </div>
                            </form>
                        </div>
                    </form:form>

                </div>
            </div>


        </div><!-- /.row -->
    </div><!-- /.page-content -->
</div>
</div><!-- /.main-content -->
<script>
    var imageBase64 = '';
    var imageName = '';
    $('#btnAddOrUpdateBuilding').click(function () {
        var typeCode = [];
        var data = {};
        var dataForm = $('#formEditBuilding').serializeArray();
        data["id"] = "${modelEdit.id}";
        $.each(dataForm, function (i, field) {
            if ('' !== field.value && null != field.value) {
                if (field.name != "typeCode") {
                    data[field.name] = field.value;
                } else {
                    typeCode.push(field.value);
                }
            }
            if ('' !== imageBase64) {
                data['imageBase64'] = imageBase64;
                data['imageName'] = imageName;
            }
        })
        if (data["name"]== ""||data["name"] == null) {
            alert("Name is required");
            return;
        }
        if(typeCode.length == 0){
            alert("TypeCode is required");
            return;
        }
        if(data["district"]== ""||data["district"] == null){
            alert("District is required");
            return;
        }
        data["typeCode"] = typeCode;
        fnAddOrUpdate(data)  // call function send data to server
    });

    $('#uploadImage').change(function (event) {
        var reader = new FileReader();
        var file = $(this)[0].files[0];
        reader.onload = function(e){
            imageBase64 = e.target.result;
            imageName = file.name; // ten hinh khong dau, khoang cach. Dat theo format sau: a-b-c
        };
        reader.readAsDataURL(file);
        openImage(this, "viewImage");
    });

    function openImage(input, imageView) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#' +imageView).attr('src', reader.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }


    function fnAddOrUpdate(data) { // function send data to server with ajax
        $('#loading_image').show();
        $.ajax({
            type: "POST",
            url: "${buildingApi}",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "text",
            success: function (response) {
                alert(response);
                window.location.replace("/admin/building-list")
            },
            error: function (response) {
                alert(response);
            }
        });
    }

</script>

</body>
</html>
