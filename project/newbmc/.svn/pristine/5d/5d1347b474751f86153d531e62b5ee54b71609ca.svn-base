/**
 * 게시판 리스트 관련 스크립트
 *
 * 수정일			수정자			수정내용
 * -------------	------------	-----------------------------
 * 2017.04.27		상천규			최초 생성 및 코딩
 * 2017.05.12		J.Ryeong Lee	js 분리
 *
 *
 * @author J.Ryeong Lee
 * @since 2017.05.12
 */
$(document).ready(function(){

	$("#getCheckedAll").change(function() { // 삭제/복원 관련 전체 선택
		if($("#getCheckedAll").is(":checked")) {
			$("input[name=delChecked]:checkbox").each(function() {
				$(this).prop("checked", true);
			});
		} else {
			$("input[name=delChecked]:checkbox").each(function() {
				$(this).prop("checked", false);
			});
		}
	});

	$("#batch_delete_btn").click(function() { // 게시글 일괄 삭제
		if ($("input[name=delChecked]:checked").length == 0) {
			alert("삭제할 게시글을 선택해주세요.");
			return;
		}

		if (confirm("선택하신 게시글을 삭제하시겠습니까?")) {
			var frm = document.getElementById("list");
			frm.page.value = 1;
			frm.action = yh.deleteUrl;
			frm.submit();
		}

		return false;
	});

	$("#batch_restore_btn").click(function() { // 게시글 일괄 복원
		if ($("input[name=delChecked]:checked").length == 0) {
			alert("복원할 게시글을 선택해주세요.");
			return;
		}

		if (confirm("선택하신 게시글을 복원하시겠습니까?")) {
			var frm = document.getElementById("list");
			frm.page.value = 1;
			frm.action = yh.restoreUrl;
			frm.submit();
		}

		return false;
	});

});