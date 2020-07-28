<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div id="container" class="conr_login">
		<div class="conr_in">
			<div class="form_login con_siz">
				<h1 class="logo al_c"><img src="/images/comm/logo.png" alt="logo" /></h1>
				<form action="login.do" method="post">
			    <input type="hidden" name="action" value="do" />
				<div class="input_form">
					<div class="input_comm input_id">
						<p class="tit en_up">ID</p>
						<input name="id" type="text" class="inp_txt" placeholder="ID"/>
					</div>
					<div class="input_comm input_pass">
						<p class="tit en_up">PASSWORD</p>
						<input name="password" type="password" class="inp_txt" placeholder="PASSWORD"/>
					</div>
					<div class="info_set">
						<ul class="flt_clr">
							<li>
								<input type="checkbox" id="stateID" class="inp_ch" />
								<label for="stateID">아이디  저장</label>
							</li>
							<li>
								<input type="checkbox" id="statePass" class="inp_ch" />
								<label for="statePass">비밀번호 저장</label>
							</li>
						</ul>
					</div>
					<c:if test="${not empty errMsg }">
						<div class="alert alert-danger" id="dangerMsg" role="alert">${errMsg }</div>
					</c:if>
					<div class="btn_wrap">
						<button type="submit" class="btn_type01">SIGN IN</button>
					</div>
				</div>
				</form>
			</div>
		</div>
	</div>