<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
					<div class="mid">
					 	<form name="faqWriteForm" method="post" action="../faq/register.do">
						<ul>
							<li class="mid_in">		
								<div class="mid_in_detail">
									<div class="box_tit">
										<span class="tit">고객지원 - FAQ 등록</span>
									</div>
									<div class="box_con">
										<div class="table_wrap">
											<div class="table">
												<div class="row">
													<span class="cell w1 th">질문</span>
													<span class="cell w2">
														<input type="text" name="title" class="inp_txt02" value='${boardVO.title}' placeholder="제목을 입력해주세요"/>
														<spring:hasBindErrors name="faqBoard">
							    							<c:if test="${errors.hasFieldErrors('title')}">
							    								<Strong>${errors.getFieldError('title').defaultMessage}</Strong>
							    							</c:if>
							    						</spring:hasBindErrors>
													</span>
													<span class="cell w3 th">등록일</span>
													<span class="cell w4"><fmt:formatDate pattern="yyyy-MM-dd" value="${boardVO.createDatetime}" /></span>
												</div>
											</div>

											<div class="substance">
												<textarea name="contents" placeholder="답변을 입력해주세요.">${boardVO.contents}</textarea>
												<spring:hasBindErrors name="faqBoard">
							    					<c:if test="${errors.hasFieldErrors('contents')}">
							    						<Strong>${errors.getFieldError('contents').defaultMessage}</Strong>
							    					</c:if>
							    				</spring:hasBindErrors>
											</div>
										</div>

										<div class="btn_wrap">
											<button type="button" class="btn_comm btn_delete" onclick="javascript:history.go(-1)">취소</button>
											<button type="button" class="btn_comm btn_upload" onclick="javascript:register()">등록</button>
										</div>
									</div>
								</div>
							</li>
						</ul>
						</form>
					</div>
<script language="javascript">
 function register()
 {
	 var form = document.faqWriteForm;
	 
	 if(!form.title.value){
		 alert("제목을 입력하여주세요");
		 form.title.focus();
		 return;
	 }
	 
	 if(!form.contents.value){
		 alert("내용을 입력하여주세요");
		 form.title.focus();
		 return;
	 }
	 
	 form.submit();
 }

</script>
