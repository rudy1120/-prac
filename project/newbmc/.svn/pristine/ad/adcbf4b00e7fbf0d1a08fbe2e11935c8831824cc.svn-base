<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script>
	$(document).ready(function() {
		var bYoutube = $('#bYoutube').val().trim();
		if(bYoutube != null && bYoutube != '') {
			$('input:radio[name="useYoutubeYN"][value="Y"]').trigger('click');
			$('.selTr').attr('style', 'display: table-row;');
			$('input[data-inputType="file"]').attr('data-validator', 'off');
		}
		else {
			$('input:radio[name="useYoutubeYN"][value="N"]').trigger('click');
			$('.selTr').attr('style', 'display: none;');
			$('input[data-inputType="file"]').attr('data-validator', 'on');
		}
		
		// 유튜브 연결 선택시 
		$('input:radio[name="useYoutubeYN"]').click(function() {
			if (this.value == 'Y') {
				$('.selTr').attr('style', 'display: table-row;');
				$('input[data-inputType="file"]').attr('data-validator', 'off');
			} else if (this.value == 'N') {
				$('.selTr').attr('style', 'display: none;');
				$('input[data-inputType="file"]').attr('data-validator', 'on');
			}
		});
		
	});
</script>

<%-- ============================== 유튜브 영상 ID ============================== --%>
<tr>
	<th scope="row"><label for="useYoutube">유튜브 영상 연결</label></th>
	<td>
		<!-- <p class="tip mB10 mT_im0">※ 유튜브 영상 연결과 동영상 업로드를 동시에 하신 경우 유튜브 영상 연결을 우선합니다.</p> -->
		<label for="useYoutubeY"><input type="radio" name="useYoutubeYN" id="useYoutubeY" value="Y" />연결</label>
		<label for="useYoutubeN"><input type="radio" name="useYoutubeYN" id="useYoutubeN" value="N" checked />미연결</label>
		<span class="tip red">유튜브 영상을 연결하실 경우 연결을 선택해주세요.</span>
	</td>
</tr>
<tr class="selTr" style="display: none;">
	<th scope="row"><label for="bYoutube">유튜브 영상 ID</label></th>
	<td>
		<span style="display:inline-block;width:500px;">
			https://www.youtube.com/watch?v=<input class="input200" id="bYoutube" name="bYoutube" value="${bbsMngVO.bYoutube}" data-required="y" data-fieldname="유튜브 영상 ID" data-validator="on" type="text" value="" maxlength="20" place-holder="유튜브 영상 ID를 입력해주세요.">
		</span>
	</td>
</tr>
