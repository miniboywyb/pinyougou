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
})