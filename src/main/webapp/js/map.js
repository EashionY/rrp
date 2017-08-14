//地图
    var map = new BMap.Map("map");
    map.enableScrollWheelZoom();    //启用滚轮放大缩小，默认禁用
    map.enableContinuousZoom();    //启用地图惯性拖拽，默认禁用
    var localSearch = new BMap.LocalSearch(map);
    localSearch.enableAutoViewport(); //允许自动调节窗体大小
    function searchByStationName(keyword,name,tel) {
        map.clearOverlays();//清空原来的标注
        var keyword = keyword;
        localSearch.setSearchCompleteCallback(function (searchResult) {
            var poi = searchResult.getPoi(0);
            map.centerAndZoom(poi.point, 16);
            var marker = new BMap.Marker(new BMap.Point(poi.point.lng, poi.point.lat));  // 创建标注，为要查询的地方对应的经纬度
            map.addOverlay(marker);
            var content =name+ "<br/><br/>电话：" +tel;
            var infoWindow = new BMap.InfoWindow("<p style='font-size:14px;'>" + content + "</p>");
            marker.addEventListener("click", function () { this.openInfoWindow(infoWindow); });
        });
        localSearch.search(keyword);
    }