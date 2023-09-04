/**
 * 주소창 팝업 분리 JS
 *
 * @author J.Ryeon Lee
 * @since 2017.04.26
 */

 /** 도로명 주소 검색 callback 함수(함수명 변경 불가) */
function jusoCallBack(roadFullAddr, roadAddrPart1, addrDetail, roadAddrPart2, engAddr, jibunAddr, zipNo) {
	(addr.selector.zip ? jQuery(addr.selector.zip).val(zipNo) : '');
	(addr.selector.addr1 ? jQuery(addr.selector.addr1).val(roadAddrPart1) : '');
	(addr.selector.addr2 ? jQuery(addr.selector.addr2).val(addrDetail) : '');
}

var addr = {
	selector: {
		zip: null,
		addr1: null,
		addr2: null
	},
	popup: function (addr1, addr2, zip) {
		addr.selector.addr1 = addr1;
		addr.selector.addr2 = addr2;
		addr.selector.zip = zip;
		window.open("/common/jsp/unit/jusoPopup.jsp", "pop", "width=560, height=450, scrollbars=yes, resizable=yes");
	}
}