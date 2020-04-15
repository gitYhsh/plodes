$(function () {
    console.log("111111");
});


/**
 * 用户密码登陆
 * **/

function UsernamLogin() {
    $.ajax({
        type: "post",
        url: ctx + "login/form",
        data: $("#form-signin").serialize(),
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                console.log("登陆成功");
                console.log(r);
            } else {
                console.log(r);
            }
        }
    });
}



/**发送验证码**/
function sendCode() {
    var mobile = $("input[name='mobile']").val().trim();
    var regex = /^1[0-9]{10}$/;
    if (mobile === "") {
        console.log("请输入手机号！");
        return;
    }
    if (!regex.test(mobile)) {
        console.log("请输入正确的手机号！");
        return;
    }
    $.get(ctx + "sms/code", {"mobile": mobile}, function (r) {
        console.log(r)
        if (r.code === 200) {
            console.log("短信发送成功，请尽快使用！");
        } else if (r.code === 666) {
            console.log("你的手速也太快了，休息一下吧！");
        } else {
            console.log("短信验证码发送失败，请重新发送！");
        }

    });
}

function mobileLogin() {
    var mobile = $("input[name='mobile']").val().trim();
    var smsCode = $("input[name='smsCode']").val().trim();

    var regex = /^1[0-9]{10}$/;
    if (mobile === "") {
        console.log("请输入手机号！");
        return;
    }
    if (!regex.test(mobile)) {
        console.log("请输入正确的手机号！");
        return;
    }
    $.ajax({
        type: "post",
        url: ctx + "mobile/login",
        data: $("#formCode").serialize(),
        dataType: "json",
        success: function (r) {
            if (r.code === 0) {
                console.log("登陆成功");
                console.log(r);
            } else {
                console.log(r);
            }
        }
    });
}
