/**页面加载完成后直接请求得到分页数据*/
$(function () {
    /**去分页第一页*/
    to_page(1);
});

function to_page(pn) {
    $.ajax({
        url: "/emps",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            console.log(result);
            /**加载分页表单数据*/
            build_emps_table(result);
            /**加载解析分页文字信息*/
            build_page_info(result);
            /**加载分页条*/
            build_page_nav(result);

        }
    });
};

/**
 * 构建分页数据表格
 * @param result
 */
function build_emps_table(result) {
    $("#emps_table tbody").empty();
    /**得到json数据中的list*/
    var emps = result.extend.pageInfo.list;
    /**遍历数据*/
    $.each(emps, function (index, item) {
        /**追加到对应的表格中*/
        var empIdTd = $("<td></td>").append(item.empId);
        var empNameTd = $("<td></td>").append(item.empName);
        var genderTd = $("<td></td>").append(item.gender == 'M' ? "男" : "女");
        var emailTd = $("<td></td>").append(item.email);
        var deptNameTd = $("<td></td>").append(item.department.deptName);

        /**编辑按钮*/
        var editBtn = $("<button></button>").addClass("btn btn-primary btn-sm")
            .append($("<span></span>").addClass("glyphicon glyphicon-pencil").append("编辑"));
        /**删除按钮*/
        var delBtn = $("<button></button>").addClass("btn btn-danger btn-sm")
            .append($("<span></span>").addClass("glyphicon glyphicon-trash").append("删除"));
        /**添加按钮到表格*/
        var btnTd = $("<td></td>").append(editBtn).append("&nbsp;&nbsp;&nbsp;&nbsp;").append(delBtn);

        /**添加td到tr*/
        $("<tr></tr>")
            .append(empIdTd)
            .append(empNameTd)
            .append(genderTd)
            .append(emailTd)
            .append(deptNameTd)
            .append(btnTd)
            /**添加td到tbody*/
            .appendTo("#emps_table tbody");

    })
}

/**
 * 解析分页文字信息
 * @param result
 */
function build_page_info(result) {
    $("#page_info_area").empty();
    $("#page_info_area").append("当前" + result.extend.pageInfo.pageNum + "页,总" +
        result.extend.pageInfo.pages + "页,总" +
        result.extend.pageInfo.total + "条记录");
    /**添加全局变量*/
    totalRecord = result.extend.pageInfo.total;


}

/**
 * 构建分页条
 * @param result
 */
function build_page_nav(result) {
    //page_nav_area
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");

    //构建元素
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "javaScript:void(0)"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.extend.pageInfo.hasPreviousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        //为元素添加点击翻页的事件
        firstPageLi.click(function () {
            to_page(1);
        });
        prePageLi.click(function () {
            to_page(result.extend.pageInfo.pageNum - 1);
        });
    }


    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "javaScript:void(0)"));
    if (result.extend.pageInfo.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        nextPageLi.click(function () {
            to_page(result.extend.pageInfo.pageNum + 1);
        });
        lastPageLi.click(function () {
            to_page(result.extend.pageInfo.pages);
        });
    }


    //添加首页和前一页 的提示
    ul.append(firstPageLi).append(prePageLi);
    //1,2，3遍历给ul中添加页码提示
    $.each(result.extend.pageInfo.navigatepageNums, function (index, item) {

        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.extend.pageInfo.pageNum == item) {
            numLi.addClass("active");
        }
        numLi.click(function () {
            to_page(item);
        });
        ul.append(numLi);
    });
    //添加下一页和末页 的提示
    ul.append(nextPageLi).append(lastPageLi);

    //把ul加入到nav
    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("#page_nav_area");
}


/**点击弹出框*/
$("#emp_add_modal_btn").click(function () {
    /**重置表单*/
    $("#empAddModal form")[0].reset();
    /**查询部门信息*/
    selectDepts();
    $('#empAddModal').modal({
        backdrop: "static"
    });
    /**数据校验*/


});

/**查出所有的部门信息并显示在下拉列表中*/
function selectDepts() {
    $("#deptName_add_input").empty();
    $.ajax({
        type: "GET",
        url: "/depts",
        success: function (result) {
            var depts = result.extend.depts;
            console.log(result);
            $.each(result.extend.depts, function () {
                var optionEle = $("<option></option>").append(this.deptName).attr("value", this.deptId);
                optionEle.appendTo("#deptName_add_input");
            });
        }
    });
}

/**数据校验*/
function valiData_save_form() {

    /**
     * 用户名校验
     * @type {*|jQuery}
     */
    var empName = $("#empName_add_input").val();
    var reqName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5}$)/
    var test = reqName.test(empName);
    if (test !== true) {

        show_validata_msg("#empName_add_input", "error", "员工姓名必须是6~16数字和英文字母或者2~5为中文汉字");
        return false;
    }else {

        show_validata_msg("#empName_add_input", "success", "");
    }


    /**
     * 查询用户名是否存在
     */

    $("#empName_add_input").change(function(){
        console.log($(this));
        alert("anchange");
        var value = $(this).val();
        var ifExit = selectExit("empName","exitEmpName", value);
        if (ifExit){
            show_validata_msg("#empName_add_input","success","用户名可用！")
        } else if (! ifExit){
            show_validata_msg("#empName_add_input","error","用户名不可用！")
        }
    });

    /**
     * 查重
     * @param url
     * @param value
     * @returns {boolean}
     */
    function selectExit(dataName,url,value) {
        var returnExt = false;
        $.ajax({
            url: url,
            data: dataName+"="+value,
            type: "POST",
            success: function (result) {
                if (result.code==100) {
                    returnExt = true;
                }else if (result.code==200) {
                    returnExt = false;
                }
            }
        });
        return returnExt;
    }

    /**
     * 邮箱校验
     * @type {*|jQuery}
     */
    var email = $("#email_add_input").val();
    var reqEmail = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/
    var test1 = reqEmail.test(email);
    if (test1 !== true) {

        show_validata_msg(this, "error", "Email格式有误");
        return false;
    }else {

        show_validata_msg(this, "success", "");
    }

    return true;
}

/**
 * 显示校验信息
 * @param ele元素
 * @param status状态
 * @param msg显示信息
 */
function show_validata_msg(ele,status,msg){
    /**将原有清空*/
    $(ele).parent().removeClass("has-success has-error")
    $(ele).next("span").text("");

    if ("success" == status) {
        $(ele).parent().addClass("has-success");
        $(ele).next("span").text("");
    }else if ("error" ==status) {
        $(ele).parent().addClass("has-error");
        $(ele).next("span").text(msg);
    }

}


/**
 * 添加员工
 */
$("#dept_form_submit_btn").click(function () {


    /**数据校验*/
    if (!valiData_save_form()) {
        return false;
    }
    /**提交添加数据*/
    $.ajax({
        url: "emp",
        data: $("#empAddModal form").serialize(),
        type: "POST",
        success: function (result) {
            alert(result.msg);
            parent.location.reload();
            $("#empAddModal").modal('hide');
            //2、来到最后一页，显示刚才保存的数据
            //发送ajax请求显示最后一页数据即可
           /* to_page(totalRecord);*/

        }
    });
});