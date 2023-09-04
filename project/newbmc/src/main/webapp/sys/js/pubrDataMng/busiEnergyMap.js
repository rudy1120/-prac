/**
 * 서비스명 : 에너지 공급업체현황 조회 js
**/

$(document).ready(function() {
    initMapHeader();

    if(mapIs == 'Y') {
        initMapHeader
        initMapData();
        setMapPaging(curSheetPage);
    }

    // Init MapHeader
    function initMapHeader() {
        var headerHTML = '';
        headerHTML += '<tr>';
        headerHTML += '<th scope="row">표시</th>';
        headerHTML += '<th scope="row">업체명</th>';
        headerHTML += '<th scope="row">연락처</th>';
        headerHTML += '<th scope="row">소재지</th>';
        headerHTML += '<th scope="row">위도</th>';
        headerHTML += '<th scope="row">경도</th>';
        headerHTML += '</tr>';

        $("#mapHeader").html(headerHTML);
    }

    // Init MapData
    function initMapData() {
        getMapData(curSheetPage);
    }

    // get MapData
    function getMapData(curPage) {
        $.getJSON(context + "/sys/pubcDataMng/dataSet/getBusiEnergyData.do",
            {curMapPage: curPage},
            function(j) {
                if(j.none != 'none') {
                    var data = "";

                    for (var i = 0; i < j.length; i++) {
                        data += "<tr>";
                        data += "<td>" + (i + 1) + "</td>";
                        data += "<td><a href='#' onclick='setDetailMarker("+j[i].latitude+","+ j[i].longitude +"); return false;'>" + j[i].eName + "</a></td>";
                        data += "<td>" + j[i].eTel + "</td>";
                        data += "<td>" + j[i].eRoad + "</td>";
                        data += "<td>" + j[i].latitude + "</td>";
                        data += "<td>" + j[i].longitude + "</td>";
                        data += "</tr>";
                    }

                    $("#mapData").html(data);

                }
            });
    }

    // set MapPaging
    function setMapPaging(curPage) {
        $.getJSON(context + "/sys/pubcDataMng/dataSet/getBusiEnergyPaging.do",{curSheetPage: curPage},
            function(data) {
                var firstPageOnList = data.firstPageNoOnPageList;
                var lastPageNoPageList = data.lastPageNoPageList;
                var firstPageNo = data.firstPageNo;
                var lastPageNo = data.lastPageNo;
                var curPageNo = data.curPageNo;
                var totalCount = data.totalCount;

                if(parseInt(curPageNo) > parseInt(lastPageNo)) {
                    setMapPaging(lastPageNo);
                }

                var pagingHTML = '';
                pagingHTML += '<a href="#" onclick="goMapPage('+firstPageNo+'); return false;" class="btn_frist">맨첨으로</a>\n';

                if(curPageNo > 10) {
                    pagingHTML += '<a href="#" onclick="goMapPage(' + parseInt(curPageNo - 10) + '); return false;" class="btn_10prev">이전10페이지</a>\n';
                } else {
                    pagingHTML += '<a href="#" onclick="goMapPage(' + firstPageNo + '); return false;" class="btn_10prev">이전10페이지</a>\n';
                }

                var curClass = 'class="on"';
                for(var i = firstPageOnList; i<= lastPageNoPageList; i++) {
                    pagingHTML += '<a href="#" ';
                    if(curPageNo == i) {pagingHTML += curClass;}
                    pagingHTML += ' onclick="goMapPage('+i+'); return false;"><b>' + i + '</b></a>';
                }

                if((parseInt(curPageNo + 10) <= parseInt(lastPageNo))) {
                    pagingHTML += '<a href="#" onclick="goMapPage(' + parseInt(curPageNo + 10) + '); return false;" class="btn_10next">다음10페이지</a>\n';
                } else if((parseInt(curPageNo + 10) > parseInt(lastPageNo))) {
                    pagingHTML += '<a href="#" onclick="goMapPage(' + lastPageNo + '); return false;" class="btn_10next">다음10페이지</a>\n';
                }

                pagingHTML += '<a href="#" onclick="goMapPage('+lastPageNo+'); return false;" class="btn_end">맨끝으로</a>\n';
                $("#lastPageTxt").html(lastPageNo);
                $("#totalCntTxt").html(totalCount);
                $("#box_map_page").html(pagingHTML);
            })
    }

});

// set updateMap
function updateMap(curPage) {
    $.getJSON(context + "/sys/pubcDataMng/dataSet/getBusiEnergyData.do",
        function(j) {
            var points = new Array();
            if(j.none != 'none') {
                for (var i = 0; i < j.length; i++) {
                    if(j[i].latitude != 0 && j[i].longitude != 0) {
                        points.push(new daum.maps.LatLng(j[i].latitude, j[i].longitude));
                    }
                }
            }
            setMapMarker(points);
        });
}


// get MapData
function goMapPage(curPage) {
    $.getJSON(context + "/sys/pubcDataMng/dataSet/getBusiEnergyData.do",
        {curSheetPage: curPage},
        function(j) {
            if(j.none != 'none') {
                curSheetPage = curPage;
                var data = "";

                for (var i = 0; i < j.length; i++) {
                    data += "<tr>";
                    data += "<td>" + (i + 1) + "</td>";
                    data += "<td><a href='#' onclick='setDetailMarker("+j[i].latitude+","+ j[i].longitude +"); return false;'>" + j[i].eName + "</a></td>";
                    data += "<td>" + j[i].eTel + "</td>";
                    data += "<td>" + j[i].eRoad + "</td>";
                    data += "<td>" + j[i].latitude + "</td>";
                    data += "<td>" + j[i].longitude + "</td>";
                    data += "</tr>";
                }
                $("#curPageMapTxt").html(curPage);
                $("#mapData").html(data);
                updateMap(curPage);
                setMapPaging(curPage);

            }
        });
}

// set MapPaging
function setMapPaging(curPage) {
    $.getJSON(context + "/sys/pubcDataMng/dataSet/getBusiEnergyPaging.do",{curSheetPage: curPage},
        function(data) {
            var firstPageOnList = data.firstPageNoOnPageList;
            var lastPageNoPageList = data.lastPageNoPageList;
            var firstPageNo = data.firstPageNo;
            var lastPageNo = data.lastPageNo;
            var curPageNo = data.curPageNo;
            var totalCount = data.totalCount;

            if(parseInt(curPageNo) > parseInt(lastPageNo)) {
                setMapPaging(lastPageNo);
            }

            var pagingHTML = '';
            pagingHTML += '<a href="#" onclick="goMapPage('+firstPageNo+'); return false;" class="btn_frist">맨첨으로</a>\n';

            if(curPageNo > 10) {
                pagingHTML += '<a href="#" onclick="goMapPage(' + parseInt(curPageNo - 10) + '); return false;" class="btn_10prev">이전10페이지</a>\n';
            } else {
                pagingHTML += '<a href="#" onclick="goMapPage(' + firstPageNo + '); return false;" class="btn_10prev">이전10페이지</a>\n';
            }

            var curClass = 'class="on"';
            for(var i = firstPageOnList; i<= lastPageNoPageList; i++) {
                pagingHTML += '<a href="#" ';
                if(curPageNo == i) {pagingHTML += curClass;}
                pagingHTML += ' onclick="goMapPage('+i+'); return false;"><b>' + i + '</b></a>';
            }

            if((parseInt(curPageNo + 10) <= parseInt(lastPageNo))) {
                pagingHTML += '<a href="#" onclick="goMapPage(' + parseInt(curPageNo + 10) + '); return false;" class="btn_10next">다음10페이지</a>\n';
            } else if((parseInt(curPageNo + 10) > parseInt(lastPageNo))) {
                pagingHTML += '<a href="#" onclick="goMapPage(' + lastPageNo + '); return false;" class="btn_10next">다음10페이지</a>\n';
            }

            pagingHTML += '<a href="#" onclick="goMapPage('+lastPageNo+'); return false;" class="btn_end">맨끝으로</a>\n';
            $("#lastPageMapTxt").html(lastPageNo);
            $("#totalCntMapTxt").html(totalCount);
            $("#box_map_page").html(pagingHTML);
        })
}