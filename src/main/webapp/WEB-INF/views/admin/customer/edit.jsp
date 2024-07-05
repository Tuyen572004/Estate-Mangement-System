<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05/05/2024
  Time: 3:21 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp" %>
<c:url var="customerEditUrl" value="/admin/building-edit"/>
<c:url var="customerApi" value="/api/customers"/>
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
                    <h1>Thông tin khách hàng
                    </h1>
                </div><!-- /.page-header -->

                <div class="row">
                    <form:form modelAttribute="modelEdit" action="${customerEditUrl}" method="POST"
                               id="formEditCustomer"
                               enctype="multipart/form-data">
                    <div class="col-xs-12">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label class="col-xs-3">Tên khách hàng</label>
                                <div class="col-xs-9">
                                    <form:input path="fullname" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Số điện thoại</label>
                                <div class="col-xs-9">
                                    <form:input path="phone" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Email</label>
                                <div class="col-xs-9">
                                    <form:input path="email" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Tên công ty</label>
                                <div class="col-xs-9">
                                    <form:input path="companyName" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Nhu cầu</label>
                                <div class="col-xs-9">
                                    <form:input path="demand" class="form-control"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3">Tình trạng</label>
                                <div class="col-xs-9">
                                    <form:select path="status" class="form-control">
                                        <!-- Check if the customer has a status before -->
                                        <c:choose>
                                            <c:when test="${not empty modelEdit.status}">
                                                <!-- If the customer have a status, display it first -->
                                                <form:option value="${modelEdit.status}"
                                                             label="${modelEdit.status}"/> <!-- Because send in form "Đã xử lý" and save in DB-->
                                                <!-- Display the rest of status options -->
                                                <c:forEach var="statusIt" items="${status}">
                                                    <c:if test="${statusIt.value != modelEdit.status}">
                                                        <form:option value="${statusIt.value}"
                                                                     label="${statusIt.value}"/>
                                                    </c:if>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <!-- If the customer has not been created -->
                                                <c:forEach var="statusIt" items="${status}">
                                                    <form:option value="${statusIt.value}"
                                                                 label="${statusIt.value}"/>
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </form:select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-xs-3"></label>
                                <div class="col-xs-9">
                                    <c:if test="${modelEdit.id != null}">
                                        <button type="button" class="btn btn-primary"
                                                id="btnAddOrUpdateCustomer">
                                            Sửa thông tin
                                        </button>
                                    </c:if>
                                    <c:if test="${modelEdit.id == null}">
                                        <button type="button" class="btn btn-primary"
                                                id="btnAddOrUpdateCustomer">
                                            Thêm khách hàng
                                        </button>
                                    </c:if>
                                    <a href="/admin/customer-list" class="btn btn-primary">
                                        Hủy thao tác
                                    </a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                </form:form>
            </div>

            <c:if test="${not empty modelEdit.id}">
                <c:forEach var="transactionType" items="${transaction}">
                    <div class="col-xs-12">
                        <div class="col-sm-12">
                            <h3 class="header smaller lighter blue">${transactionType.value}</h3>
                            <button class="btn btn-lg btn-primary"
                                    onclick="addTransaction('${transactionType.key}')">
                                <i class="orange ace-icon fa fa-location-arrow bigger-130"></i>Add
                            </button>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <div class="col-sm-12">
                            <table class="table table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Ngày tạo</th>
                                    <th>Người tạo</th>
                                    <th>Ngày sửa</th>
                                    <th>Người sửa</th>
                                    <th>Chi tiết giao dịch</th>
                                    <th>Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:if test="${transactionType.key =='CSKH'}">
                                    <c:forEach var="transactionDetail" items="${listTakeCare}">
                                        <tr>
                                            <td>${transactionDetail.createdDate}</td>
                                            <td>${transactionDetail.createdBy}</td>
                                            <td>${transactionDetail.modifiedDate}</td>
                                            <td>${transactionDetail.modifiedBy}</td>
                                            <td>${transactionDetail.note}</td>
                                            <td>
                                                <div class="hidden-sm hidden-xs btn-group">
                                                    <button class="btn btn-xs btn-info" data-toggle="tooltip"
                                                            title="Sửa thông tin giao dịch"
                                                            onclick="updateTransaction(${transactionDetail.id},'${transactionDetail.note}')">
                                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${transactionType.key =='DDX'}">
                                    <c:forEach var="transactionDetail" items="${listVisit}">
                                        <tr>
                                            <td>${transactionDetail.createdDate}</td>
                                            <td>${transactionDetail.createdBy}</td>
                                            <td>${transactionDetail.modifiedDate}</td>
                                            <td>${transactionDetail.modifiedBy}</td>
                                            <td>${transactionDetail.note}</td>
                                            <td>
                                                <div class="hidden-sm hidden-xs btn-group">
                                                    <button class="btn btn-xs btn-info" data-toggle="tooltip"
                                                            title="Sửa thông tin giao dịch"
                                                            onclick="updateTransaction(${transactionDetail.id},'${transactionDetail.note}')">
                                                        <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                    </button>
                                                </div>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>

        <div class="modal fade" id="addTransactionModal" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">Nhập giao dịch</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group has-success">
                            <label for="note"
                                   class="col-xs-12 col-sm-3 control-label no-padding-right">Chi tiết giao dich
                            </label>
                            <div class="col-xs-12 col-sm-9">
                            <span class="block input-icon input-icon-right">
                                <input type="text" id="note" class="width-100">
                                </span>
                            </div>
                        </div>
                        <input type="hidden" id="customerId" name="customerId" value="${modelEdit.id}">
                        <input type="hidden" id="code" name="code" value="">
                        <input type="hidden" id="id" name="id" value="">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" id="btnAddOrUpdateTransaction" value="">Thêm giao
                            dịch
                        </button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>

        </div><!-- /.row -->
    </div><!-- /.page-content -->
</div>

</div><!-- /.main-content -->
<script>

    $('#btnAddOrUpdateCustomer').click(function () {
        var dataForm = $('#formEditCustomer').serializeArray(); // get data from form
        var data = {};
        data['id'] = "${modelEdit.id}";
        $.each(dataForm, function (i, item) {
            data[item.name] = item.value;
        })
        if (data['fullname'] == null || data['fullname'] == "") {
            alert('Name is required');
        } else if (data['phone'] == null || data['phone'] == "") {
            alert('Phone is required');
        } else {
            fnAddOrUpdate(data)  // call function send data to server
        }
    });


    function fnAddOrUpdate(data) { // function send data to server with ajax
        $.ajax({
            type: "POST",
            url: "${customerApi}",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "text",
            success: function (response) {
                alert(response);
                window.location.replace("/admin/customer-list")
            },
            error: function (response) {
                alert(response);
            }
        });
    }

    function addTransaction(transactionType) {
        $('#addTransactionModal').modal();
        $('#code').val(transactionType);
    }

    function updateTransaction(id, note) {
        $('#addTransactionModal').modal();
        $('#id').val(id);
        $('#note').val(note);
    }

    $('#btnAddOrUpdateTransaction').click(function (e) {
        e.preventDefault();
        var data = {};
        data['id'] = $('#id').val();
        data['code'] = $('#code').val();
        data['customerId'] = $('#customerId').val();
        data['note'] = $('#note').val();
        if (data['note'] == null || data['note'] == '') {
            alert('Chi tiết giao dịch không được để trống');
        }
        fnAddOrUpdateTransaction(data)  // call function send data to server
    });

    function fnAddOrUpdateTransaction(data) {
        $.ajax({
            type: "POST",
            url: "${customerApi}/transaction",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "text",
            success: function (response) {
                alert(response);
                window.location.reload();
            },
            error: function (response) {
                alert(response);
            }
        });
    }

</script>

</body>
</html>
