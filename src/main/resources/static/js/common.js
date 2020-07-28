$(document).ready(function() {
  checkBoxEvent();
  fCheckAll();
  snbCon();
  fileInput();
  popupEvent();
  datepickerEvent();
});

//체크박스 컨트롤
function checkBoxEvent() {
  $(".inp_ch_label").click(function(){
    var thisCheckd = $(this).find(".inp_ch_obj").is(":checked");
    if(thisCheckd){
      $(this).addClass("inp_ch_on");
    }else{
      $(this).removeClass("inp_ch_on");
    }
  });

  $('.detail_view').click(function(){
    $(this).parents('.mid_in_list').next('.mid_in_detail').fadeIn();
    $(this).parents('.mid_in_list').hide();
  });
};//checkBoxEvent

function fCheckAll() {
	$(".check_All_label").click(function(){
		var thisCheckd = $(this).find(".checkAll").is(":checked");
	    if (thisCheckd) {
	    	$(".inp_ch_label").addClass("inp_ch_on");
	        $("input[type=checkbox]").prop("checked", true);
	    } else {
	    	$(".inp_ch_label").removeClass("inp_ch_on");
	        $("input[type=checkbox]").prop("checked", false);
	    }
	});
}

// snb 메뉴 컨트롤
function snbCon(){
  $('.snb').each(function(){
    var _snbWrap = $(this);
    _snbWrap.next('.content').find('.mid_in').not(':first').hide();

    _snbWrap.find('.depth_2_item').click(function(){
      var _ancIndex = $(this).data('num');
      if(!$(this).hasClass('active')){
        _snbWrap.find('.depth_2_item').removeClass('active');
        _snbWrap.next('.content').find('.mid_in').hide();
        _snbWrap.next('.content').find('.mid_in').removeClass('mid_in_on');
        _snbWrap.next('.content').find('.mid_in_list').fadeIn();
        _snbWrap.next('.content').find('.mid_in_detail').hide();
        _snbWrap.next('.content').find('.mid_in').eq(_ancIndex).fadeIn();
        $(this).addClass('active');
      }else{
        _snbWrap.next('.content').find('.mid_in').removeClass('mid_in_on');
        _snbWrap.next('.content').find('.mid_in_list').fadeIn();
        _snbWrap.next('.content').find('.mid_in_detail').hide();
      }
      return false;
    });
  });
};//snbCon



//인풋 파일 이름 변경
function fileInput(){
  $('.file01').on('change', function(){
    var control = $('.inputFileMaskText');
    control.val(this.files[0].name);
  });

  $('.file02').on('change', function(){
    var control2 = $('.inputFileMaskText2');
    control2.val(this.files[0].name);
  });

  $('.file03').on('change', function(){
    var control3 = $('.inputFileMaskText3');
    control3.val(this.files[0].name);
  });

  $('.file04').on('change', function(){
    var control4 = $('.inputFileMaskText4');
    control4.val(this.files[0].name);
  });

  $('.file05').on('change', function(){
    var control5 = $('.inputFileMaskText5');
    control5.val(this.files[0].name);
  });

  $('.file06').on('change', function(){
    var control6 = $('.inputFileMaskText6');
    control6.val(this.files[0].name);
  });

  $('.file07').on('change', function(){
    var control6 = $('.inputFileMaskText7');
    control6.val(this.files[0].name);
  });

  $('.file08').on('change', function(){
    var control8 = $('.inputFileMaskText8');
    control8.val(this.files[0].name);
  });

  $('.file09').on('change', function(){
    var control9 = $('.inputFileMaskText9');
    control9.val(this.files[0].name);
  });

  $('.file10').on('change', function(){
    var control10 = $('.inputFileMaskText10');
    control10.val(this.files[0].name);
  });

  $('.file11').on('change', function(){
    var control11 = $('.inputFileMaskText11');
    control11.val(this.files[0].name);
  });
};//fileInput

//popup on/off
function popupEvent(){
  $('.btn_popup').click(function(){
    $('.popup_admin').fadeIn();
  });

  $('.popup_close').click(function(){
    $('.popup_admin').fadeOut();
  });
};//popupEvent

//datepicker
function datepickerEvent(){
  //input을 datepicker로 선언
  // $(".datepicker").datepicker({
  //   dateFormat: 'yy-mm-dd',
  //   prevText: '이전 달',
  //   nextText: '다음 달',
  //   monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
  //   monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
  //   dayNames: ['일', '월', '화', '수', '목', '금', '토'],
  //   dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
  //   dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
  //   showMonthAfterYear: true,
  //   yearSuffix: '년'
  // });
};//datepickerEvent

$(function() {
  var dateFormat = "yy-mm-dd",
  from = $( "#from" )
  .datepicker({
    dateFormat: 'yy-mm-dd',
    defaultDate: "+1w",
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    showMonthAfterYear: true,
    yearSuffix: '년'
  })
  .on( "change", function() {
    to.datepicker( "option", "minDate", getDate( this ) );
  }),
  to = $( "#to" ).datepicker({
    dateFormat: 'yy-mm-dd',
    defaultDate: "+1w",
    monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
    dayNames: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
    dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
    showMonthAfterYear: true,
    yearSuffix: '년'
  })
  .on( "change", function() {
    from.datepicker( "option", "maxDate", getDate( this ) );
  });

  function getDate( element ) {
    var date;
    try {
      date = $.datepicker.parseDate( dateFormat, element.value );
    } catch( error ) {
      date = null;
    }

    return date;
  }
});
