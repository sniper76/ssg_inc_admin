<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
					<form name="frm" action = "<c:url value='/cs1/listPage.do' />" method="post">
					<input type="hidden" name="boardType" id="boardType" value="1" >
					<div class="mid">
						<ul>
							<li class="mid_in">
								<div class="mid_in_list">
									<div class="box_tit">
										<span class="tit">상품문의 - 문의하기 </span>
									</div>
									<div class="box_con">
										<div class="table_wrap">
											<div class="search flt_clr">
												<div class="select_wrap flt_l">
														<select name="searchType" id="searchType" class="select">
															<option value="t" 
															  <c:out value="${pageMaker.cri.searchType eq 't'?'selected':''}"/>>제목</option>
															<option value="n"
															  <c:out value="${pageMaker.cri.searchType eq 'n'?'selected':''}"/>>이름</option>
															<option value="c"
															  <c:out value="${pageMaker.cri.searchType eq 'c'?'selected':''}"/>>회사명</option>
														</select>
												</div>

												<div class="input_wrap flt_l">
													<input type="text"  name="keyword" id="keyword" class="inp_txt" value='${pageMaker.cri.keyword}' class="inp_txt" />
												</div>

												<div class="btn_search flt_l">
													<button type="button" id="searchBtn" name="searchBtn"><img src="/images/comm/btn_search.png" alt="search" /></button>
												</div>
											</div>
											<div class="table">
												<div class="thead">
												    <div class="row">
														<span class="cell w1">
															<label class="inp_ch_label check_All_label">
															    <input type="checkbox" id="checkAll" name="checkAll" class="checkAll"  />
															</label>
														</span>
														<span class="cell w2">No.</span>
														<span class="cell w3">제목</span>
														<span class="cell w4">이름</span>
														<span class="cell w5">회사명</span>
														<span class="cell w6">등록일</span>
													</div>
												</div>

												<div class="tbody">
													<c:forEach items="${list}" var="boardVO">
													<div class="row">
														<span class="cell w1">
															<label class="inp_ch_label">
																<input type="checkbox" id="check1" name="check1" class="inp_ch_obj" />
			    												<input name="checkBoardIdx" type="hidden" value="<c:out value='${boardVO.boardIdx}'/>">
															</label>
														</span>
														<span class="cell w2">${boardVO.boardIdx}</span>
														<span class="cell w3"><a href="<c:url value='/cs1/read.do?boardIdx=${boardVO.boardIdx}' />">${boardVO.title}</a></span>
														<span class="cell w4">${boardVO.csUserName}</span>
														<span class="cell w5">${boardVO.csUserCompany}</span>
														<span class="cell w6"><fmt:formatDate pattern="yyyy-MM-dd" value="${boardVO.createDatetime}" /></span>
													</div>
													</c:forEach>
												</div>
											</div>
										</div>

										<div class="btn_wrap">
											<button type="button" class="btn_comm btn_delete" onclick="javascript:fnDeleteCs()">삭제</button>
										</div>

										<div class="paging_box al_c">
											<ul class="paging_list flt_clr">
												
												<c:if test="${pageMaker.prev}">
													<li class="btn_arrow btn_prev"><a href="/cs1/listPage.do${pageMaker.makeSearch(pageMaker.startPage -1)}"><img src="/images/comm/btn_arrow.png" alt="prev" /></a></li>
												</c:if>
												
												<c:forEach begin="${pageMaker.startPage}" end="${pageMaker.endPage }" var="idx">
												<li 
												    <c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
													<a href="/cs1/listPage.do${pageMaker.makeSearch(idx)}">${idx}</a>
												</li>
												</c:forEach>
												
												<c:if test="${pageMaker.next && pageMaker.endPage>0}">
													<li class="btn_arrow btn_next"><a href="/cs1/listPage.do${pageMaker.makeSearch(pageMaker.endPage +1)}"><img src="/images/comm/btn_arrow.png" alt="next" /></a></li>
												</c:if>
												
												
											</ul>
										</div>
									</div>
								</div>
							</li>
						</ul>						
					</div>
					<input name="checkedIdForDel" type="hidden" />
					</form>	
<script>
$(document).ready(
		
	function() {
		
		$('#searchBtn').on(
			"click",
			function(event){
				self.location = "listPage.do"
				    + '${pageMaker.makeQuery(1)}'
				    + "&searchType=" + $("select option:selected").val()
				    + "&keyword=" + encodeURIComponent($('#keyword').val());
			}
				
	    )
	}		
)
</script>	
<script type="text/javaScript" language="javascript">
function fnDeleteCs() {
    var checkField = document.frm.check1;
    var id = document.frm.checkBoardIdx;
    var checkedIds = "";
    var checkedCount = 0;
    if(checkField) {
        if(checkField.length > 1) {
            for(var i=0; i < checkField.length; i++) {
                if(checkField[i].checked) {
                    checkedIds += ((checkedCount==0? "" : ",") + id[i].value);
                    checkedCount++;
                }
            }
        } else {
            if(checkField.checked) {
                checkedIds = id.value;
            }
        }
    }
    if(checkedIds.length > 0) {
        if(confirm("삭제하시겠습니까?")){
        	document.frm.checkedIdForDel.value=checkedIds;
            document.frm.action = "../cs1/removes.do";
            document.frm.method = "POST";
            document.frm.submit();
        }
    } else {
    	alert("선택된 건수가 없습니다.")
    }
}

</script>								