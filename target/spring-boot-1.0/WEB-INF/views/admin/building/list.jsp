
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05/05/2024
  Time: 10:04 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="buildingApi" value="/api/buildings"/>
<c:url var="buildingListUrl" value="/admin/building-list"/>
<html>
<head>
    <title>Danh sách tòa nhà </title>
</head>
<body>
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
                <h1>Danh sách tòa nhà
                </h1>
            </div><!-- /.page-header -->


            <div class="row">
                <div class="widget-box" style="font-family: 'Times New Roman'">
                    <div class="widget-header">
                        <h4 class="widget-title">Tìm kiếm</h4>

                        <span class="widget-toolbar">
									<a href="#" data-action="reload">
										<i class="ace-icon fa fa-refresh"></i>
									</a>

									<a href="#" data-action="collapse">
										<i class="ace-icon fa fa-chevron-up"></i>
									</a>

								</span>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main">
                            <form:form action="${buildingListUrl}" method="GET" modelAttribute="modelSearch"
                                       id="formBuildingList">
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <label>Tên tòa nhà</label>
                                                <form:input class="form-control" path="name"/>
                                            </div>
                                            <div class="col-sm-6">
                                                <label>Diện tích sàn</label>
                                                <form:input class="form-control" path="floorArea"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-2">
                                                <label>Quận</label>
                                                <form:select class="form-control" path="district">
                                                    <form:option value="" label="--Chọn quận--"/>
                                                    <form:options items="${districts}"/>
                                                </form:select>
                                            </div>
                                            <div class="col-xs-5">
                                                <label>Phường</label>
                                                <form:input class="form-control" path="ward"/>
                                            </div>
                                            <div class="col-xs-5">
                                                <label>Đường</label>
                                                <form:input class="form-control" path="street"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <label>Số tầng hầm</label>
                                                <form:input class="form-control" path="numberOfBasement"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label>Hướng</label>
                                                <form:input class="form-control" path="direction"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label>Hạng</label>
                                                <form:input class="form-control" path="level"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-3">
                                                <label>Diện tích từ</label>
                                                <form:input class="form-control" path="areaFrom"/>
                                            </div>
                                            <div class="col-xs-3">
                                                <label>Diện tích đến</label>
                                                <form:input class="form-control" path="areaTo"/>
                                            </div>
                                            <div class="col-xs-3">
                                                <label>Giá thuê từ</label>
                                                <form:input class="form-control" path="rentPriceFrom"/>
                                            </div>
                                            <div class="col-xs-3">
                                                <label>Giá thuê đến</label>
                                                <form:input class="form-control" path="rentPriceTo"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <label>Tên quản lý</label>
                                                <form:input class="form-control" path="managerName"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label>Số điện thoại quản lý</label>
                                                <form:input class="form-control" path="managerPhone"/>
                                            </div>
                                            <div class="col-xs-3">
                                                <label>Nhân viên phụ trách</label>
                                                <form:select class="form-control" path="staffId">
                                                    <form:option value="" label="--Chọn nhân viên--"/>
                                                    <form:options items="${staffs}"/>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <label>
                                                    <form:checkboxes path="typeCode" items="${typeCodes}"/>
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <div class="col-xs-6">
                                                    <button type="button" class="btn btn-sm btn-primary" id="btnSearch" >
                                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14"
                                                             fill="currentColor" class="bi bi-search"
                                                             viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                        </svg>
                                                        Tìm kiếm
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form:form>

                        </div>
                    </div>
                    <div style="text-align:right">
                        <a href="/admin/building-edit">
                            <button title="Thêm tòa nhà" class="btn btn-primary">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-building-fill-add" viewBox="0 0 16 16">
                                    <path
                                            d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0"/>
                                    <path
                                            d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v7.256A4.5 4.5 0 0 0 12.5 8a4.5 4.5 0 0 0-3.59 1.787A.5.5 0 0 0 9 9.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .39-.187A4.5 4.5 0 0 0 8.027 12H6.5a.5.5 0 0 0-.5.5V16H3a1 1 0 0 1-1-1zm2 1.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3 0v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zM4 5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M7.5 5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm2.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M4.5 8a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                                </svg>
                            </button>
                        </a>
                        <button title="Xóa tòa nhà" class="btn btn-danger" id="btnDeleteBuildings">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-building-fill-dash" viewBox="0 0 16 16">
                                <path
                                        d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1"/>
                                <path
                                        d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v7.256A4.5 4.5 0 0 0 12.5 8a4.5 4.5 0 0 0-3.59 1.787A.5.5 0 0 0 9 9.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .39-.187A4.5 4.5 0 0 0 8.027 12H6.5a.5.5 0 0 0-.5.5V16H3a1 1 0 0 1-1-1zm2 1.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3 0v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5m3.5-.5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zM4 5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M7.5 5a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm2.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5h-1a.5.5 0 0 0-.5.5M4.5 8a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z"/>
                            </svg>
                        </button>
                    </div>
                </div>
            </div>

            <div style="margin-top: 5%"></div>

            <div class="row">
                <table class="table table-striped table-bordered table-hover"
                       style="font-family: 'Times New Roman'" id="tableBuildingList">
                    <thead>
                    <tr>
                        <th class="center">
                            <label class="pos-rel">
                                <input type="checkbox" class="ace">
                                <span class="lbl"></span>
                            </label>
                        </th>
                        <th>Tên tòa nhà</th>
                        <th>Địa chỉ</th>
                        <th>Số tầng hầm</th>
                        <th>Tên quản lý</th>
                        <th>SĐT quản lý</th>
                        <th>D.T Sàn</th>
                        <th>D.T Trống</th>
                        <th>D.T Thuê</th>
                        <th>Giá thuê</th>
                        <th>Phí dịch vụ</th>
                        <th>Phí môi giới</th>
                        <th>Thao tác</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${buildingSearchResponses}" var="it">
                        <tr>
                            <td class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" class="ace" value="${it.id}">
                                    <span class="lbl"></span>
                                </label>
                            </td>
                            <td>${it.name}</td>
                            <td>${it.address}</td>
                            <td>${it.numberOfBasement}</td>
                            <td>${it.managerName}</td>
                            <td>${it.managerPhone}</td>
                            <td>${it.floorArea}</td>
                            <td>${it.emptyArea}</td>
                            <td>${it.rentArea}</td>
                            <td>${it.rentPrice}</td>
                            <td>${it.serviceFee}</td>
                            <td>${it.brokerageFee}</td>
                            <td>
                                <div>
                                    <button class="btn btn-xs btn-success" title="Giao tòa nhà"
                                            onclick="assignmentBuildingFunction(${it.id})">
                                        <i class="ace-icon glyphicon glyphicon-align-justify"></i>
                                    </button>
                                    <a class="btn btn-xs btn-info" title="Sửa tòa nhà" href="/admin/building-edit-${it.id}">
                                        <i class="ace-icon fa fa-pencil-square-o"></i>
                                    </a>
                                    <button class="btn btn-xs btn-danger" title="Xóa tòa nhà"
                                            onclick="btnDeleteBuilding(${it.id})">
                                        <i class="ace-icon fa fa-trash-o icon-only bigger-130"></i>
                                    </button>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div><!-- /.row -->
</div><!-- /.page-content -->
<div id="assignmentBuildingModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <div class="modal-body">
                <table class="table table-striped table-bordered table-hover" id="staffList"
                       style="font-family: 'Times New Roman'">
                    <thead>
                    <tr>
                        <th class="center"> Chọn</th>
                        <th>Tên nhân viên</th>
                    </tr>
                    </thead>
                    <tbody>
                    </tbody>
                </table>
                <input type="hidden" id="buildingId" name="buildingId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignmentBuilding" value="">Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>
<script>
    //@brief: function handle when click button Giao tòa nhà in table Danh sách tòa nhà part 2
    function assignmentBuildingFunction(buildingId) {
        $('#assignmentBuildingModal').modal();
        $('#buildingId').val(buildingId);
        loadStaffs(buildingId);

    }
    function loadStaffs(buildingId){
        var urlA = "${buildingApi}/" + buildingId + "/staffs";
        $.ajax({
            type: "GET",
            url: urlA,
            dataType:"json",
            async: false,
            success: (responde) => {
                var row="";
                $.each(responde.data, function(index, staff){
                    row += '<tr> ' +
                                '<td class="center"> ' +
                                    '<label class="pos-rel"> <input type="checkbox" class="ace" value="' + staff.staffId + '" ' + staff.checked + ' > ' +
                                        '<span class="lbl"></span> ' +
                                    '</label> ' +
                                '</td>' +
                                '<td>' + staff.fullName + '</td> '+
                            '</tr> ';

                });
                console.log(row);
                $('#staffList tbody').html(row);
            },
            error: (error) => {
                alert("Không load được dữ liệu nhân viên");
            }
        })
    }

    //@brief: function collect data buildingIds and staffIds when click button Giao tòa nhà in modal fade id="assignmentBuildingModal"
    $('#btnAssignmentBuilding').click(function (event) {
        event.preventDefault();
        var data = {};
        data['buildingId'] = $('#buildingId').val();
        $('#staffList').find('tbody input[type="checkbox"]').each(function () {
            console.log($(this).val());
            console.log($(this).is(':checked'));
        });
        var staffIds = $('#staffList').find('tbody input[type="checkbox"]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffIds;
        fnAssignmentBuilding(data);
    });

    //@brief: function send data to server when click button Giao tòa nhà in modal fade id="assignmentBuildingModal"
    function fnAssignmentBuilding(data){
        $.ajax({
            type: "PUT",
            url: "${buildingApi}",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "text",
            success: (responde) => {
                alert(responde);
                window.location.reload();
            },
            error: (responde) => {
                alert(responde);
            }
        })
    }

    $('document').ready(function () {
        $('#btnSearch').click(function (event) {
            event.preventDefault();
            $('#formBuildingList').submit();
        });
    });
    $('#btnDeleteBuildings').click(function (event) {
        event.preventDefault();
        var data = {};
        var buildingIds = $('#tableBuildingList').find('tbody input[type="checkbox"]:checked').map(function () {
            return $(this).val();
        }).get(); // Convert the jQuery object to an array
        data['ids'] = buildingIds;
        fnDeleteBuilding(data);
    });
    function btnDeleteBuilding(buildingId) {
        var data = {};
        data['ids'] = [buildingId];
        fnDeleteBuilding(data);
    }
    function fnDeleteBuilding(data) {
        $.ajax({
            type: "DELETE",
            url: "${buildingApi}",
            data: JSON.stringify(data["ids"]),
            contentType: "application/json",
            dataType: "text",
            success: (responde) => {
                alert(responde);
                window.location.reload();
            },
            error: (responde) => {
                alert(responde);
            }
        })
    }
</script>
</body>
</html>
