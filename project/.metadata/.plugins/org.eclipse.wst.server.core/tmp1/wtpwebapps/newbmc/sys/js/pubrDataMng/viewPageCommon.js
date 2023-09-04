$(document).ready(function() {
    initDataArea(sheetIs, fileIs, mapIs, linkIs, chartIs);

    // Init DataArea
    function initDataArea(sheet, file, map, link, chart) {
        var firstAr = "";

        if(sheet == "Y") {
            firstAr = "sheet";
        } else if(file == "Y") {
            firstAr = "file";
        } else if(map == "Y") {
            firstAr = "map";
        } else if(link == "Y") {
            firstAr = "link";
        } else if(chart == "Y") {
            firstAr = "chart";
        }

        areaOpen(firstAr);
    }

    function areaOpen(arType) {
        if(arType == 'sheet') {
            $("#sheetAr").slideDown();
            $("#fileAr").css("display","none");
            $("#mapAr").css("display","none");
            $("#linkAr").css("display","none");
            $("#chartAr").css("display","none");

            $("#sheetArOpenBtn").attr("class","tabon");
            $("#fileArOpenBtn").removeAttr("class");
            $("#mapArOpenBtn").removeAttr("class");
            $("#linkArOpenBtn").removeAttr("class");
            $("#chartArOpenBtn").removeAttr("class");

        } else if(arType == 'file') {
            $("#sheetAr").css("display","none");
            $("#fileAr").slideDown();
            $("#mapAr").css("display","none");
            $("#linkAr").css("display","none");
            $("#chartAr").css("display","none");

            $("#sheetArOpenBtn").removeAttr("class");
            $("#fileArOpenBtn").attr("class","tabon");
            $("#mapArOpenBtn").removeAttr("class");
            $("#linkArOpenBtn").removeAttr("class");
            $("#chartArOpenBtn").removeAttr("class");

        } else if(arType == 'map') {
            $("#sheetAr").css("display","none");
            $("#fileAr").css("display","none");
            $("#mapAr").slideDown();
            $("#linkAr").css("display","none");
            $("#chartAr").css("display","none");

            $("#sheetArOpenBtn").removeAttr("class");
            $("#fileArOpenBtn").removeAttr("class");
            $("#mapArOpenBtn").attr("class","tabon");
            $("#linkArOpenBtn").removeAttr("class");
            $("#chartArOpenBtn").removeAttr("class");

        } else if(arType == 'link') {
            $("#sheetAr").css("display","none");
            $("#fileAr").css("display","none");
            $("#mapAr").css("display","none");
            $("#linkAr").css("display","block");
            $("#chartAr").css("display","none");

            $("#sheetArOpenBtn").removeAttr("class");
            $("#fileArOpenBtn").removeAttr("class");
            $("#mapArOpenBtn").removeAttr("class");
            $("#linkArOpenBtn").attr("class","tabon");
            $("#chartArOpenBtn").removeAttr("class");

        } else if(arType == 'chart') {
            $("#sheetAr").css("display","none");
            $("#fileAr").css("display","none");
            $("#mapAr").css("display","none");
            $("#linkAr").css("display","none");
            $("#chartAr").css("display","block");

            $("#sheetArOpenBtn").removeAttr("class");
            $("#fileArOpenBtn").removeAttr("class");
            $("#mapArOpenBtn").removeAttr("class");
            $("#linkArOpenBtn").removeAttr("class");
            $("#chartArOpenBtn").attr("class","tabon");
        }
    }

    $("#sheetArOpenBtn").click(function() {
        areaOpen('sheet');
    });

    $("#mapArOpenBtn").click(function() {
        areaOpen('map');
    });

    $("#fileArOpenBtn").click(function() {
        areaOpen('file');
    });
});