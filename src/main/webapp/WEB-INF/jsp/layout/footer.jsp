<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                    <form id="userForm" name="userForm" method="POST">
           			<input name="userCode" id="userCode" type="hidden" value="do" />
					<div class="popup_admin dim_bg">
						<div class="popup_admin_con dim_con">
							<p class="popup_tit al_c fnt_w_b">관리자 비밀번호 변경</p>

							<table class="table">
								<caption>popup 테이블</caption>
								<colgroup>
									<col width="25%">
									<col width="">
								</colgroup>
								<tr>
									<td>ID</td>
									<td>Admin</td>
								</tr>

								<tr>
									<td>현재 비밀번호</td>
									<td>
										<input name="userPassword" id="userPassword" type="password" class="inp_txt02" />
									</td>
								</tr>

								<tr>
									<td>새 비밀번호</td>
									<td>
										<input name="newPassword1" id="newPassword1" type="password" class="inp_txt02" />
									</td>
								</tr>

								<tr>
									<td>새 비밀번호 확인</td>
									<td>
										<input name="newPassword2" id="newPassword2"type="password" class="inp_txt02" />
									</td>
								</tr>
							</table>

							<div class="btn_wrap">
								<button type="button" class="btn_comm btn_check" onclick="userUpdate();">확인</button>
							</div>

							<div class="popup_close">
								<img src="/images/comm/popup_close.png" alt="close" />
							</div>
						</div>
					</div>
				    </form>
<script>

function userUpdate() {

	var formData = $("#userForm").serialize();
	
    $.ajax({
        type: 'POST',
        url: '/user/updateUserAjax',
        data: formData,
        beforeSubmit: function() {
            if ($("#userPassword").val() == '') {
                alert('현재 패스워드를 입력하여 주세요');
                return false;
            }
            if ($("#newPassword1").val() == '') {
                alert('변경하실 패스워드를 입력하여 주세요');
                return false;
            }
            if ($("#newPassword1").val() != $("#newPassword2").val()) {
                alert('변경하실 패스워드를 동일하게 입력하여 주세요');
                return false;
            }
        },
        dataType: "json",
        success: function(data) {
            alert(data.msg);
        },
        error: function (e) {        	
            console.log("del ERROR : ", e);
        }
    });
}
       
</script>