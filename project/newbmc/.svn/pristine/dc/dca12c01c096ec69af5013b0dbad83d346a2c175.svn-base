/**
 * 서비스명 : 기업정보 조회 js
**/

$(document).ready(function() {
    initSheetHeader();

    if(sheetIs == 'Y') {
        initSheetHeader
        initSheetData();
        setSheetPaging(curSheetPage);
    }

    // Init SheetHeader
    function initSheetHeader() {
        var headerHTML = '';
        headerHTML += '<tr>';
        headerHTML += '<th scope="row">업체명</th>';
        headerHTML += '<th scope="row">대표자</th>';
        headerHTML += '<th scope="row">사업장소재지</th>';
        headerHTML += '<th scope="row">주요생산품</th>';
        headerHTML += '<th scope="row">연락처</th>';
        headerHTML += '<th scope="row">위도</th>';
        headerHTML += '<th scope="row">경도</th>';
        headerHTML += '</tr>';

        $("#SheetHeader").html(headerHTML);
    }

    // Init SheetData
    function initSheetData() {
        getSheetData(curSheetPage);
    }

    // get SheetData
    function getSheetData(curPage) {
        $.getJSON(context + "/sys/pubcDataMng/dataSet/getBusiManufacturerData.do",
            {curSheetPage: curPage},
            function(j) {
                if(j.none != 'none') {
                    var data = "";

                    for (var i = 0; i < j.length; i++) {
                        data += "<tr>";
                        data += "<td>" + j[i].mTitle + "</td>";
                        data += "<td>" + j[i].mName + "</td>";
                        data += "<td>" + j[i].mRoad + "</td>";
                        data += "<td>" + j[i].mProduct + "</td>";
                        data += "<td>" + j[i].mTel + "</td>";
                        data += "<td>" + j[i].latitude + "</td>";
                        data += "<td>" + j[i].longitude + "</td>";
                        data += "</tr>";
                    }

                    $("#sheetData").html(data);

                }
            });
    }

    // set SheetPaging
    function setSheetPaging(curPage) {
        $.getJSON(context + "/sys/pubcDataMng/dataSet/getBusiManufacturerPaging.do",{curSheetPage: curPage},
            function(data) {
                var firstPageOnList = data.firstPageNoOnPageList;
                var lastPageNoPageList = data.lastPageNoPageList;
                var firstPageNo = data.firstPageNo;
                var lastPageNo = data.lastPageNo;
                var curPageNo = data.curPageNo;
                var totalCount = data.totalCount;

                if(parseInt(curPageNo) > parseInt(lastPageNo)) {
                    setSheetPaging(lastPageNo);
                }

                var pagingHTML = '';
                pagingHTML += '<a href="#" onclick="goSheetPage('+firstPageNo+'); return false;" class="btn_frist">맨첨으로</a>\n';

                if(curPageNo > 10) {
                    pagingHTML += '<a href="#" onclick="goSheetPage(' + parseInt(curPageNo - 10) + '); return false;" class="btn_10prev">이전10페이지</a>\n';
                } else {
                    pagingHTML += '<a href="#" onclick="goSheetPage(' + firstPageNo + '); return false;" class="btn_10prev">이전10페이지</a>\n';
                }

                var curClass = 'class="on"';
                for(var i = firstPageOnList; i<= lastPageNoPageList; i++) {
                    pagingHTML += '<a href="#" ';
                    if(curPageNo == i) {pagingHTML += curClass;}
                    pagingHTML += ' onclick="goSheetPage('+i+'); return false;"><b>' + i + '</b></a>';
                }

                if((parseInt(curPageNo + 10) <= parseInt(lastPageNo))) {
                    pagingHTML += '<a href="#" onclick="goSheetPage(' + parseInt(curPageNo + 10) + '); return false;" class="btn_10next">다음10페이지</a>\n';
                } else if((parseInt(curPageNo + 10) > parseInt(lastPageNo))) {
                    pagingHTML += '<a href="#" onclick="goSheetPage(' + lastPageNo + '); return false;" class="btn_10next">다음10페이지</a>\n';
                }

                pagingHTML += '<a href="#" onclick="goSheetPage('+lastPageNo+'); return false;" class="btn_end">맨끝으로</a>\n';
                $("#lastPageTxt").html(lastPageNo);
                $("#totalCntTxt").html(totalCount);
                $("#box_sheet_page").html(pagingHTML);
            })
    }

});

// get SheetData
function goSheetPage(curPage) {
    $.getJSON(context + "/sys/pubcDataMng/dataSet/getBusiManufacturerData.do",
        {curSheetPage: curPage},
        function(j) {
            if(j.none != 'none') {
                curSheetPage = curPage;
                var data = "";

                for (var i = 0; i < j.length; i++) {
                    data += "<tr>";
                    data += "<td>" + j[i].mTitle + "</td>";
                    data += "<td>" + j[i].mName + "</td>";
                    data += "<td>" + j[i].mRoad + "</td>";
                    data += "<td>" + j[i].mProduct + "</td>";
                    data += "<td>" + j[i].mTel + "</td>";
                    data += "<td>" + j[i].latitude + "</td>";
                    data += "<td>" + j[i].longitude + "</td>";
                    data += "</tr>";
                }
                $("#curPageTxt").html(curPage);
                $("#sheetData").html(data);
                setSheetPaging(curPage);
            }
        });
}

// set SheetPaging
function setSheetPaging(curPage) {
    $.getJSON(context + "/sys/pubcDataMng/dataSet/getBusiManufacturerPaging.do",{curSheetPage: curPage},
        function(data) {
            var firstPageOnList = data.firstPageNoOnPageList;
            var lastPageNoPageList = data.lastPageNoPageList;
            var firstPageNo = data.firstPageNo;
            var lastPageNo = data.lastPageNo;
            var curPageNo = data.curPageNo;
            var totalCount = data.totalCount;

            if(parseInt(curPageNo) > parseInt(lastPageNo)) {
                setSheetPaging(lastPageNo);
            }

            var pagingHTML = '';
            pagingHTML += '<a href="#" onclick="goSheetPage('+firstPageNo+'); return false;" class="btn_frist">맨첨으로</a>\n';

            if(curPageNo > 10) {
                pagingHTML += '<a href="#" onclick="goSheetPage(' + parseInt(curPageNo - 10) + '); return false;" class="btn_10prev">이전10페이지</a>\n';
            } else {
                pagingHTML += '<a href="#" onclick="goSheetPage(' + firstPageNo + '); return false;" class="btn_10prev">이전10페이지</a>\n';
            }

            var curClass = 'class="on"';
            for(var i = firstPageOnList; i<= lastPageNoPageList; i++) {
                pagingHTML += '<a href="#" ';
                if(curPageNo == i) {pagingHTML += curClass;}
                pagingHTML += ' onclick="goSheetPage('+i+'); return false;"><b>' + i + '</b></a>';
            }

            if((parseInt(curPageNo + 10) <= parseInt(lastPageNo))) {
                pagingHTML += '<a href="#" onclick="goSheetPage(' + parseInt(curPageNo + 10) + '); return false;" class="btn_10next">다음10페이지</a>\n';
            } else if((parseInt(curPageNo + 10) > parseInt(lastPageNo))) {
                pagingHTML += '<a href="#" onclick="goSheetPage(' + lastPageNo + '); return false;" class="btn_10next">다음10페이지</a>\n';
            }

            pagingHTML += '<a href="#" onclick="goSheetPage('+lastPageNo+'); return false;" class="btn_end">맨끝으로</a>\n';
            $("#lastPageTxt").html(lastPageNo);
            $("#totalCntTxt").html(totalCount);
            $("#box_sheet_page").html(pagingHTML);
        })
}