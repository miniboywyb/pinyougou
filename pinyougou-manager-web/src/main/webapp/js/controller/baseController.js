app.controller("baseController",function ($scope) {
    $scope.paginationConf = {
        currentPage: 1,
        totalItems: 10,
        itemsPerPage: 10,
        perPageOptions: [10, 20, 30, 40, 50],
        onChange: function () {
            $scope.reloadList();
        }
    }
    $scope.selectId = [];
    $scope.updateSelection = function ($event, id) {
        if ($event.target.checked) {
            $scope.selectId.push(id);
        } else {
            var index = $scope.selectId.indexOf(id);
            $scope.selectId.splice(index, 1);
        }
    }
    $scope.reloadList=function(){
        $scope.search($scope.paginationConf.currentPage,
            $scope.paginationConf.itemsPerPage);
    }

    $scope.selectJSON = function (json, key) {
        var list = JSON.parse(json);
        var strText = "";
        for (var i = 0; i < list.length; i++) {
            strText += list[i][key];
            if (i < list.length - 1) {
                strText += ",";
            }
        }
        return strText;
    }


})