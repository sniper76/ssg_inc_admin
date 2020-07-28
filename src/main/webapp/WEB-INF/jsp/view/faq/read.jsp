<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
					<form name="faqWriteForm">
					<div class="mid">
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
													    <input type="hidden" name="boardIdx" class="inp_txt02" value='${boardVO.boardIdx}'/>
														<input type="text" name="title" class="inp_txt02" value='${boardVO.title}' placeholder="제목을 입력해주세요"/>
													</span>
													<span class="cell w3 th">등록일</span>
													<span class="cell w4"><fmt:formatDate pattern="yyyy-MM-dd" value="${boardVO.createDatetime}" /></span>
												</div>
											</div>

											<div class="substance">
												<textarea name="contents" placeholder="답변을 입력해주세요.">${boardVO.contents}</textarea>
											</div>
										</div>

										<div class="btn_wrap">
											<button type="button" class="btn_comm btn_delete" onclick="javascript:remove()">삭제</button>
											<button type="button" class="btn_comm btn_upload" onclick="javascript:modify()">수정</button>
										</div>
									</div>
								</div>
							</li>
						</ul>
					</div>
					</form>
<script language="javascript">
 function modify()
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
	 form.action="/faq/modify.do";
	 form.method="POST"
	 form.submit();
 }
 
 function remove()
 {
	 var form = document.faqWriteForm;
	 
	 form.action="/faq/remove.do";
	 form.method="POST"
	 form.submit();
 }

</script>
