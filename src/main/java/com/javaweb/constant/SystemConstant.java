package com.javaweb.constant;

public class SystemConstant {
    /*Spring security 4: ROLE_ADMIN, Spring security 3 not required*/
    public static final String ONE_EQUAL_ONE = " WHERE 1 = 1 ";
    public static final String STAFF_ROLE = "ROLE_STAFF";
    /*Spring security 4: ROLE_ADMIN, Spring security 3 not required*/
    public static final String USER_ROLE = "ROLE_USER";
    public static final String MANAGER_ROLE = "ROLE_MANAGER";
    public static final String HOME = "/trang-chu";
    public static final String ADMIN_HOME = "/admin/home";
    public static final String MODEL = "model";
    public static final String INSERT_SUCCESS = "insert_success";
    public static final String UPDATE_SUCCESS = "update_success";
    public static final String DELETE_SUCCESS = "delete_success";
    public static final String ERROR_SYSTEM = "error_system";
    public static final String ALERT = "alert";
    public static final String MESSAGE_RESPONSE = "messageResponse";
    public static final String PASSWORD_DEFAULT = "123456";
    public static final String CHANGE_PASSWORD_FAIL = "change_password_fail";
    public static final String BUILDING_NOT_FOUND = "BUILDING_NOT_FOUND";

    // constants for model
    public static final String MODEL_SEARCH = "modelSearch";
    public static final String MODEL_STAFFS = "staffs";
    public static final String MODEL_BUILDING_TYPES = "typeCodes";
    public static final String MODEL_DISTRICTS = "districts";
    public static final String MODEL_BUILDING_SEARCH_RESPONSES = "buildingSearchResponses";
    public static final String MODEL_EDIT = "modelEdit";

    // constants for check active
    public static final int ACTIVE_STATUS = 1;

    // constants for url controller
    public static final String BUILDING_LIST_CONTROLLER = "/admin/building-list";
    public static final String BUILDING_EDIT_CONTROLLER = "/admin/building-edit";
    public static final String BUILDING_EDIT_ID_CONTROLLER = "/admin/building-edit-{id}";

    // constants for path of view
    public static final String BUILDING_LIST_VIEW = "admin/building/list";
    public static final String BUILDING_EDIT_VIEW = "admin/building/edit";

}
