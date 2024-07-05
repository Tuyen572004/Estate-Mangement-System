<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05/05/2024
  Time: 10:04 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="customerApi" value="/api/customers"/>
<c:url var="customerListUrl" value="/admin/customer-list"/>
<c:url var="customerEditUrl" value="/admin/customer-edit"/>
<html>
<head>
    <title>Danh sách khách hàng </title>
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
                <h1>Danh sách khách hàng
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
                            <form:form action="${customerListUrl}" method="GET" modelAttribute="modelSearch"
                                       id="formCustomerList">
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-4">
                                                <label>Tên khách hàng</label>
                                                <form:input class="form-control" path="fullname"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label>Di động</label>
                                                <form:input class="form-control" path="phone"/>
                                            </div>
                                            <div class="col-xs-4">
                                                <label>Email</label>
                                                <form:input class="form-control" path="email"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <security:authorize access="hasRole('MANAGER')">
                                                <div class="col-xs-4">
                                                    <label>Nhân viên phụ trách</label>
                                                    <form:select class="form-control" path="staffId">
                                                        <form:option value="" label="--Chọn nhân viên--"/>
                                                        <form:options items="${staffs}"/>
                                                    </form:select>
                                                </div>
                                            </security:authorize>
                                            <div class="col-xs-4">
                                                <label>Tình trạng</label>
                                                <form:select class="form-control" path="status">
                                                    <form:option value="" label="--Chọn tình trạng--"/>
                                                    <c:forEach var="statusIt" items="${status}">
                                                        <form:option value="${statusIt.value}"
                                                                     label="${statusIt.value}"/>
                                                    </c:forEach>
                                                </form:select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-xs-12">
                                            <div class="col-xs-6">
                                                <div class="col-xs-6">
                                                    <button type="button" class="btn btn-sm btn-primary" id="btnSearch">
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
                        <a href="${customerEditUrl}">
                            <button title="Thêm khách hàng" class="btn btn-primary">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-person-add" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0m-2-6a3 3 0 1 1-6 0 3 3 0 0 1 6 0M8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
                                    <path d="M8.256 14a4.5 4.5 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10q.39 0 .74.025c.226-.341.496-.65.804-.918Q8.844 9.002 8 9c-5 0-6 3-6 4s1 1 1 1z"/>
                                </svg>
                            </button>
                        </a>
                        <security:authorize access="hasRole('MANAGER')">
                            <button title="Xóa khách hàng" class="btn btn-danger" id="btnDeleteCustomers">
                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                     class="bi bi-person-dash" viewBox="0 0 16 16">
                                    <path d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1m0-7a3 3 0 1 1-6 0 3 3 0 0 1 6 0M8 7a2 2 0 1 0 0-4 2 2 0 0 0 0 4"/>
                                    <path d="M8.256 14a4.5 4.5 0 0 1-.229-1.004H3c.001-.246.154-.986.832-1.664C4.484 10.68 5.711 10 8 10q.39 0 .74.025c.226-.341.496-.65.804-.918Q8.844 9.002 8 9c-5 0-6 3-6 4s1 1 1 1z"/>
                                </svg>
                            </button>
                        </security:authorize>

                    </div>
                </div>
            </div>

            <div style="margin-top: 5%"></div>

            <div class="row">
                <display:table requestURI="${customerListUrl}" name="customerResponses"
                               cellspacing="0" cellpadding="0" partialList="true" sort="external"
                               size="${modelSearch.totalItems}" defaultsort="2" defaultorder="ascending"
                               pagesize="${modelSearch.maxPageItems}"
                               class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                               style="margin: 3em 0 1.5em; font-family: 'Times New Roman'" id="tableCustomerList">
                    <display:column
                            title="<fieldset class='form-group'>
                            <input type='checkbox' id='checkAll' class='check-box-element'>
                            </fieldset>" class="center select-cell"
                            headerClass="center select-cell">
                        <fieldset>
                            <input type="checkbox" name="checkList" value="${tableCustomerList.id}"
                                   id="checkbox_${tableCustomerList.id}" class="check-box-element"/>
                        </fieldset>
                    </display:column>
                    <display:column property="fullname" title="Tên khách hàng"></display:column>
                    <display:column property="phone" title="Di động"></display:column>
                    <display:column property="email" title="Email"></display:column>
                    <display:column property="demand" title="Nhu cầu"></display:column>
                    <display:column property="createdBy" title="Người thêm"></display:column>
                    <display:column property="createdDate" title="Ngày thêm"></display:column>
                    <display:column property="status" title="Tình trạng"></display:column>
                    <display:column title="Thao tác">
                        <div>
                            <security:authorize access="hasRole('MANAGER')">
                                <button class="btn btn-xs btn-success" title="Giao khách hàng"
                                        onclick="assignmentCustomerFunction(${tableCustomerList.id})">
                                    <i class="ace-icon glyphicon glyphicon-align-justify"></i>
                                </button>
                            </security:authorize>

                            <a class="btn btn-xs btn-info" title="Sửa thông tin"
                               href="${customerEditUrl}-${tableCustomerList.id}">
                                <i class="ace-icon fa fa-pencil-square-o"></i>
                            </a>
                            <security:authorize access="hasRole('MANAGER')">
                                <button class="btn btn-xs btn-danger" title="Xóa khách hàng"
                                        onclick="btnDeleteCustomer(${tableCustomerList.id})">
                                    <i class="ace-icon fa fa-trash-o icon-only bigger-130"></i>
                                </button>
                            </security:authorize>

                        </div>
                    </display:column>

                </display:table>
            </div>
        </div>
    </div><!-- /.row -->
</div><!-- /.page-content -->
<div id="assignmentCustomerModal" class="modal fade" role="dialog">
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
                <input type="hidden" id="customerId" name="customerId" value="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btnAssignmentCustomer" value="">Giao khách hàng
                </button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>
<script>

    function assignmentCustomerFunction(customerId) {
        $('#assignmentCustomerModal').modal();
        $('#customerId').val(customerId);
        loadStaffs(customerId);

    }

    function loadStaffs(customerId) {
        var urlA = "${customerApi}/" + customerId + "/staffs";
        $.ajax({
            type: "GET",
            url: urlA,
            dataType: "json",
            async: false,
            success: (responde) => {
                var row = "";
                $.each(responde.data, function (index, staff) {
                    row += '<tr> ' +
                        '<td class="center"> ' +
                        '<label class="pos-rel"> <input type="checkbox" class="ace" value="' + staff.staffId + '" ' + staff.checked + ' > ' +
                        '<span class="lbl"></span> ' +
                        '</label> ' +
                        '</td>' +
                        '<td>' + staff.fullName + '</td> ' +
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

    //@brief: function collect data customerIds and staffIds when click button Giao khách hàng in modal fade id="assignmentCustomerModal"
    $('#btnAssignmentCustomer').click(function (event) {
        event.preventDefault();
        var data = {};
        data['customerId'] = $('#customerId').val();
        $('#staffList').find('tbody input[type="checkbox"]').each(function () {
            console.log($(this).val());
            console.log($(this).is(':checked'));
        });
        var staffIds = $('#staffList').find('tbody input[type="checkbox"]:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffIds;
        fnAssignmentCustomer(data);
    });

    //@brief: function send data to server when click button Giao khách hàng in modal fade id="assignmentCustomerModal"
    function fnAssignmentCustomer(data) {
        $.ajax({
            type: "PUT",
            url: "${customerApi}",
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
            $('#formCustomerList').submit();
        });
    });
    $('#btnDeleteCustomers').click(function (event) {
        event.preventDefault();
        var data = {};
        var customerIds = $('#tableCustomerList').find('tbody input[type="checkbox"]:checked').map(function () {
            return $(this).val();
        }).get(); // Convert the jQuery object to an array
        data['ids'] = customerIds;
        fnDeleteCustomer(data);
    });

    function btnDeleteCustomer(CustomerId) {
        var data = {};
        data['ids'] = [CustomerId];
        fnDeleteCustomer(data);
    }

    function fnDeleteCustomer(data) {
        $.ajax({
            type: "DELETE",
            url: "${customerApi}",
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
